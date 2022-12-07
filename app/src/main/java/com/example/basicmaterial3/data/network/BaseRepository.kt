package com.example.basicmaterial3.data.network

import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val webService: WebService
) {
    suspend fun getAcromine(sf: String) = webService.getAcromine(sf)
}