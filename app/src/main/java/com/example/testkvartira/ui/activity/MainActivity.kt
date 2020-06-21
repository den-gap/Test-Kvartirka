package com.example.testkvartira.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testkvartira.R
import com.example.testkvartira.pojo.City
import com.example.testkvartira.ui.flatlist.FlatListAdapter
import com.example.testkvartira.ui.flatlist.FlatListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var columnCount = 1
    private lateinit var rvAdapter: FlatListAdapter
    private lateinit var selectedCity: City
    private val viewModel by viewModels<FlatListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()

        rvAdapter = FlatListAdapter(this)
        flat_listRV.apply {
            adapter = rvAdapter
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            viewModel.flatList.observe(this@MainActivity, Observer {
                Log.d("WRITE_TO_ADAPTER", it.size.toString())
                Log.d("WRITE_TO_ADAPTER", it.toString())
                (adapter as FlatListAdapter).flatList = it
                progress_bar.visibility = View.INVISIBLE
                flat_listRV.visibility = View.VISIBLE
            })
        }

        initSwipeRefresh()

    }

    private fun initSpinner() {
        viewModel.cityList.observe(this, Observer { cities ->
            val cityPosition =
                cities.indexOf(cities.findLast { it.name == viewModel.defaultCityName })
            citiesSPN.apply {
                setTitle(resources.getString(R.string.choose_city))
                adapter = ArrayAdapter<String>(
                    this@MainActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    cities.map { it.name })
                setSelection(cityPosition)
            }
        })
        citiesSPN.onItemSelectedListener = this@MainActivity
    }

    private fun initSwipeRefresh() {
        flats_SWP.setOnRefreshListener {
            viewModel.loadFlats(selectedCity.id!!)
            flats_SWP.isRefreshing = false
        }
        flats_SWP.setColorSchemeColors(resources.getColor(R.color.colorAccent))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val cityName = parent?.getItemAtPosition(position).toString()
        viewModel.cityList.observe(this@MainActivity, Observer { cities ->
            selectedCity = cities.first { it.name == cityName }
        })

        viewModel.loadFlats(selectedCity.id!!)
        progress_bar.visibility = View.VISIBLE
        flat_listRV.visibility = View.INVISIBLE
    }
}