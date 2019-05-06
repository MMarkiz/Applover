package com.applover.markiz.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applover.markiz.R
import com.applover.markiz.base.ApploverFragment

/**
 * author marcinm on 2019-05-06.
 */
class LoginFragment : ApploverFragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_login, container, false)
	}
}