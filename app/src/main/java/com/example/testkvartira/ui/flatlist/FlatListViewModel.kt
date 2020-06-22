package com.example.testkvartira.ui.flatlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkvartira.api.KvartirkaApiFactory
import com.example.testkvartira.pojo.City
import com.example.testkvartira.pojo.Flat
import com.example.testkvartira.util.Countries
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FlatListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var flatList: MutableLiveData<List<Flat>> = MutableLiveData()
    var cityList: MutableLiveData<List<City>> = MutableLiveData()

    var userCityId = 18

    val defaultLon = 37.6082298810962
    val defaultLat = 55.7625506743728


    init {
        loadCities(Countries.RUSSIA)
    }

    private fun loadCities(countryId: Int) {
        val disposable = KvartirkaApiFactory.apiService.getCountry()
            .map { it.countries }
            .subscribeOn(Schedulers.io())
            .subscribe({
                it?.let { countries ->
                    val cities = countries.first { it.id == countryId }.cities
                    Log.d("LOADING_CITIES", cities?.size.toString())
                    cityList.postValue(cities)
                }
            }, {
                Log.d("LOADING_CITIES", it.message)
            })
        compositeDisposable.add(disposable)
    }

    fun initUserCity(lat: Double, lon: Double) {
        val disposable = KvartirkaApiFactory.apiService.getFlats(
            city_id = userCityId,
            point_lat = lat,
            point_lng = lon
        )
            .map { it.flats }
            .subscribeOn(Schedulers.io())
            .subscribe({
                it?.let { flats ->
                    Log.d("LOADING_FLATS", flats.size.toString())
                    userCityId = flats.first().cityId ?: 18
                    Log.d("LOADING_FLATS", "CityId: $userCityId")
                    flatList.postValue(flats)
                }
            }, {
                Log.d("LOADING_FLATS", it.message)
            })
        compositeDisposable.add(disposable)
    }

    fun loadFlats(
        cityId: Int,
        lat: Double,
        lon: Double
    ) {
        Log.d("LOADING_FLATS", "Begin loading...")
        val disposable =
            if (lat != defaultLat && lon != defaultLon && cityId == userCityId) {
                KvartirkaApiFactory.apiService.getFlats(
                    city_id = cityId,
                    point_lat = lat,
                    point_lng = lon
                )
            } else {
                KvartirkaApiFactory.apiService.getFlats(city_id = cityId)
            }
                .map { it.flats }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it?.let { flats ->
                        Log.d("LOADING_FLATS", flats.size.toString())
                        flatList.postValue(flats)
                    }
                }, {
                    Log.d("LOADING_FLATS", it.message)
                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}