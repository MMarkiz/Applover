package com.applover.markiz.data.model

/**
 * author marcinm on 2019-05-07.
 */
open class NetworkState(val state: State) {

	enum class State {
		LOADING,
		SUCCESS,
		UNAUTHORIZED,
		ERROR_SERVER,
		ERROR_UNKNOWN,
		NO_INTERNET_CONNECTION
	}

	companion object {

		val LOADING = NetworkState(State.LOADING)
		val SUCCESS = NetworkState(State.SUCCESS)
		val UNAUTHORIZED = NetworkState(State.UNAUTHORIZED)
		val ERROR_SERVER = NetworkState(State.ERROR_SERVER)
		val ERROR_UNKNOWN = NetworkState(State.ERROR_UNKNOWN)
		val NO_INTERNET_CONNECTION = NetworkState(State.NO_INTERNET_CONNECTION)
	}
}