package com.shubham.assignment.network.apiManager

import com.shubham.assignment.data.OrderModal
import com.shubham.assignment.data.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface IApiDescriptor {

    @POST("api/v1/homepage/trendingpostsgroupedv3/")
    suspend fun getOrderList(
        @Body data: OrderModal,
        @Header("TOKEN") authorization: String
    ): Response<OrderResponse>

    @GET("image.mux.com/{mux_playback_id}/thumbnail.png")
    fun getImagePreview(@Path("mux_playback_id") muxPlaybackId: String): Response<Any>

}