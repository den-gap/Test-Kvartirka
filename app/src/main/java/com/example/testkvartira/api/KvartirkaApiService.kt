package com.example.testkvartira.api

import com.example.testkvartira.pojo.RootCountry
import com.example.testkvartira.pojo.RootFlats
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface KvartirkaApiService {

    @GET(GET_FLATS)
    fun getFlats(
        @Query(OFFSET) offset: Int = 0,
        @Query(CURRENCY_ID) currency_id: Int = 643,
        @Query(CITY_ID) city_id: Int
    ): Observable<RootFlats>

    @GET(GET_FLATS)
    fun getFlats(
        @Query(OFFSET) offset: Int = 0,
        @Query(CURRENCY_ID) currency_id: Int = 643,
        @Query(CITY_ID) city_id: Int = 18,
        @Query(POINT_LNG) point_lng: Double,
        @Query(POINT_LAT) point_lat: Double
    ): Observable<RootFlats>

    @GET(GET_FLATS)
    fun getFlatById(
        @Query(FLAT_IDS) flatIds: String
    ): Observable<RootFlats>

    @GET(GET_COUNTRY)
    fun getCountry(): Observable<RootCountry>

    companion object {
        private const val GET_FLATS = "flats"
        private const val GET_COUNTRY = "country"
        private const val OFFSET = "offset"
        private const val DEVICE_SCREEN_WIDTH = "device_screen_width"
        private const val CURRENCY_ID = "currency_id"
        private const val TYPE = "type"
        private const val PRICE = "price"
        private const val SLEEPING_PLACES = "sleeping_places"
        private const val INTERNET = "internet"
        private const val REPORT_DOCUMENTS = "report_documents"
        private const val POINT_LNG = "point_lng"
        private const val POINT_LAT = "point_lat"
        private const val CITY_ID = "city_id"
        private const val FLAT_IDS = "flat_ids"
    }
}