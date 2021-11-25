package com.vishnu.rupeek

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.vishnu.rupeek.data.repo.Repository
import com.vishnu.rupeek.data.response.WeatherResponse
import com.vishnu.stackoverlfow.data.ApiCallStatus
import com.vishnu.stackoverlfow.data.ApiMapper


class HomeViewModel(val app: Application) : AndroidViewModel(app) {
    var weatherResponseStatus = MutableLiveData<ApiMapper<WeatherResponse>>()

    val repository = Repository(app)


    fun getData() {
        weatherResponseStatus.value = ApiMapper(ApiCallStatus.LOADING, null, null)

        repository.getData() { status, message, result ->
            when (status) {
                true -> {
                    weatherResponseStatus.value = ApiMapper(ApiCallStatus.SUCCESS, result, null)

                }
                false -> {
                    weatherResponseStatus.value = ApiMapper(ApiCallStatus.SUCCESS, result, null)

                }

            }
        }

    }
}