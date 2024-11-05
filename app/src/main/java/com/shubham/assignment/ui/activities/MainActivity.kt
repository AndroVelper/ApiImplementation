package com.shubham.assignment.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shubham.assignment.databinding.ActivityMainBinding


// this is the complete entry point for the app.

class MainActivity : AppCompatActivity() {

    private val mBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

}