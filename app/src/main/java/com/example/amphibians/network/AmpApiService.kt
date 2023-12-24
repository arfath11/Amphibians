package com.example.amphibians.network

import com.example.amphibians.model.AmpData
import retrofit2.http.GET

interface AmpApiService

{


    @GET("amphibians?authuser=1")
    suspend  fun getdata(): List<AmpData>
}