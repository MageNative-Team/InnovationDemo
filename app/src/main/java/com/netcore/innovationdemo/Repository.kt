package com.netcore.innovationdemo

import android.telecom.Call
import com.netcore.innovationdemo.model.CompareDataModel
import javax.inject.Inject

class Repository @Inject constructor(var apiService: ApiService) {

    fun getSearchResult(q: String): retrofit2.Call<CompareDataModel> {
        return apiService.getSearch(q)
    }
}