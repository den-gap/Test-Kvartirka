package com.example.testkvartira.ui.flatlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkvartira.api.KvartirkaApiFactory
import com.example.testkvartira.pojo.Flat
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FlatListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var flatList: MutableLiveData<List<Flat>> = MutableLiveData()

    init {
        loadFlats()
    }

    fun loadFlats() {
        val disposable = KvartirkaApiFactory.apiService.getFlats()
            .map { it.flats }
            .subscribeOn(Schedulers.io())
            .subscribe({
                it?.let { flats ->
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