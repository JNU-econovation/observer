package com.ecnv2021.observer_android

import android.util.Log
import com.google.gson.Gson
import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import dev.bandb.graphview.layouts.layered.SugiyamaArrowEdgeDecoration
import dev.bandb.graphview.layouts.layered.SugiyamaConfiguration
import dev.bandb.graphview.layouts.layered.SugiyamaLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NodesActivity : ServerMapActivity() {
    private lateinit var mRetrofit: Retrofit
    private lateinit var mRetrofitAPI: RetrofitAPI
    private val mGson: Gson? = null
    val _nodes = arrayListOf<Node>()
    private val TAG = "TTestNode"
    var nodeResult: NodeVO? = null
    var edgeResult: EdgeVO? = null

    public override fun setLayoutManager() {
        recyclerView.layoutManager =
            SugiyamaLayoutManager(this, SugiyamaConfiguration.Builder().build())
    }

    public override fun setEdgeDecoration() {
        recyclerView.addItemDecoration(SugiyamaArrowEdgeDecoration())
    }

    public override fun createGraph(): Graph {
        val graph = Graph()

        //예시 노드, 간선 추가
        /*
        _nodes.add(Node("user"))
        _nodes.add(Node("frontweb"))
        _nodes.add(Node("backend"))
        _nodes.add(Node("backendWeb"))
        _nodes.add(Node("mysql"))

        graph.addEdge(_nodes.get(0), _nodes.get(1))
        graph.addEdge(_nodes.get(1), _nodes.get(2))
        graph.addEdge(_nodes.get(1), _nodes.get(3))
        graph.addEdge(_nodes.get(2), _nodes.get(4))
        graph.addEdge(_nodes.get(3), _nodes.get(4))*/

        //node 생성
        if (nodeResult != null) {
            for (i in 0 until nodeResult!!.nodes.size) {
                _nodes.add(Node(nodeResult!!.nodes.get(i).name))
            }
            Log.d(TAG, "nodeResult: node 생성.")
        } else {
            Log.d(TAG, "nodeResult: null임 ")
        }
        //_nodes.add(Node("mysql"))
        //graph.addEdge(_nodes.get(0), _nodes.get(1))

        //edge 생성
        if (edgeResult != null) {
            for (i in 0 until edgeResult!!.edges.size) {
                var edge1: Int? =
                    nodeResult?.nodes?.indexOfFirst { it.address == edgeResult!!.edges.get(i).clientAddr }
                Log.d(TAG, "edge1: " + edge1)
                var edge2: Int? =
                    nodeResult?.nodes?.indexOfFirst { it.address == edgeResult!!.edges.get(i).remoteAddr }
                Log.d(TAG, "edge2: " + edge2)

                if (edge1 != null && edge1 != -1) {
                    graph.addEdge(_nodes.get(edge1), _nodes.get(edge2!!))
                    Log.d(TAG, "edgeResult: edge 생성. " + i)
                } else if (edge1 == -1 && edge2 != 0) {
                    graph.addEdge(_nodes.get(0), _nodes.get(edge2!!))
                    Log.d(TAG, "edgeResult: edge -1 생성. " + i)
                } else {
                    Log.d(TAG, "edge1: null edge 추가 안 됨. i: " + i)
                }
            }
        } else {
            Log.d(TAG, "edgeResult: null임 ")
        }

        return graph
    }

    public override fun setRetrofitInit() {
        mRetrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mRetrofitAPI = mRetrofit.create(RetrofitAPI::class.java)
    }

    public override suspend fun callNodeList() {

        GlobalScope.async(Dispatchers.IO) {

            val call = mRetrofitAPI.nodeList
            call.enqueue(object : Callback<NodeVO?> {
                override fun onResponse(call: Call<NodeVO?>, response: Response<NodeVO?>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        nodeResult = result

                        Log.d(TAG, "CallNodeList : 성공 + 결과 : " + result.toString().substring(0, 5))
                        Log.d(
                            TAG,
                            "CallNodeList nodeResult : " + nodeResult.toString().substring(0, 7)
                        )

                    } else {
                        Log.d(TAG, "CallNodeList 연결: 실패")
                    }
                }

                override fun onFailure(call: Call<NodeVO?>, t: Throwable) {
                    Log.d(TAG, "CallNodeList onFailure: " + t.message)
                }
            })

        }.await()

    }

    public override suspend fun callEdgeList() {
        GlobalScope.async(Dispatchers.IO) {

            val call = mRetrofitAPI.edgeList
            call.enqueue(object : Callback<EdgeVO?> {
                override fun onResponse(call: Call<EdgeVO?>, response: Response<EdgeVO?>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        edgeResult = result

                        Log.d(
                            TAG,
                            "CallEdgeList onResponse: 성공 + 결과 : " + result.toString()
                                .substring(0, 5)
                        )
                        Log.d(
                            TAG,
                            "CallEdgeList onResponse_edgeResult : " + edgeResult.toString()
                                .substring(0, 7)
                        )

                    } else {
                        Log.d(TAG, "CallEdgeList onResponse: 실패")
                    }
                }

                override fun onFailure(call: Call<EdgeVO?>, t: Throwable) {
                    Log.d(TAG, "CallEdgeList onFailure: " + t.message)
                }
            })

        }.await()
    }
}