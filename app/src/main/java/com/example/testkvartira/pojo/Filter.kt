package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Filter(
    @SerializedName("currency_id")
    @Expose
    var currencyId: Int? = null,

    @SerializedName("city_id")
    @Expose
    var cityId: Int? = null,

    @SerializedName("price")
    @Expose
    var price: Int? = null,

    @SerializedName("building_type")
    @Expose
    var buildingType: List<String>? = null,

    @SerializedName("sleeping_places")
    @Expose
    var sleepingPlaces: Int? = null,

    @SerializedName("point")
    @Expose
    var point: Point? = null,

    @SerializedName("addons")
    @Expose
    var addons: List<String>? = null,

    @SerializedName("app_user_id")
    @Expose
    var appUserId: Any? = null
)