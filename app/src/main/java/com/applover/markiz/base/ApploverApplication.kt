package com.applover.markiz.base

import android.app.Application
import com.applover.markiz.data.network.*
import com.applover.markiz.data.repository.ApploverRepository
import com.applover.markiz.data.repository.ApploverRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * author marcinm on 2019-05-06.
 */
class ApploverApplication : Application(), KodeinAware {
	override val kodein = Kodein.lazy {
		import(androidXModule(this@ApploverApplication))

		bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
		bind() from singleton { ApploverApiService(instance()) }
		bind<ApploverNetworkDataSource>() with singleton { ApploverNetworkDataSourceImpl(instance()) }
		bind<ApploverRepository>() with singleton { ApploverRepositoryImpl(instance()) }
	}

}