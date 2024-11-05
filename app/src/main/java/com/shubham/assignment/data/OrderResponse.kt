package com.shubham.assignment.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


data class OrderResponse(
    val data: Data,
    val status: Long,
    val success: Boolean,
)

data class Data(
    val posts: List<List<Post>>,
    val meta: List<Meum>,
)

@Parcelize
data class SinglePostData(
    val posts : MutableList<Post>
) : Parcelable

@Parcelize
data class Post(
    @SerializedName("post_id")
    val postId: String,
    val tags: String,
    @SerializedName("tag_text")
    val tagText: String,
    val order: Long,
    val mentions: Long,
    @SerializedName("user_id")
    val userId: String,
    val language: String,
    @SerializedName("live_at")
    val liveAt: String,
    val title: String,
    val text: String,
    @SerializedName("is_sponsored")
    val isSponsored: Long,
    @SerializedName("mux_playback_id")
    val muxPlaybackId: String,
    @SerializedName("mux_status")
    val muxStatus: String,
    @SerializedName("bucket_status")
    val bucketStatus: String,
    val origin: String,
    @SerializedName("max_down_res")
    val maxDownRes: String?,
    @SerializedName("mux_assest_id")
    val muxAssestId: String,
    val resh: String,
    val resw: String,
    val handle: String,
    @SerializedName("profile_pic")
    val profilePic: String,
    @SerializedName("interest_id")
    val interestId: String,
    val likes: Long,
    val views: Long,
    @SerializedName("is_followed")
    val isFollowed: Boolean,
    @SerializedName("is_liked")
    val isLiked: Boolean,
    val products: List<String?>,
    val brand: List<Brand>,
) : Parcelable

@Parcelize
data class Brand(
    val id: Long,
    @SerializedName("brand_id")
    val brandId: Long,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("brand_name")
    val brandName: String,
    val image: String?,
    @SerializedName("image_v2")
    val imageV2: String?,
    val category: String?,
    val description: String?,
    val meta: String?,
    @SerializedName("is_onboarded")
    val isOnboarded: Boolean,
    val website: String?,
) : Parcelable

@Parcelize
data class Meum(
    @SerializedName("tag_text")
    val tagText: String,
    val mentions: Long,
    val order: Long,
) : Parcelable
