package com.example.basicmaterial3.data.network

import com.example.basicmaterial3.domain.AcromineRemoteDataSource
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val webService: WebService
) {
    suspend fun getAcromine(sf: String) = AcromineRemoteDataSource(webService, sf).invoke()
}