package com.example.testkvartira.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Flat(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("city_id")
    @Expose
    var cityId: Int? = null,

    @SerializedName("building_type")
    @Expose
    var buildingType: String? = null,

    @SerializedName("metro")
    @Expose
    var metro: String? = null,

    @SerializedName("rooms")
    @Expose
    var rooms: Int? = null,

    @SerializedName("address")
    @Expose
    var address: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("prices")
    @Expose
    var prices: Prices? = null,

    @SerializedName("photo_default")
    @Expose
    var photoDefault: PhotoDefault? = null,

    @SerializedName("contacts")
    @Expose
    var contacts: Contacts? = null,

    @SerializedName("photos")
    @Expose
    var photos: List<Photo>? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("description_full")
    @Expose
    var descriptionFull: String? = null,

    @SerializedName("badges")
    @Expose
    var badges: Badges? = null,

    @SerializedName("distance_from_point_text")
    @Expose
    var distanceFromPointText: String? = null
)