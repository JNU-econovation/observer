package com.ecnv2021.observer_android;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NodeVO {
    private int size;
    private ArrayList<Node> list;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Node> getList() {
        return list;
    }

    public void setList(ArrayList<Node> list) {
        this.list = list;
    }
}

class Node {
    private String id;
    private String name;

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
