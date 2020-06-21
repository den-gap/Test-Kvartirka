package com.example.testkvartira.ui.flat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkvartira.api.KvartirkaApiFactory
import com.example.testkvartira.pojo.Flat
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FlatViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var flat: MutableLiveData<Flat> = MutableLiveData()

    fun loadFlat(id: Int) {
        val stringId = "[$id]"
        val disposable = KvartirkaApiFactory.apiService.getFlatById(stringId)
            .map { it.flats }
            .map { it?.first() }
            .subscribeOn(Schedulers.io())
            .subscribe({
                it?.let { loadedFlat ->
                    Log.d("LOADING_ONE_FLAT", loadedFlat.toString())
                    flat.postValue(loadedFlat)
                }
            }, {
                Log.d("LOADING_ONE_FLAT", it.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}