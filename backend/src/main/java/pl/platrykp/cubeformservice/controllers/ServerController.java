package pl.platrykp.cubeformservice.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.platrykp.cubeformservice.details.UserDetailsImpl;
import pl.platrykp.cubeformservice.models.ServerAccessTokenEntity;
import pl.platrykp.cubeformservice.models.ServerEntity;
import pl.platrykp.cubeformservice.repositories.ServerAccessTokenRepository;
import pl.platrykp.cubeformservice.repositories.ServerRepository;
import pl.platrykp.cubeformservice.requests.LoginServerRequest;
import pl.platrykp.cubeformservice.requests.RegisterServerRequest;
import pl.platrykp.cubeformservice.resources.MultipleOfflineServerResource;
import pl.platrykp.cubeformservice.resources.MultipleOnlineServerResource;
import pl.platrykp.cubeformservice.resources.OfflineServerResource;
import pl.platrykp.cubeformservice.resources.OnlineServerResource;
import pl.platrykp.cubeformservice.services.ServerService;
import pl.platrykp.cubeformservice.util.JsonResponse;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servers")
public class ServerController {

    private final Logger logger = LoggerFactory.getLogger(ServerController.class);

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private ServerService serverService;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ServerAccessTokenRepository accessTokenRepository;

    @GetMapping("/online")
    public Object getOnlineServers(){
        return new MultipleOnlineServerResource(serverService.getServerList());
    }

//    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('admin')")
//    public Object getAllServers() {
//        return mapToMultipleOfflineServerResources(serverRepository.findAll());
//    }

    @PostMapping("/online")
    public Object loginServer(@RequestBody LoginServerRequest loginServerRequest){

        Optional<ServerEntity> serverEntityOptional = serverRepository
                .findByAccessToken_AccessToken(loginServerRequest.getAccessToken());

        if(serverEntityOptional.isEmpty())
            return JsonResponse.badRequest("wrong access token");

        ServerEntity serverEntity = serverEntityOptional.get();

        OnlineServerResource onlineServerResource = new OnlineServerResource(
                serverEntity.getId(),
                loginServerRequest.getAccessToken(),
                loginServerRequest.getAddress(),
                loginServerRequest.getPort(),
                serverEntity.getName(),
                serverEntity.getGameMode(),
                serverEntity.isMods(),
                serverEntity.getOwner()
        );

        serverService.setServer(onlineServerResource);
        return JsonResponse.ok("Ok");
    }

    @GetMapping("/my")
    public Object getMyServers(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return mapToMultipleOfflineServerResources(serverRepository.findByOwner_Id(userDetails.getId()));
    }

    @PostMapping("/register")
    public Object registerServer(@RequestBody RegisterServerRequest serverRequest, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ServerAccessTokenEntity accessTokenEntity = createNewAccessTokenEntity();

        ServerEntity serverEntity = new ServerEntity(
                serverRequest.getName(),
                serverRequest.getGameMode(),
                serverRequest.isMods(),
                userDetails.getUserEntity(),
                accessTokenEntity
        );
        accessTokenEntity.setServer(serverEntity);

        try {
            return new OfflineServerResource(serverRepository.save(serverEntity));
        }
        catch (DataAccessException er){
            logger.info(er.getMessage());
            return JsonResponse.badRequest("Cannot insert server");
        }
    }

    @PutMapping("/{id}/token")
    public Object regenerateToken(@PathVariable UUID id, Authentication authentication){
        Optional<ServerEntity> serverEntityOptional = serverRepository.findById(id);
        if(serverEntityOptional.isEmpty())
            return JsonResponse.notFound("No server with this id");

        ServerEntity serverEntity = serverEntityOptional.get();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if(!userDetails.getId().equals(serverEntity.getOwner().getId()))
            return JsonResponse.forbidden("This is not your server");

        ServerAccessTokenEntity oldAccessToken = serverEntity.getAccessToken();

        ServerAccessTokenEntity newAccessTokenEntity = createNewAccessTokenEntity();
        serverEntity.setAccessToken(newAccessTokenEntity);


        try {
            accessTokenRepository.delete(oldAccessToken);
            return new OfflineServerResource(serverRepository.save(serverEntity));
        }
        catch (DataAccessException er){
            logger.info(er.getMessage());
            return JsonResponse.badRequest("Cannot insert access token");
        }
    }

    private ServerAccessTokenEntity createNewAccessTokenEntity(){
        String accessToken = generateToken();

        ServerAccessTokenEntity accessTokenEntity = new ServerAccessTokenEntity();
        accessTokenEntity.setCreationTime(new Timestamp(System.currentTimeMillis()));
        accessTokenEntity.setAccessToken(accessToken);

        return accessTokenEntity;
    }

    private String generateToken(){
        byte[] accessTokenBytes = new byte[32];
        secureRandom.nextBytes(accessTokenBytes);
        return Base64.encodeBase64String(accessTokenBytes);
    }

    private MultipleOfflineServerResource mapToMultipleOfflineServerResources(List<ServerEntity> list) {
        return new MultipleOfflineServerResource(
                list.stream()
                        .map(OfflineServerResource::new)
                        .collect(Collectors.toList())
        );
    }

}
