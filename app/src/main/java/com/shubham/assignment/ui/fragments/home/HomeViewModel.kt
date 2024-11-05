package com.shubham.assignment.ui.fragments.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shubham.assignment.data.OrderModal
import com.shubham.assignment.data.OrderResponse
import com.shubham.assignment.network.repository.Repository
import com.shubham.assignment.utils.Constants
import com.shubham.assignment.utils.debugLog
import com.shubham.assignment.utils.debugToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val application: Application , private val repository : Repository) : AndroidViewModel(application){


    private val _orderData : MutableLiveData<OrderResponse> = MutableLiveData()
    val orderData : LiveData<OrderResponse> get()  = _orderData



    fun initializeViewModel(){
        getOrderList()
    }

    private fun getOrderList(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val response = repository.getOrderList(OrderModal())
                if(response.isSuccessful){
                    _orderData.postValue(response.body())
                }
                else{
                    "Api return the error".debugToast(application.applicationContext, Toast.LENGTH_LONG)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }

        }


    }
}