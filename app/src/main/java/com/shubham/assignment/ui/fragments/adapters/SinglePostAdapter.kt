package com.shubham.assignment.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shubham.assignment.R
import com.shubham.assignment.communication.IPostDetailListener
import com.shubham.assignment.data.Post
import com.shubham.assignment.databinding.ItemHashtagBinding
import com.shubham.assignment.utils.getTotalDaysOfPostPosted

class SinglePostListAdapter(
    private val data: MutableList<Post>,
    private val listener: IPostDetailListener
) : RecyclerView.Adapter<SinglePostListAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemHashtagBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemHashtagBinding.inflate(
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
            Glide.with(root.context)
                .load(root.context.getString(R.string.image_loader, data.muxPlaybackId))
                .placeholder(R.drawable.ic_launcher_background).into(hashTagImage)
            Glide.with(root.context).load(data.profilePic).into(profileImage)
            profileName.text = data.handle
            orderDescription.text = data.text
            orderPostDay.text = root.context.getString(
                R.string.days,
                data.liveAt.getTotalDaysOfPostPosted().toString()
            )
            root.setOnClickListener {
                listener.postIsClicked(data)
            }
        }
    }
}
