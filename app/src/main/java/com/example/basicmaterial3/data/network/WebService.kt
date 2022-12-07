package com.example.basicmaterial3.data.network

import com.example.basicmaterial3.domain.AcromineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    //http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM
    @GET("software/acromine/dictionary.py")
    suspend fun getAcromine(@Query("sf") sf: String?): Response<ArrayList<AcromineResponse.AcromineResponseItem>>

}

class AcromineRemoteDataSource(
    private val webService: WebService, private val sf: String
) {
    suspend operator fun invoke(): ApiResult<ArrayList<AcromineResponse.AcromineResponseItem>> =
        handleApi {
            webService.getAcromine(sf)
        }
}