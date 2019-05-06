package com.applover.markiz.ui.login

import android.os.Bundle
import com.applover.markiz.R
import com.applover.markiz.base.ApploverActivity

/**
 * author marcinm on 2019-05-06.
 */
class LoginActivity : ApploverActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_fragment_only)

		addFragment(LoginFragment(), FRAGMENT_CONTAINER)
	}
}