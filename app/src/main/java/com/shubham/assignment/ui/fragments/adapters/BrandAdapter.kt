package com.shubham.assignment.ui.fragments.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.assignment.data.Brand
import com.shubham.assignment.databinding.ItemBrandBinding
import com.shubham.assignment.utils.loadImage

class BrandAdapter(
    private val data: MutableList<Brand>,
) : RecyclerView.Adapter<BrandAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemBrandBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemBrandBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data[position]
        holder.binding.apply {
            Log.e("codeChecking", "onBindViewHolder: ${data.image}")
            data.image?.let {
                brandImage.loadImage(brandImage.context, it)
            }
            root.setOnClickListener {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.website))
                    root.context.startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}
