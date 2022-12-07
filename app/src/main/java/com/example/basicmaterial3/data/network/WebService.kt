package com.example.basicmaterial3.data.network

import com.example.basicmaterial3.domain.AcromineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    /**
     * This function getAcromine(@Query("sf") sf: String?) is calling an api by retrofit to fetch the details of acromine that is entered by user in searchview and it is passing the entered sf into query param.
     * Here is a sample request for sf=HMM
     * http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM
     */
    @GET("software/acromine/dictionary.py")
    suspend fun getAcromine(@Query("sf") sf: String?): Response<ArrayList<AcromineResponse.AcromineResponseItem>>

}

