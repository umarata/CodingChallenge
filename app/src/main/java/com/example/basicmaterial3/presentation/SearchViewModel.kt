package com.example.basicmaterial3.presentation

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicmaterial3.data.network.BaseRepository
import com.example.basicmaterial3.domain.AcromineResponse
import com.example.basicmaterial3.domain.printLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val baseRepository: BaseRepository) :
    ViewModel() {

    val acromineMutableLiveData =
        MutableLiveData<ArrayList<AcromineResponse.AcromineResponseItem>?>()
    val acromineLiveData: LiveData<ArrayList<AcromineResponse.AcromineResponseItem>?> =
        acromineMutableLiveData

    suspend fun getAcromine(sf: String) {
        try {
            val acromine = baseRepository.getAcromine(sf)
            printLog("acromine sf:$sf response\n$acromine", this)
            acromineMutableLiveData.postValue(acromine)
        } catch (e: java.lang.Exception) {
            printLog("error:$e", this)
        }
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