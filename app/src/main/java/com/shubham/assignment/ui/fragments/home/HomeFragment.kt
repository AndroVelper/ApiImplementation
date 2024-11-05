package com.shubham.assignment.ui.fragments.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.assignment.communication.IPostDetailListener
import com.shubham.assignment.communication.ISeePostListener
import com.shubham.assignment.data.Post
import com.shubham.assignment.data.SinglePostData
import com.shubham.assignment.databinding.FragmentHomeBinding
import com.shubham.assignment.network.apiManager.RetroFitInstanceManager
import com.shubham.assignment.network.repository.Repository
import com.shubham.assignment.ui.fragments.adapters.PostListingAdapter
import com.shubham.assignment.utils.Constants
import com.shubham.assignment.utils.hideView
import com.shubham.assignment.utils.navigate
import com.shubham.assignment.utils.runOnMainThread
import com.shubham.assignment.utils.showView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeFragment : Fragment() , ISeePostListener , IPostDetailListener {

    // make the binding lifecycle aware you don;t need to take care of variable.
    private val mBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private lateinit var repository: Repository

    // Use viewModels with a custom factory
    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(requireActivity().application, repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    // when the view has been created.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.dataProgressLoader.showView()
        initializeVariable()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding.retryButton.setOnClickListener { homeViewModel.initializeViewModel() }
        mBinding.swipeToRefresh.setOnRefreshListener {
            homeViewModel.initializeViewModel()
        }
    }


    private fun initializeVariable() {
        repository = Repository(RetroFitInstanceManager.apiService)
        initObserver()
    }

    /**
     * Logic for data showing
     * A As there are two types of list a Vertical list and Horizontal listing.
     * b Logic is the list with same order id (generally also have the same hashtags) is considered to be in same group.
     * c in this manner i have classified the data into groups for the horizontal and vertical scaling.
     * **/

    private fun initObserver() {
        homeViewModel.orderData.observe(viewLifecycleOwner) { orderDataResponse ->
            mBinding.dataProgressLoader.hideView()
            mBinding.postRecyclerView.showView()
            mBinding.retryButton.hideView()
            mBinding.noInternetConnection.hideView()
            mBinding.swipeToRefresh.isRefreshing = false
             val orderData: MutableList<MutableList<Post>> = mutableListOf()
            CoroutineScope(Dispatchers.Default).launch {
                val task = CoroutineScope(Dispatchers.Default).async {
                    val data = orderDataResponse?.data?.posts ?: emptyList()
                    data.forEach {
                        orderData.add(it.toMutableList())
                    }
                }
                task.await()
                Constants.CompleteDataHolder  = orderData
                runOnMainThread {
                    updateAndInitializeTheRecyclerView(orderData)
                }
            }

        }
    }

    private fun updateAndInitializeTheRecyclerView(orderData: MutableList<MutableList<Post>>) {
        mBinding.postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PostListingAdapter(orderData, this , this)
        mBinding.postRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        if(Constants.CompleteDataHolder != null){
            updateAndInitializeTheRecyclerView(Constants.CompleteDataHolder!!)
            mBinding.dataProgressLoader.hideView()
            return
        }

        if(!isInternetAvailable()){
            mBinding.dataProgressLoader.hideView()
            mBinding.noInternetConnection.showView()
            mBinding.postRecyclerView.hideView()
            mBinding.retryButton.showView()
            return

        }
        homeViewModel.initializeViewModel()

    }

    override fun seeAllPostClicked(position: Int, completePostData: MutableList<Post>) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(SinglePostData(completePostData))
        navigate(directions)
    }

    override fun postIsClicked(data: Post) {
        val directions = HomeFragmentDirections.actionHomeFragmentToSinglePostDetailScreen(data,Constants.isFromHomeScreen)
        navigate(directions)
    }

    override fun onResume() {
        super.onResume()
        Constants.detailScreenCurrentPost = null
    }


    fun isInternetAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}