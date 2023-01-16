package com.netcore.innovationdemo

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.JsonElement
import com.netcore.innovationdemo.databinding.ActivityMainBinding
import com.netcore.innovationdemo.databinding.CompareLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    @Inject
    lateinit var compareDataAdapter: CompareDataAdapter

    lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        model.searchresult.observe(this, Observer { this.consumeResponse(it) })
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        binding?.compareBut?.setOnClickListener {
            progressDialog.show()
            model.getSearchResult("Fitness Essential Oil Blend")
        }
    }

    private fun consumeResponse(it: JsonElement?) {

     compareDataAdapter.setData(it!!)
        val bottomsheetDialog = BottomSheetDialog(this,R.style.WideDialog)
        bottomsheetDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val dialogBinding = DataBindingUtil.inflate<CompareLayoutBinding>(
            LayoutInflater.from(this),
            R.layout.compare_layout,
            null,
            false
        )
        bottomsheetDialog.setContentView(dialogBinding.root)
        dialogBinding.compairList.adapter = compareDataAdapter
        bottomsheetDialog.show()
        progressDialog.dismiss()
    }

}