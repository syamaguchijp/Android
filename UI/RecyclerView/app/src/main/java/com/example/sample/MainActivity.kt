package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
        // SwipeRefreshLayout
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener {
            println("OnRefresh")
            startNetworking(recyclerView, swipeRefreshLayout)
        }

        startNetworking(recyclerView, swipeRefreshLayout)
    }

    private fun startNetworking(recyclerView: RecyclerView, swipeRefreshLayout: SwipeRefreshLayout) {

        val query = mutableMapOf<String, String>("per_page" to "50")
        NetworkManager().get("https://qiita.com/api/v2/items", query,
            { statusCode: Int, responseBody: String ->

            Logging.d("complete!!! ${responseBody}")

            if (statusCode == 200) {
                val json = Json { ignoreUnknownKeys = true }
                val rowDataList = json.decodeFromString<List<RowData>>(responseBody)
                val viewAdapter = MyViewAdapter(this,
                    object : MyViewAdapter.ItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            Logging.d("tapped! ${position}")
                        }
                    }, rowDataList)
                recyclerView.apply {
                    adapter = viewAdapter
                }
            }

            swipeRefreshLayout.isRefreshing = false
        })
    }
}