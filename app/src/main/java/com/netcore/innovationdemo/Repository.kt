package com.netcore.innovationdemo

import android.telecom.Call
import com.google.gson.JsonElement
import com.netcore.innovationdemo.model.CompareDataModel
import org.json.JSONObject
import javax.inject.Inject

class Repository @Inject constructor(var apiService: ApiService) {

    fun getSearchResult(q: String): retrofit2.Call<JsonElement> {
        return apiService.getSearch(q)
    }
}