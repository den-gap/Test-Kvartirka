package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class RootCountry(
    @SerializedName("version")
    @Expose
    var version: Double? = null,

    @SerializedName("currencies")
    @Expose
    var currencies: List<Currency>? = null,

    @SerializedName("countries")
    @Expose
    var countries: List<Country>? = null,

//    @SerializedName("counters")
//    @Expose
//    var counters: Counters? = null,

    @SerializedName("starred_flats_ids")
    @Expose
    var starredFlatsIds: List<Int>? = null

//    @SerializedName("favorite_cities")
//    @Expose
//    var favoriteCities: List<FavoriteCity>? = null,

)