package com.example.coding_challenge.domain

import com.example.coding_challenge.data.network.ApiResult
import com.example.coding_challenge.data.network.WebService
import com.example.coding_challenge.data.network.handleApi

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