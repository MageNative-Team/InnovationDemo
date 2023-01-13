package com.netcore.innovationdemo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.netcore.innovationdemo.model.CompareDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var repository: Repository) : ViewModel() {
    private  val TAG = "MainViewModel"
    var searchresult =MutableLiveData<CompareDataModel>()
    fun getSearchResult(q:String){
        var call =repository.getSearchResult(q)
        call.enqueue(object :Callback<CompareDataModel> {
            override fun onResponse(call: Call<CompareDataModel>, response: Response<CompareDataModel>) {
                Log.d(TAG, "onResponse: "+response.body())
                searchresult.value =response.body()
            }

            override fun onFailure(call: Call<CompareDataModel>, t: Throwable) {
                Log.d(TAG, "onFailure: "+t.message)
            }

        })
    }
}