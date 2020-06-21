package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Point(
    @SerializedName("point_lat")
    @Expose
    var pointLat: Double? = null,

    @SerializedName("point_lng")
    @Expose
    var pointLng: Double? = null
)