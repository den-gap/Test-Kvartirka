package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Query(
    @SerializedName("filter")
    @Expose
    var filter: Filter? = null,

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
)