package com.example.testkvartira.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testkvartira.R
import com.example.testkvartira.pojo.City
import com.example.testkvartira.ui.flatlist.FlatListAdapter
import com.example.testkvartira.ui.flatlist.FlatListViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 222
    }

    private var columnCount = 1
    private lateinit var rvAdapter: FlatListAdapter
    private val viewModel by viewModels<FlatListViewModel>()

    private lateinit var selectedCity: City
    private var locationLat: Double = 55.7625506743728
    private var locationLon: Double = 37.6082298810962

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLocation()
        viewModel.cityList.observe(this@MainActivity, Observer {
            initSpinner(it)
        })
        initSwipeRefresh()

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
    }

    private fun initLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                val location = locationResult.lastLocation
                locationLon = location?.longitude ?: viewModel.defaultLon
                Log.d("LOCATION_CALLBACK", "Longitude: $locationLon")
                locationLat = location?.latitude ?: viewModel.defaultLat
                Log.d("LOCATION_CALLBACK", "Latitude: $locationLat")
                viewModel.initUserCity(locationLat, locationLon)
            }
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                locationLon = location?.longitude ?: viewModel.defaultLon
                Log.d("LOCATION_LAST", "Longitude: $locationLon")
                locationLat = location?.latitude ?: viewModel.defaultLat
                Log.d("LOCATION_LAST", "Latitude: $locationLat")
                viewModel.initUserCity(locationLat, locationLon)
            }
    }

    private fun initSpinner(cities: List<City>) {
        selectedCity = cities.first { city -> city.id == viewModel.userCityId }
        citiesSPN.apply {
            setTitle(resources.getString(R.string.choose_city))
            adapter = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                cities.map { it.name })
            setSelection(
                cities.indexOf(
                    selectedCity
                )
            )
        }
        citiesSPN.onItemSelectedListener = this@MainActivity
    }

    private fun initSwipeRefresh() {
        flats_SWP.setOnRefreshListener {
            viewModel.loadFlats(selectedCity.id!!, locationLat, locationLon)
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
        viewModel.loadFlats(selectedCity.id!!, locationLat, locationLon)
        progress_bar.visibility = View.VISIBLE
        flat_listRV.visibility = View.INVISIBLE
    }
}