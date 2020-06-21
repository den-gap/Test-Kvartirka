package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class City(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("country_id")
    @Expose
    var countryId: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("name_genitive")
    @Expose
    var nameGenitive: String? = null,

    @SerializedName("name_prep")
    @Expose
    var namePrep: String? = null,

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates? = null,

    @SerializedName("yandex_maps_has_public")
    @Expose
    var yandexMapsHasPublic: Boolean? = null,

    @SerializedName("yandex_maps_has_detailed")
    @Expose
    var yandexMapsHasDetailed: Boolean? = null,

    @SerializedName("important")
    @Expose
    var important: Boolean? = null
)