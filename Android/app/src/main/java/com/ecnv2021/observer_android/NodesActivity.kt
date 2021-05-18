package com.ecnv2021.observer_android

import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import dev.bandb.graphview.layouts.layered.SugiyamaArrowEdgeDecoration
import dev.bandb.graphview.layouts.layered.SugiyamaConfiguration
import dev.bandb.graphview.layouts.layered.SugiyamaLayoutManager

class NodesActivity : ServerMapActivity() {

    public override fun setLayoutManager() {
        recyclerView.layoutManager = SugiyamaLayoutManager(this, SugiyamaConfiguration.Builder().build())
    }

    public override fun setEdgeDecoration() {
        recyclerView.addItemDecoration(SugiyamaArrowEdgeDecoration())
    }

    public override fun createGraph(): Graph {
        val graph = Graph()
        val _nodes = arrayListOf<Node>()
        val nodes: List<Node> = _nodes

        _nodes.add(Node("user"))
        _nodes.add(Node("frontweb"))
        _nodes.add(Node("backend"))
        _nodes.add(Node("backendWeb"))
        _nodes.add(Node("mysql"))
        _nodes.add(Node("Cloud"))
        graph.addEdge(nodes.get(0),nodes.get(1))
        graph.addEdge(nodes.get(1),nodes.get(5))
        graph.addEdge(nodes.get(1),nodes.get(2))
        graph.addEdge(nodes.get(1),nodes.get(3))
        graph.addEdge(nodes.get(2),nodes.get(4))
        graph.addEdge(nodes.get(3),nodes.get(4))

        return graph
    }
}