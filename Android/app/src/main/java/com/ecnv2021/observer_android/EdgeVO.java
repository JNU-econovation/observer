package com.ecnv2021.observer_android;

import java.util.ArrayList;

public class EdgeVO {
    private ArrayList<Edge> list;

    public ArrayList<Edge> getList() {
        return list;
    }

    public void setList(ArrayList<Edge> list) {
        this.list = list;
    }
}

class Edge {
    private String id1;
    private String id2;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }
}
