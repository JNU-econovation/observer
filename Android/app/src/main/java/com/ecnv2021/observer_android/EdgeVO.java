package com.ecnv2021.observer_android;

import java.util.ArrayList;
import java.util.List;

public class EdgeVO {
    private List<EdgeResponseDTO> edges;

    public List<EdgeResponseDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeResponseDTO> edges) {
        this.edges = edges;
    }
}

class EdgeResponseDTO {
    private String clientAddr;
    private String remoteAddr;

    public String getClientAddr() {
        return clientAddr;
    }

    public void setClientAddr(String clientAddr) {
        this.clientAddr = clientAddr;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
}
