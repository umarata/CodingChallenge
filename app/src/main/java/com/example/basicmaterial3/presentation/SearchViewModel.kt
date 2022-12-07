package com.example.basicmaterial3.presentation

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basicmaterial3.data.network.ApiError
import com.example.basicmaterial3.data.network.ApiException
import com.example.basicmaterial3.data.network.ApiSuccess
import com.example.basicmaterial3.data.network.BaseRepository
import com.example.basicmaterial3.domain.AcromineResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel() {

    val acromineMutableLiveData =
        MutableLiveData<ArrayList<AcromineResponse.AcromineResponseItem>?>()
    val acromineLiveData: LiveData<ArrayList<AcromineResponse.AcromineResponseItem>?> =
        acromineMutableLiveData

    val acromineErrorMutableLiveData = MutableLiveData<String>()

    val showProgress = MutableLiveData<Boolean>()

    suspend fun getAcromine(sf: String) {
        showProgress.postValue(true)
        when (val apiResult = baseRepository.getAcromine(sf)) {
            is ApiSuccess -> {
                if (apiResult.data.isEmpty()) {
                    acromineErrorMutableLiveData.postValue("No results found for \"$sf\"")
                } else {
                    acromineMutableLiveData.postValue(apiResult.data)
                }
            }
            is ApiError -> {
                val response = "${apiResult.code} ${apiResult.message}"
                acromineErrorMutableLiveData.postValue(response)
            }
            is ApiException -> {
                val response = "${apiResult.e.message}"
                acromineErrorMutableLiveData.postValue(response)
            }
        }
        showProgress.postValue(false)
    }

    val queryMutableLiveData = MutableLiveData<String?>()

    val onQueryTextChangeListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            queryMutableLiveData.postValue(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText.isNullOrEmpty()){
            queryMutableLiveData.postValue(newText)
            }
            return false
        }
    }
}