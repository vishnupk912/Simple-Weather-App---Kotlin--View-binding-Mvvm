package com.vishnu.stackoverlfow.data

import android.telecom.Call
import com.google.gson.annotations.SerializedName
import com.vishnu.rupeek.data.response.WeatherResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("v2/5d3a99ed2f0000bac16ec13a")
    fun getdata(

    ): retrofit2.Call<WeatherResponse>
}