package com.example.coding_challenge.presentation

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coding_challenge.data.network.ApiError
import com.example.coding_challenge.data.network.ApiException
import com.example.coding_challenge.data.network.ApiSuccess
import com.example.coding_challenge.data.network.BaseRepository
import com.example.coding_challenge.domain.AcromineResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * This SearchViewModel is annotated by HiltViewModel so that it get the reference of repository by hilt that is injected in its constructor
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel() {

    /**
     * MutableLiveData instance created to hold the api response
     */
    val acromineMutableLiveData =
        MutableLiveData<ArrayList<AcromineResponse.AcromineResponseItem>?>()

    /**
     * LiveData created from acromineMutableLiveData for observing in the SearchFragment
     */
    val acromineLiveData: LiveData<ArrayList<AcromineResponse.AcromineResponseItem>?> =
        acromineMutableLiveData

    /**
     * acromineErrorMutableLiveData for holding the error message and code that we are showing to the user in the snackbar
     */
    val acromineErrorMutableLiveData = MutableLiveData<String>()

    /**
     * showProgress is a mutableLiveData created to toggle the visibility of progressBar in SearchFragment, for this databinding is used so no code for toggling the visibility
     * when its true so the progressbar will become visible otherwise hidden
     */
    val showProgress = MutableLiveData<Boolean>()

    /**
     * suspend function getAcromine(sf: String) created to fetch the details of acromine from the api by repository and it checks the response and modify the ui accordingly
     */
    suspend fun getAcromine(sf: String) {
        /**
         * changing the visibility of progressbar to visible
         */
        showProgress.postValue(true)
        when (val apiResult = baseRepository.getAcromine(sf)) {
            /**
             * for handling the success response
             */
            is ApiSuccess -> {
                /**
                 * checking if the data returned from empty then show snacknbar to the user like no results found
                 */
                if (apiResult.data.isEmpty()) {
                    acromineErrorMutableLiveData.postValue("No results found for \"$sf\"")
                }
                /**
                 * if the data returned from the api is not empty then passing it to mutable livedata so the view can get it
                 */
                else {
                    acromineMutableLiveData.postValue(apiResult.data)
                }
            }
            /**
             * for handling the error response
             */
            is ApiError -> {
                val response = "${apiResult.code} ${apiResult.message}"
                acromineErrorMutableLiveData.postValue(response)
            }
            /**
             * for handling the exception
             */
            is ApiException -> {
                /**
                 * checking if unable to reach host to show no internet, although it could be wrong if there is any issue on server
                 */
                if (apiResult.e is UnknownHostException) {
                    acromineErrorMutableLiveData.postValue("Unable to reach the server, please check your internet connection")
                } else {
                    val response = "${apiResult.e.message}"
                    acromineErrorMutableLiveData.postValue(response)
                }
            }
        }
        /**
         * changing the visibility of progressbar to gone
         */
        showProgress.postValue(false)
    }

    /**
     * mutable livedata created for holding the value entered in searchView
     */
    val queryMutableLiveData = MutableLiveData<String?>()

    /**
     * QueryTextListener created to observe the values entered in the searchView
     */
    val onQueryTextChangeListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            /**
             * posting the value present in searchView when user pressed the submit button in keyboard
             */
            queryMutableLiveData.postValue(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            /**
             * if the user cleared the searchView then posting the empty value to query mutable livedata so the list gets cleared
             */
            if (newText.isNullOrEmpty()) {
                queryMutableLiveData.postValue(newText)
            }
            return false
        }
    }
}