package com.shubham.assignment.network.apiManager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstanceManager {

    private val retrofitInstance = Retrofit.Builder()
        .baseUrl("http://165.232.191.15/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService: IApiDescriptor = retrofitInstance.create(IApiDescriptor::class.java)
}