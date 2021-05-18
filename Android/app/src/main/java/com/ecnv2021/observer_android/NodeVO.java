package com.ecnv2021.observer_android;

import java.util.ArrayList;

public class NodeVO {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private ArrayList<Node> list;

    public ArrayList<Node> getList() {
        return list;
    }

    public void setList(ArrayList<Node> list) {
        this.list = list;
    }
}

class Node{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
