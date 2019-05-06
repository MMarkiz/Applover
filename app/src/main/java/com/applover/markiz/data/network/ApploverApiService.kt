package com.applover.markiz.data.network

import com.applover.markiz.data.model.Status
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * author marcinm on 2019-05-06.
 */
interface ApploverApiService {

	companion object {
		operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): ApploverApiService {

			val okHttpClient = OkHttpClient.Builder()
				.addInterceptor(connectivityInterceptor)
				.build()

			return Retrofit.Builder()
				.client(okHttpClient)
				.baseUrl("https://bench-api.applover.pl/api/")
				.addCallAdapterFactory(CoroutineCallAdapterFactory())
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(ApploverApiService::class.java)
		}
	}

	@POST("v1/login")
	fun postLoginAsync(@Query("email") email: String, @Query("password") password: String): Deferred<Status>
}