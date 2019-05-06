package com.applover.markiz.data.repository

import androidx.lifecycle.LiveData
import com.applover.markiz.data.model.Status
import com.applover.markiz.data.network.ApploverNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * author marcinm on 2019-05-06.
 */
class ApploverRepositoryImpl(private val apploverNetworkDataSource: ApploverNetworkDataSource) : ApploverRepository {

	override suspend fun postLogin(email: String,password: String): LiveData<Status> {
		return withContext(Dispatchers.IO) {
			sendLogin(email,password)
			return@withContext apploverNetworkDataSource.login
		}
	}

	private suspend fun sendLogin(email : String, password: String) {
		apploverNetworkDataSource.sendLogin(email,password)
	}
}