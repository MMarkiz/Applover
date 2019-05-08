package com.applover.markiz.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applover.markiz.data.network.ApploverApiService

/**
 * author marcinm on 2019-05-06.
 */
class LoginViewModelFactory(private val apploverApiService: ApploverApiService): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(apploverApiService) as T
    }
}