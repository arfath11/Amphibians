package com.example.amphibians.data

import com.example.amphibians.model.AmpData
import com.example.amphibians.network.AmpApiService

interface  AmpDataRepository{


    suspend fun  getAmpData(): List<AmpData>


}

class NetworkAmpDataRepository(private val  apmApiService: AmpApiService,
    ): AmpDataRepository{


    override suspend fun getAmpData(): List<AmpData>  =  apmApiService.getdata()





}