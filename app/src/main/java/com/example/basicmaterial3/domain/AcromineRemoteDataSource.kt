package com.example.basicmaterial3.domain

import com.example.basicmaterial3.data.network.ApiResult
import com.example.basicmaterial3.data.network.WebService
import com.example.basicmaterial3.data.network.handleApi

/**
 * This class AcromineRemoteDataSource is acting as a remote datasource for repository and it calls a function webService.getAcromine(sf) from WebService
 */
class AcromineRemoteDataSource(
    private val webService: WebService, private val sf: String
) {
    suspend operator fun invoke(): ApiResult<ArrayList<AcromineResponse.AcromineResponseItem>> =
        handleApi {
            webService.getAcromine(sf)
        }
}