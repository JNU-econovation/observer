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
    private val TAG = "testNode"
    var postResult: PostResult? = null
    var nodeResult: NodeVO? = null
    var edgeResult: EdgeVO? = null

    public override fun setLayoutManager() {
        recyclerView.layoutManager = SugiyamaLayoutManager(this, SugiyamaConfiguration.Builder().build())
    }

    public override fun setEdgeDecoration() {
        recyclerView.addItemDecoration(SugiyamaArrowEdgeDecoration())
    }

    public override fun createGraph(): Graph {
        val graph = Graph()

        //예시로 노드, 간선 추가
        _nodes.add(Node("user"))
        _nodes.add(Node("frontweb"))
        _nodes.add(Node("backend"))
        _nodes.add(Node("backendWeb"))
        _nodes.add(Node("mysql"))
        _nodes.add(Node("Cloud"))

        graph.addEdge(_nodes.get(0), _nodes.get(1))
        graph.addEdge(_nodes.get(1), _nodes.get(5))
        graph.addEdge(_nodes.get(1), _nodes.get(2))
        graph.addEdge(_nodes.get(1), _nodes.get(3))
        graph.addEdge(_nodes.get(2), _nodes.get(4))
        graph.addEdge(_nodes.get(3), _nodes.get(4))

        //테스트코드
        if (postResult != null) {
            _nodes.add(Node(postResult.toString().substring(0, 5)))
            graph.addEdge(_nodes.get(3), _nodes.get(6))
            Log.d(TAG, "postResult: 추가됨.")
        } else {
            Log.d(TAG, "postResult: null임 ")
        }

        //node 생성
        if(nodeResult != null){
            for(i in 0 until nodeResult!!.list.size){
                _nodes.add(Node(nodeResult!!.list.get(i).name))
            }
            Log.d(TAG, "nodeResult: 추가됨.")
        } else {
            Log.d(TAG, "nodeResult: null임 ")
        }

        //edge 생성
        if(edgeResult != null){
            for(i in 0 until edgeResult!!.list.size){
                var edge1 : Int? = nodeResult?.list?.indexOfFirst { it.id ==  edgeResult!!.list.get(i).id1 }
                var edge2 : Int? = nodeResult?.list?.indexOfFirst { it.id ==  edgeResult!!.list.get(i).id2 }
                graph.addEdge(_nodes.get(edge1!!),_nodes.get(edge2!!))
            }
            Log.d(TAG, "edgeResult: 추가됨.")
        } else {
            Log.d(TAG, "edgeResult: null임 ")
        }


        return graph
    }

    public override fun setRetrofitInit() {
        mRetrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mRetrofitAPI = mRetrofit.create(RetrofitAPI::class.java)
    }

    public override suspend fun callTestList() {
        GlobalScope.async(Dispatchers.IO) {

            val call = mRetrofitAPI.getPosts("1")
            call.enqueue(object : Callback<PostResult?> {
                override fun onResponse(call: Call<PostResult?>, response: Response<PostResult?>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        postResult = result

                        Log.d(TAG, "onResponse: 성공 + 결과 : " + result.toString().substring(0, 5))
                        Log.d(TAG, "onResponse_postResult : " + postResult.toString().substring(0, 7))

                    } else {
                        Log.d(TAG, "onResponse: 실패")
                    }
                }

                override fun onFailure(call: Call<PostResult?>, t: Throwable) {
                    Log.d(TAG, "onFailure: " + t.message)
                }
            })

        }.await()
    }

    public override suspend fun callNodeList() {

        GlobalScope.async(Dispatchers.IO) {

            val call = mRetrofitAPI.nodeList
            call.enqueue(object : Callback<NodeVO?> {
                override fun onResponse(call: Call<NodeVO?>, response: Response<NodeVO?>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        nodeResult = result

                        Log.d(TAG, "onResponse: 성공 + 결과 : " + result.toString().substring(0, 5))
                        Log.d(TAG, "onResponse_nodeResult : " + nodeResult.toString().substring(0, 7))

                    } else {
                        Log.d(TAG, "onResponse: 실패")
                    }
                }

                override fun onFailure(call: Call<NodeVO?>, t: Throwable) {
                    Log.d(TAG, "onFailure: " + t.message)
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

                        Log.d(TAG, "onResponse: 성공 + 결과 : " + result.toString().substring(0, 5))
                        Log.d(TAG, "onResponse_edgeResult : " + edgeResult.toString().substring(0, 7))

                    } else {
                        Log.d(TAG, "onResponse: 실패")
                    }
                }

                override fun onFailure(call: Call<EdgeVO?>, t: Throwable) {
                    Log.d(TAG, "onFailure: " + t.message)
                }
            })

        }.await()
    }
}