package com.vishnu.rupeek.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.ContextCompat.getSystemService
import com.vishnu.rupeek.data.response.BaseResponse


fun <T : Any> Call<T>.awaitResponse(
    onSuccess: (T?) -> Unit = {},
    onFailure: (String?) -> Unit = {}
) {

    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val r = response
            if (response.isSuccessful) {
                onSuccess.invoke(response.body())
            } else {
                val gson = Gson()
                val (error, message, status) = gson.fromJson(
                    response.errorBody()!!.charStream(),
                    BaseResponse::class.java
                ).also {
                    onFailure.invoke(it.message)
                }
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure.invoke(t.message)
        }
    })
}

fun dateFormat(dateStr: String): String {
    var format=""
    try {
        val sdfInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = sdfInput.parse(dateStr)
        val sdfOutput = SimpleDateFormat("MMM dd yyyy")
        sdfOutput.timeZone = TimeZone.getTimeZone("Etc/UTC")
        format = sdfOutput.format(date)

    }catch (e:Exception){

    }
    return format


}

