package com.vishnu.rupeek.data.repo

import android.content.Context
import com.vishnu.rupeek.data.response.WeatherResponse
import com.vishnu.rupeek.utils.awaitResponse
import com.vishnu.stackoverlfow.data.ApiManger


class Repository(val context: Context) {
    private val api = ApiManger(context).api


    fun getData(
        onApiCallback: (status: Boolean, message: String?, result: WeatherResponse?) -> Unit
    ) {
        api.getdata().awaitResponse(
            onFailure = {
                onApiCallback(false, it, null)
            }, onSuccess = {
                onApiCallback(true, null, it)

            }
        )
    }
}