package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Prices(
    @SerializedName("day")
    @Expose
    var day: Int? = null,

    @SerializedName("night")
    @Expose
    var night: Int? = null,

    @SerializedName("hour")
    @Expose
    var hour: Int? = null
)