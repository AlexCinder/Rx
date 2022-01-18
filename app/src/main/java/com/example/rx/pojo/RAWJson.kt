package com.example.rx.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class RAWJson(

    @SerializedName("RAW")
    @Expose
    val rawJson: JsonObject
)



