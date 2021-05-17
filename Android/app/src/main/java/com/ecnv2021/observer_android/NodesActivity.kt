package com.ecnv2021.observer_android

import dev.bandb.graphview.decoration.edge.ArrowEdgeDecoration
import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import dev.bandb.graphview.layouts.energy.FruchtermanReingoldLayoutManager


class NodesActivity : ServerMapActivity() {

    public override fun setLayoutManager() {
        recyclerView.layoutManager = FruchtermanReingoldLayoutManager(this, 1000)
    }

    public override fun setEdgeDecoration() {
        recyclerView.addItemDecoration(ArrowEdgeDecoration())
    }

    public override fun createGraph(): Graph {
        val graph = Graph()
        /*val a = Node("user")
        val b = Node(nodeText)
        val c = Node(nodeText)
        val d = Node(nodeText)
        val e = Node(nodeText)
        val f = Node(nodeText)
        val g = Node(nodeText)
        val h = Node(nodeText)
        graph.addEdge(a, b)
        graph.addEdge(a, c)
        graph.addEdge(a, d)
        graph.addEdge(c, e)
        graph.addEdge(d, f)
        graph.addEdge(f, c)
        graph.addEdge(g, c)
        graph.addEdge(h, g)*/
        val user = Node("USER")
        val frontweb = Node("FrontWeb")
        val backend = Node("Backend")
        val backweb = Node("BackendWeb")
        val mysql = Node("mysql")
        graph.addEdge(user,frontweb)
        graph.addEdge(frontweb,Node("Cloud"))
        graph.addEdge(frontweb,backweb)
        graph.addEdge(frontweb,backend)
        graph.addEdge(backend,mysql)
        graph.addEdge(backweb,mysql)
        return graph
    }
}