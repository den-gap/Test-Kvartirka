package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class RootFlats (
    @SerializedName("version")
    @Expose
    var version: Double? = null,

    @SerializedName("query")
    @Expose
    var query: Query? = null,

    @SerializedName("currency")
    @Expose
    var currency: Currency? = null,

    @SerializedName("city_id")
    @Expose
    var cityId: Int? = null,

    @SerializedName("flats")
    @Expose
    var flats: List<Flat>? = null

    )