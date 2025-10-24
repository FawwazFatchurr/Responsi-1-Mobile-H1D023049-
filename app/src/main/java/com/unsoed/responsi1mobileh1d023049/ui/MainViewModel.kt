package com.unsoed.responsi1mobileh1d023049.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsoed.responsi1mobileh1d023049.data.TeamResponse
import com.unsoed.responsi1mobileh1d023049.network.ApiClient
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _teamData = MutableLiveData<TeamResponse>()
    val teamData: LiveData<TeamResponse> = _teamData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchTeamDetails()
    }

    private fun fetchTeamDetails() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getTeamDetail()
                if (response.isSuccessful && response.body() != null) {
                    _teamData.postValue(response.body())
                }
            } catch (e: Exception) {
                // Tangani error
            } finally {
                _isLoading.value = false
            }
        }
    }
}