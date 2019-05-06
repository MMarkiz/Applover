package com.applover.markiz.data.repository

import androidx.lifecycle.LiveData
import com.applover.markiz.data.model.Status

/**
 * author marcinm on 2019-05-06.
 */
interface ApploverRepository {
	suspend fun postLogin(email: String, password: String): LiveData<Status>
}