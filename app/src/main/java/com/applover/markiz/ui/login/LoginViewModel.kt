package com.applover.markiz.ui.login

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applover.markiz.data.model.NetworkState
import com.applover.markiz.data.network.ApploverApiService
import com.applover.markiz.internal.Event
import com.applover.markiz.internal.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * author marcinm on 2019-05-06.
 */
class LoginViewModel(private val apploverApiService: ApploverApiService) : ViewModel() {

	companion object {
		private const val CODE_UNAUTHORIZED = 401
		private const val CODE_INTERNAL_SERVER_ERROR = 500
	}

	private val _networkState = MutableLiveData<Event<NetworkState>>()
	val networkState: LiveData<Event<NetworkState>>
		get() = _networkState

	@Bindable
	val email = MutableLiveData<String>()

	@Bindable
	val password = MutableLiveData<String>()

	fun loginRequest() {
		_networkState.value = Event(NetworkState.LOADING)

		GlobalScope.launch(Dispatchers.Main) {
			val request = apploverApiService.postLoginAsync(email.value.toString(), password.value.toString())
			try {
				val response = request.await()
				when {
					response.isSuccessful -> _networkState.value = Event(NetworkState.SUCCESS)
					response.code() == CODE_UNAUTHORIZED -> _networkState.value = Event(NetworkState.UNAUTHORIZED)
					response.code() == CODE_INTERNAL_SERVER_ERROR -> _networkState.value =
						Event(NetworkState.ERROR_SERVER)
					else -> {
						_networkState.value = Event(NetworkState.ERROR_UNKNOWN)
					}
				}
			} catch (noConnection: NoConnectivityException) {
				_networkState.value = Event(NetworkState.NO_INTERNET_CONNECTION)
			}
		}
	}
}