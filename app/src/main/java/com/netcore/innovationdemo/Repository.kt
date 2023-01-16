package com.netcore.innovationdemo

import com.google.gson.JsonElement
import javax.inject.Inject

class Repository @Inject constructor(var apiService: ApiService) {

    fun getSearchResult(q: String): retrofit2.Call<JsonElement> {
        return apiService.getSearch(q)
    }
}