package com.shubham.assignment.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.shubham.assignment.R
import com.shubham.assignment.communication.IPostDetailListener
import com.shubham.assignment.data.Post
import com.shubham.assignment.data.SinglePostData
import com.shubham.assignment.databinding.FragmentDetailBinding
import com.shubham.assignment.ui.fragments.adapters.SinglePostListAdapter
import com.shubham.assignment.utils.Constants
import com.shubham.assignment.utils.Constants.Companion.detailScreenCurrentPost
import com.shubham.assignment.utils.navigate


class DetailFragment : Fragment() , IPostDetailListener {

    private val mBinding : FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(detailScreenCurrentPost == null){
             val args : DetailFragmentArgs by navArgs()
            detailScreenCurrentPost = args.allPostdata
            loadTheDataToUI(args.allPostdata)
        }else{
            loadTheDataToUI(detailScreenCurrentPost)

        }
        setonClickListener()
    }


    private fun setonClickListener() {
        mBinding.apply {
            backButton.setOnClickListener{navigate(R.id.action_detailFragment_to_homeFragment)}
        }
    }

    private fun loadTheDataToUI(data: SinglePostData?) {
        val adapter = SinglePostListAdapter(data?.posts?: mutableListOf() , this)
        mBinding.postCompleteDetail.layoutManager = GridLayoutManager(requireContext(),2)
        mBinding.postCompleteDetail.adapter = adapter
        mBinding.postHashTag.text = context?.getString(R.string.hashtag_text,data?.posts?.get(0)?.tagText)
    }

    override fun postIsClicked(data: Post) {
        val directions = DetailFragmentDirections.actionDetailFragmentToSinglePostDetailScreen(data , Constants.isFromDetailScreen)
        navigate(directions)
    }

}