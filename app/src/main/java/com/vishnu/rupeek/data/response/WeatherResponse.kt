package com.vishnu.rupeek.data.response

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("rain")
        val rain: Int,
        @SerializedName("temp")
        val temp: Int,
        @SerializedName("time")
        val time: Int,
        @SerializedName("wind")
        val wind: Int
    )
}