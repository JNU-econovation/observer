package com.capstone.apm.transaction.websocket;

import java.net.URI;
import java.net.URISyntaxException;

public class ServerConfiguration {
    private URI uri;

    public ServerConfiguration(String uri){
        try {
            this.uri = new URI(uri);
        }catch (URISyntaxException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ServerConfiguration(URI uri){
        this.uri = uri;
    }

    public URI getUri() {
        return uri;
    }
}
