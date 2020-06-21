package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class PhotoDefault(
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null,

    @SerializedName("width")
    @Expose
    var width: Int? = null,

    @SerializedName("height")
    @Expose
    var height: Int? = null
)