package pl.platrykp.cubeformservice.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.platrykp.cubeformservice.resources.OnlineServerResource;

import java.io.IOException;

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
    //                                               msg_type | ask
    private static final byte[] SERVER_ALIVE_TEST_PACKAGE = {0, 1};
    //                                                  min  sec    ms
    private static final int CHECK_SERVER_FIXED_RATE = 1 * 60 * 1000;

    private final ExecutorService threadPool;

    private final HashMap<UUID, OnlineServerResource> servers;
    private final HashMap<String, UUID> accessTokenToIdMap;

    public ServerService(){
        servers = new HashMap<>();
        accessTokenToIdMap = new HashMap<>();
        threadPool = Executors.newCachedThreadPool();
    }

    public void setServer(OnlineServerResource server){
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
        servers.forEach((uuid, serverResource) -> {
            final OnlineServerResource server = serverResource;
            CompletableFuture.runAsync(()->{
                if(updateServerStatus(server))
                    return;
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

        try(Socket socket = new Socket(server.getAddress(), server.getPort())){
            socket.getOutputStream().write(SERVER_ALIVE_TEST_PACKAGE);
            byte[] msg = new byte[1 + 4 + 4];
            int len = socket.getInputStream().read(msg, 0, msg.length);
            if(len != msg.length) // simple test
                return false;


            if(msg[0] != 1) // if response msg_type != 1
                return false;


            ByteBuffer maxPlayersBuff = ByteBuffer.wrap(msg, 1, 4);
            int maxPlayers = maxPlayersBuff.getInt();

            ByteBuffer currPlayersBuff = ByteBuffer.wrap(msg, 5, 4);
            int currPlayers = currPlayersBuff.getInt();

            server.setMaxPlayers(maxPlayers);
            server.setCurrentPlayers(currPlayers);

            return true;
        }
        catch (IOException er){
            return false;
        }
    }
}
