package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Badges(
    @SerializedName("owner_confirmed")
    @Expose
    var ownerConfirmed: Boolean? = null,

    @SerializedName("more_than_year")
    @Expose
    var moreThanYear: Boolean? = null,

    @SerializedName("payed")
    @Expose
    var payed: Int? = null
)