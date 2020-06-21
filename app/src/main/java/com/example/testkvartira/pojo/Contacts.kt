package com.example.testkvartira.pojo

import android.provider.ContactsContract.CommonDataKinds.Phone

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Contacts(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("flats_count")
    @Expose
    var flatsCount: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("skype")
    @Expose
    var skype: String? = null,

    @SerializedName("vk_profile")
    @Expose
    var vkProfile: String? = null,

    @SerializedName("send_booking_request_allowed")
    @Expose
    var sendBookingRequestAllowed: Boolean? = null,

    @SerializedName("show_prepayment_warning")
    @Expose
    var showPrepaymentWarning: Boolean? = null,

    @SerializedName("phones")
    @Expose
    var phones: List<Phone>? = null
)