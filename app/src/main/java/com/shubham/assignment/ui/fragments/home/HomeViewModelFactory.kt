package com.shubham.assignment.ui.fragments.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.assignment.network.repository.Repository


/***
 * Return the HomeViewModelFactory why factory because if we need to pass the params to
 * the viewModel we need to make the factory we process with the constructor initialization
 * then viewmodel will behave like the normal class
 *
 * @param application : as we are using the @see AndroidViewModel it takes the application as
 * a param which is used for the context.
 * @param repository : As i am using the constructor injection so repository is passed from the fragment
 * and activity
 *
 * @returns the instance of the viewmodel if exist otherwise create a new instance of the viewmodel
 *
 * ***/

class HomeViewModelFactory(
    private val application: Application,
    private val repository: Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
