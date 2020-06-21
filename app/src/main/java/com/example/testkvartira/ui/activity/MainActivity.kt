package com.example.testkvartira.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testkvartira.R
import com.example.testkvartira.ui.flatlist.FlatListAdapter
import com.example.testkvartira.ui.flatlist.FlatListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var columnCount = 1
    private lateinit var rvAdapter: FlatListAdapter
    private val viewModel by viewModels<FlatListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAdapter = FlatListAdapter(this)
        //Используем котлин синтетик для поиска view
        flat_listRV.apply {
            adapter = rvAdapter
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            //Подписываемся на изменения альбомов для передачи их в адаптер RecyclerView
            viewModel.flatList.observe(this@MainActivity, Observer {
                Log.d("WRITE_TO_ADAPTER", it.size.toString())
                Log.d("WRITE_TO_ADAPTER", it.toString())
                (adapter as FlatListAdapter).flatList = it
                progress_bar.visibility = View.INVISIBLE
            })
        }

        initSwipeRefresh()

    }

    private fun initSwipeRefresh() {
        flats_SWP.setOnRefreshListener {
            viewModel.loadFlats()
            flats_SWP.isRefreshing = false
        }
        flats_SWP.setColorSchemeColors(resources.getColor(R.color.colorAccent))
    }
}