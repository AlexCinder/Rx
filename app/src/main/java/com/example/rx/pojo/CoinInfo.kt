package com.example.rx.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo
)

data class CoinInfo(
    @SerializedName("Id")
    @Expose
    val id: String,

    @SerializedName("Name")
    @Expose
    val name: String,

    @SerializedName("FullName")
    @Expose
    val fullName: String,

    @SerializedName("ImageUrl")
    @Expose
    val url: String

)

data class Data(

    @SerializedName("Data")
    @Expose
    val list: List<Datum>
)