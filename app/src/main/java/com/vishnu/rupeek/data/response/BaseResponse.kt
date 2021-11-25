package com.vishnu.rupeek.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
)