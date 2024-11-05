package com.shubham.assignment.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shubham.assignment.R
import com.shubham.assignment.databinding.FragmentSinglePostDetailScreenBinding
import com.shubham.assignment.ui.fragments.adapters.BrandAdapter
import com.shubham.assignment.utils.Constants
import com.shubham.assignment.utils.getTotalDaysOfPostPosted
import com.shubham.assignment.utils.loadImage
import com.shubham.assignment.utils.navigate


class SinglePostDetailScreen : Fragment() {

    private val binding: FragmentSinglePostDetailScreenBinding by lazy {
        FragmentSinglePostDetailScreenBinding.inflate(layoutInflater)
    }

    private val args: SinglePostDetailScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpScreenData()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.backButton.setOnClickListener{
            navigate(if(args.fromWhichScreen == Constants.isFromHomeScreen)R.id.action_singlePostDetailScreen_to_homeFragment else R.id.action_singlePostDetailScreen_to_detailFragment)
        }
    }

    private fun setUpScreenData() {
        binding.apply {
            hashTagImage.loadImage(requireContext(),getString(R.string.image_loader, args.postData.muxPlaybackId))
            profileImage.loadImage(requireContext(), args.postData.profilePic)
            orderPostDay.text = getString(R.string.days , args.postData.liveAt.getTotalDaysOfPostPosted().toString())
            orderDescription.text = args.postData.text
            profileName.text = args.postData.handle
            val adapter = BrandAdapter(args.postData.brand.toMutableList())
            brandsRecycler.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false )
            brandsRecycler.adapter = adapter


        }
    }
}