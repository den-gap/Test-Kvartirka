package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Meta(
    @SerializedName("offset")
    @Expose
    var offset: Int? = null,

    @SerializedName("nearest")
    @Expose
    var nearest: Int? = null,

    @SerializedName("limit")
    @Expose
    var limit: Int? = null
)