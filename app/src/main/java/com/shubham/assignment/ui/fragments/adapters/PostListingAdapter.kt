package com.shubham.assignment.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubham.assignment.R
import com.shubham.assignment.communication.IPostDetailListener
import com.shubham.assignment.communication.ISeePostListener
import com.shubham.assignment.data.Post
import com.shubham.assignment.databinding.ItemHomeListingBinding

class PostListingAdapter(private val data: MutableList<MutableList<Post>> , private val listener : ISeePostListener , private val postDetailScreenListener : IPostDetailListener) : RecyclerView.Adapter<PostListingAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding : ItemHomeListingBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemHomeListingBinding.inflate(LayoutInflater.from(parent.context) , parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data[position]
        holder.binding.apply {

            topDivider.visibility = if(position == 0) View.INVISIBLE else View.VISIBLE
            hashTagName.text = root.context.getString(R.string.hashtag_text , data[0].tagText)
            val adapter = SinglePostListAdapter(data , postDetailScreenListener)
            postAdapters.layoutManager = LinearLayoutManager(root.context , LinearLayoutManager.HORIZONTAL , false)
            postAdapters.adapter = adapter
            seeAll.setOnClickListener { listener.seeAllPostClicked(position , data) }
        }
    }
}
