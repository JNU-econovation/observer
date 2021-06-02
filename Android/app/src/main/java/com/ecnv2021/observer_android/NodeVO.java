package com.ecnv2021.observer_android;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NodeVO {
    private int size;
    private List<NodeResponseDTO> nodes;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<NodeResponseDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeResponseDTO> nodes) {
        this.nodes = nodes;
    }
}

class NodeResponseDTO {
    private String address;
    private String name;

    public NodeResponseDTO(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String id) {
        this.address = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
