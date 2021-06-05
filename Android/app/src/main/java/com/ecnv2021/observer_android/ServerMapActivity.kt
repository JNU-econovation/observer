package com.ecnv2021.observer_android

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dev.bandb.graphview.AbstractGraphAdapter
import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

abstract class ServerMapActivity : AppCompatActivity() {
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var adapter: AbstractGraphAdapter<NodeViewHolder>
    private var currentNode: Node? = null

    protected abstract fun createGraph(): Graph
    protected abstract fun setLayoutManager()
    protected abstract fun setEdgeDecoration()
    protected abstract fun setRetrofitInit()
    protected abstract suspend fun callNodeList()
    protected abstract suspend fun callEdgeList()
    private var timerTask: Timer? = null
    private val TAG = "TTestServerMap"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
    }

    override fun onResume() {
        var graph: Graph? = null

        //api 서버 연결 init
        setRetrofitInit()

        timerTask = kotlin.concurrent.timer(period = 4000) {
            //서버에서 Node, Edge정보 가져오기

            runBlocking {
                Log.d(TAG, "ServerMapData timer starts")
                GlobalScope.launch {
                    callNodeList()
                    callEdgeList()
                }
            }

            runOnUiThread {
                //서버맵 UI 세팅
                Log.d(TAG, "ServerMapUI timer starts")
                Handler().postDelayed({
                    graph = createGraph()
                    recyclerView = findViewById(R.id.recycler)
                    setLayoutManager()
                    setEdgeDecoration()
                    setupGraphView(graph)
                }, 1000L)
            }
        }
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "ServerMap onPause()");
        timerTask?.cancel();
        super.onPause()
    }

    override fun onDestroy() {
        Log.i(TAG, "ServerMap onDestroy()");
        timerTask?.cancel();
        super.onDestroy()
    }

    private fun setupGraphView(graph: Graph?) {
        adapter = object : AbstractGraphAdapter<NodeViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.node, parent, false)
                return NodeViewHolder(view)
            }

            override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
                holder.textView.text = Objects.requireNonNull(getNodeData(position)).toString()
            }
        }
        adapter.submitGraph(graph)
        recyclerView.adapter = adapter

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected inner class NodeViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener {
                val nextIntent = Intent(this@ServerMapActivity, ScatterChartActivity::class.java)
                currentNode = adapter.getNode(bindingAdapterPosition)
                Log.d(TAG, adapter.getNodeData(bindingAdapterPosition)?.toString()!!)
                nextIntent.putExtra("name", adapter.getNodeData(bindingAdapterPosition)?.toString())
                startActivity(nextIntent)
            }
        }
    }

}