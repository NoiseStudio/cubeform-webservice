package pl.platrykp.cubeformservice.resources;

import lombok.Data;

import java.util.Collection;

@Data
public class MultipleOnlineServerResource {

    private Collection<OnlineServerResource> servers;

    public MultipleOnlineServerResource(Collection<OnlineServerResource> servers) {
        this.servers = servers;
    }
}
