package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Photo(
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null
)