package pl.platrykp.cubeformservice.resources;

import lombok.Data;

import java.util.Collection;

@Data
public class MultipleOfflineServerResource {

    private Collection<OfflineServerResource> servers;

    public MultipleOfflineServerResource(Collection<OfflineServerResource> servers) {
        this.servers = servers;
    }
}
