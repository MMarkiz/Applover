package com.applover.markiz.data.network

import androidx.lifecycle.LiveData
import com.applover.markiz.data.model.Status

/**
 * author marcinm on 2019-05-06.
 */
interface ApploverNetworkDataSource {

	val login: LiveData<Status>

	suspend fun sendLogin(email:String,password:String)
}