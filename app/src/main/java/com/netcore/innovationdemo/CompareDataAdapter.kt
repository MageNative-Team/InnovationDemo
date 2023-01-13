package com.netcore.innovationdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.netcore.innovationdemo.databinding.DataItemLayoutBinding
import com.netcore.innovationdemo.model.CompareDataModel
import com.netcore.innovationdemo.model.CompareResult
import javax.inject.Inject

class CompareDataAdapter @Inject constructor() :
    RecyclerView.Adapter<CompareDataAdapter.CompareDataViewHolder>() {
    lateinit var compareDataModel: List<CompareResult>
    fun setData(compareDataModel: List<CompareResult>) {
        this.compareDataModel = compareDataModel
    }

    class CompareDataViewHolder : RecyclerView.ViewHolder {
        var binding: DataItemLayoutBinding

        constructor(itemView: DataItemLayoutBinding) : super(itemView.root) {
            this.binding = itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompareDataViewHolder {
        val view = DataBindingUtil.inflate<DataItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.data_item_layout,
            parent,
            false
        )
        return CompareDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompareDataViewHolder, position: Int) {
        holder.binding.productLink.text = compareDataModel.get(position).title
        holder.binding.productPrice.text ="₹ "+compareDataModel.get(position).source_price
        Glide.with(holder.binding.productPrice.context)
            .load(compareDataModel.get(position).image)
            .into(holder.binding.productImage)
        Glide.with(holder.binding.productPrice.context)
            .load(compareDataModel.get(position).source_logo)
            .into(holder.binding.sourceImage)
        holder.binding.sourceName.text =compareDataModel.get(position).source_name
        holder.binding.rattingBar.rating =compareDataModel.get(position).source_rating.toFloat()
    }

    override fun getItemCount(): Int {
        return compareDataModel.size
    }

}