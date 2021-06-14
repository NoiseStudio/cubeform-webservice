package pl.platrykp.cubeformservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.platrykp.cubeformservice.resources.OnlineServerResource;

import java.io.IOException;

import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ServerService {

    private final Logger logger = LoggerFactory.getLogger(ServerService.class);

    //                                               msg_type | ask
    private static final byte[] SERVER_ALIVE_TEST_PACKAGE = {0, 1};
    //                                                  min  sec    ms
    private static final int CHECK_SERVER_FIXED_RATE = 10 * 1000;//1 * 60 * 1000;

    private final ExecutorService threadPool;

    private final HashMap<UUID, OnlineServerResource> servers;
    private final HashMap<String, UUID> accessTokenToIdMap;

    public ServerService(){
        servers = new HashMap<>();
        accessTokenToIdMap = new HashMap<>();
        threadPool = Executors.newCachedThreadPool();
    }

    public void setServer(OnlineServerResource serverRequest){
        final OnlineServerResource server = serverRequest;
        CompletableFuture.runAsync(()->{
            if(updateServerStatus(server)){
                servers.put(server.getId(), server);
                accessTokenToIdMap.put(server.getAccessToken(), server.getId());
            }
        }, threadPool);
    }

    public OnlineServerResource getServer(UUID id) {
        if(id == null)
            return null;

        return servers.get(id);
    }

    public OnlineServerResource getServer(String accessToken) {
        if(accessToken == null)
            return null;

        return getServer(accessTokenToIdMap.get(accessToken));
    }

    public void dropServer(OnlineServerResource server) {
        servers.remove(server.getId());
        accessTokenToIdMap.remove(server.getAccessToken());
    }

    public ArrayList<OnlineServerResource> getServerList() {
        return new ArrayList<>(servers.values());
    }


    @Scheduled(fixedRate = CHECK_SERVER_FIXED_RATE)
    public void processListCheck(){
        logger.info("Yee boy");
        servers.forEach((uuid, serverResource) -> {
            final OnlineServerResource server = serverResource;
            CompletableFuture.runAsync(()->{
                if(updateServerStatus(server)){

                    logger.info("Server {} is still active", server.getId());
                    return;
                }
                logger.info("Server {} is not active", server.getId());
                dropServer(server);
            }, threadPool);
        });
    }


    protected boolean updateServerStatus(OnlineServerResource server){
        /*
        *  Spring  - - -  Server
        *  0b0 0b1   >
        *
        *            <    0b1
        *            <    (int) max Players
        *            <    (int) current players
        *
        *   close communication
        *  Sprint  - - -  Server
        */
        long startCommunicationTime = System.currentTimeMillis();
        try(Socket socket = new Socket(server.getAddress(), server.getPort())){
            logger.info("Checking status for {}", server.getId());
            socket.getOutputStream().write(SERVER_ALIVE_TEST_PACKAGE);
            byte[] msg = new byte[1 + 4 + 4];
            InputStream is = socket.getInputStream();

            int len = 0;
            while (len < msg.length )
                len += is.read(msg, len, msg.length - len);

            if(msg[0] != 1) // if response msg_type != 1
                return false;

            long endCommunicationTime = System.currentTimeMillis();

            long ping = endCommunicationTime - startCommunicationTime;

            ByteBuffer maxPlayersBuff = ByteBuffer.wrap(msg, 1, 4);
            int maxPlayers = maxPlayersBuff.getInt();

            ByteBuffer currPlayersBuff = ByteBuffer.wrap(msg, 5, 4);
            int currPlayers = currPlayersBuff.getInt();

            server.setMaxPlayers(maxPlayers);
            server.setCurrentPlayers(currPlayers);
            server.setPing((int)ping);

            return true;
        }
        catch (IOException er){
            return false;
        }
    }
}
