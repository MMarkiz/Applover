package com.applover.markiz.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applover.markiz.data.model.Status
import com.applover.markiz.internal.NoConnectivityException

/**
 * author marcinm on 2019-05-06.
 */
class ApploverNetworkDataSourceImpl(private val apploverApiService : ApploverApiService): ApploverNetworkDataSource {

	private val _login = MutableLiveData<Status>()
	override val login: LiveData<Status>
		get() = _login

	override suspend fun sendLogin(email:String,password:String) {
		try {
			val fetchedOrders = apploverApiService.postLoginAsync(email,password).await()
			_login.postValue(fetchedOrders)
		} catch (e: NoConnectivityException) {
			Log.e("Conectivity", "No internet connection", e)
		}
	}
}