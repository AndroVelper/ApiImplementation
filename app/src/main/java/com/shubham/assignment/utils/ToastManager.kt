package com.shubham.assignment.utils

import android.content.Context
import android.widget.Toast


/***
 * Used to make the toast and show on the screen
 * @since follow the singleTon pattern.
 * ***/


class ToastManager private constructor() {

    private var toast: Toast? = null

    companion object {

        private var instance: ToastManager? = null

        fun getInstance(): ToastManager {
            return instance ?: synchronized(this) {
                ToastManager().also { instance = it }
            }
        }
    }


    /***
     * Return the toast if already toast is there then just update the text and show that.
     *
     * @param context : reference to which we need to attach the toast
     * @param message : message we need to show in the toast
     * @param duration : Duration of the toast by default it is set to Short but you can change to
     *  @see Toast.LENGTH_LONG  as well
     *
     * @return Show the toast on the screen.
     * ***/

    fun makeToast(context: Context , message : String , duration : Int = Toast.LENGTH_SHORT){

        toast?:Toast(context)

        if (toast == null) {
            toast = Toast.makeText(context, message, duration)
        } else {
            toast?.setText(message) // Update the text of the existing toast
        }
        toast?.duration = duration // Update the duration if needed
        toast?.show()
    }
}