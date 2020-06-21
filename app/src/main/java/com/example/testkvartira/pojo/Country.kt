package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("cities")
    @Expose
    var cities: List<City>? = null,

    @SerializedName("capital_id")
    @Expose
    var capitalId: Int? = null,

    @SerializedName("currency_default_id")
    @Expose
    var currencyDefaultId: Int? = null
)