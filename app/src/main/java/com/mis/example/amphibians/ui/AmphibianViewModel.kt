package com.mis.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mis.example.amphibians.network.Amphibian
import com.mis.example.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    // 6- DONE: Create properties to represent MutableLiveData and LiveData for the API status
    private var _apiStatus = MutableLiveData<AmphibianApiStatus>()
    val apiStatus: LiveData<AmphibianApiStatus> get() = _apiStatus

    // 7- DONE: Create properties to represent MutableLiveData and LiveData for a list of amphibian objects
    private var _amphibiansList = MutableLiveData<List<Amphibian>>()
    val amphibiansList: LiveData<List<Amphibian>> get() = _amphibiansList

    // 8- DONE: Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //  This will be used to display the details of an amphibian when a list item is clicked
    private var _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> get() = _amphibian

    // 9- DONE: Create a function that gets a list of amphibians from the api service and sets the
    //  status via a Coroutine
    fun getAmphibians() {
        _apiStatus.value = AmphibianApiStatus.LOADING
        viewModelScope.launch {
            try {
                _amphibiansList.value = AmphibiansApi.service.getAmphibiansList()
                _apiStatus.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = AmphibianApiStatus.ERROR
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        // 10- DONE: Set the amphibian object
        _amphibian.value = amphibian
    }
}
