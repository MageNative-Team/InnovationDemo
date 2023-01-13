package com.netcore.innovationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.netcore.innovationdemo.databinding.ActivityMainBinding
import com.netcore.innovationdemo.databinding.CompareLayoutBinding
import com.netcore.innovationdemo.model.CompareDataModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    @Inject
    lateinit var compareDataAdapter: CompareDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        model.searchresult.observe(this, Observer { this.consumeResponse(it) })
        binding?.compareBut?.setOnClickListener {
            model.getSearchResult("Fitness Essential Oil Blend")
        }
    }

    private fun consumeResponse(it: CompareDataModel?) {
     compareDataAdapter.setData(it?.compareResultList!!)
        val bottomsheetDialog = BottomSheetDialog(this)
        val dialogBinding = DataBindingUtil.inflate<CompareLayoutBinding>(
            LayoutInflater.from(this),
            R.layout.compare_layout,
            null,
            false
        )
        bottomsheetDialog.setContentView(dialogBinding.root)
        dialogBinding.compairList.adapter = compareDataAdapter
        bottomsheetDialog.show()
    }

}