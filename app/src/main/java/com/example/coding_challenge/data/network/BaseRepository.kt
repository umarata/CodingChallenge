package com.example.coding_challenge.data.network

import com.example.coding_challenge.domain.AcromineRemoteDataSource
import javax.inject.Inject

/**
 * This BaseRepository provides the getAcromine(sf: String) function for fetching the details of acromine from api, in this repository webService: WebService is injected in constructor
 */
class BaseRepository @Inject constructor(
    private val webService: WebService
) {
    suspend fun getAcromine(sf: String) = AcromineRemoteDataSource(webService, sf).invoke()
}