package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Phone (
    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("normalized")
    @Expose
    var normalized: String? = null
)