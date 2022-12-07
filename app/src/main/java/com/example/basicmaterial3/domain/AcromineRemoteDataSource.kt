package com.example.basicmaterial3.domain

import com.example.basicmaterial3.data.network.ApiResult
import com.example.basicmaterial3.data.network.WebService
import com.example.basicmaterial3.data.network.handleApi

class AcromineRemoteDataSource(
    private val webService: WebService, private val sf: String
) {
    suspend operator fun invoke(): ApiResult<ArrayList<AcromineResponse.AcromineResponseItem>> =
        handleApi {
            webService.getAcromine(sf)
        }
}