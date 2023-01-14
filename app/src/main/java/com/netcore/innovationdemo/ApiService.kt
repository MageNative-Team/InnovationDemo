package com.netcore.innovationdemo

import com.google.gson.JsonElement
import com.netcore.innovationdemo.model.CompareDataModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/search.php")
    fun getSearch(@Query("q") q: String): Call<JsonElement>
}