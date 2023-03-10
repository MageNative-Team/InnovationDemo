package com.netcore.innovationdemo

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.netcore.innovationdemo.databinding.DataItemLayoutBinding
import javax.inject.Inject

class CompareDataAdapter @Inject constructor() :
    RecyclerView.Adapter<CompareDataAdapter.CompareDataViewHolder>() {
    lateinit var compareDataModel: JsonElement
    fun setData(compareDataModel: JsonElement) {
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
        holder.binding.productLink.text =
            (compareDataModel as JsonObject).get("data").asJsonArray.get(position).asJsonObject["title"].asString
        holder.binding.productPrice.text =
            "₹ " + (compareDataModel as JsonObject).get("data").asJsonArray.get(position).asJsonObject.get(
                "source_price"
            ).asString
        Glide.with(holder.binding.productPrice.context)
            .load(
                (compareDataModel as JsonObject).get("data").asJsonArray.get(position).asJsonObject.get(
                    "image"
                ).asString
            )
            .into(holder.binding.productImage)
        Glide.with(holder.binding.productPrice.context)
            .load(
                (compareDataModel as JsonObject).get("data").asJsonArray.get(position).asJsonObject.get(
                    "source_logo"
                ).asString
            )
            .into(holder.binding.sourceImage)
        holder.binding.sourceName.text =
            (compareDataModel as JsonObject).get("data").asJsonArray.get(position).asJsonObject.get(
                "source_name"
            ).asString
        holder.binding.rattingBar.rating =
            (compareDataModel as JsonObject).get("data").asJsonArray.get(position).asJsonObject.get(
                "source_rating"
            ).asString.toFloat()
        holder.binding.productContainer.setOnClickListener {
            val intent = Intent(it.context, Weblink::class.java)
            intent.putExtra(
                "link",
                (compareDataModel as JsonObject).get("data").asJsonArray.get(position).asJsonObject.get(
                    "url"
                ).asString
            )
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return compareDataModel.asJsonObject.get("data").asJsonArray.size()
    }

}