package com.shubham.assignment.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.shubham.assignment.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


/**
 * This return the debug log according to the user convince of any type e,d,w,i,v
 *
 * @param tag : tag of the log
 * @param type : which type of log we want
 *
 * @return print the requested log in the console.
 * **/

fun String.debugLog(tag: String, type: Char = 'd') {
    when (type) {
        'd' -> Log.d(tag, this)
        'e' -> Log.e(tag, this)
        'w' -> Log.w(tag, this)
        'i' -> Log.i(tag, this)
        'v' -> Log.v(tag, this)
        else -> Log.d(tag, this)
    }
}


/**
 * This return the debug toast according to the user convince of any type length short and long according to convince
 *
 * @param context : reference of the screen to which we need to attach the toast
 * @param duration : Duration of the toast which we are showing eg Length.Short(1.5sec) and Long(3Sec)
 *
 * @return print the requested data on the screen
 * **/


fun String.debugToast(context: Context, duration: Int?) {
    val toastManager = ToastManager.getInstance()
    if (duration != null) {
        toastManager.makeToast(context, message = this, duration = duration);
        return
    }
    toastManager.makeToast(context, message = this);
}

fun Fragment.runOnMainThread(task: () -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        task()
    }
}

/*
* These are the extensive used functions to prevent
* the function stack this will work as inline
*
* this has a warning but as the use of this function increase this
* will somehow impact the performance
* */

inline fun View.hideView() {
    this.visibility = View.GONE
}

inline fun View.showView() {
    this.visibility = View.VISIBLE
}


/*
* find the number of days from the post date to the current date.
* provide the date and this will return you the total no of days of the post
* why this if someHow date pattern change then we will show the
 */

fun String.getTotalDaysOfPostPosted(): Long {
    try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val pastDate = LocalDateTime.parse("2022-03-19T19:16:28", formatter)
        val currentDate = LocalDateTime.now()
        return ChronoUnit.DAYS.between(pastDate, currentDate)
    } catch (e: Exception) {
        return 0L
    }
}

/*
* this is for the navigation between the complete app.
* */

fun Fragment.navigate(action: Int) {
    try {
        findNavController().navigate(action)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.navigate(action: NavDirections) {
    try {
        findNavController().navigate(action)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

 inline fun ShapeableImageView.loadImage(context : Context , url : String   ){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}