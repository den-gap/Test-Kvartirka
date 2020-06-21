package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Currency(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("label")
    @Expose
    var label: String? = null
)