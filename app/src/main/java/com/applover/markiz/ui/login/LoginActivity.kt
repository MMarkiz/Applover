package com.applover.markiz.ui.login


import android.os.Bundle
import android.os.Handler
import com.applover.markiz.R
import com.applover.markiz.base.ApploverActivity


/**
 * author marcinm on 2019-05-06.
 */
class LoginActivity : ApploverActivity() {

	companion object {
		const val DELAY_TWO_SECONDS = 2000L
	}

	private var backPressedOnce = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_fragment_only)

		if (savedInstanceState == null)
			addFragment(LoginFragment(), FRAGMENT_CONTAINER)
	}

	override fun onBackPressed() {
		val currentFragment = supportFragmentManager.findFragmentById(FRAGMENT_CONTAINER)

		if (!backPressedOnce && currentFragment is LoginFragment) {
			this.backPressedOnce = true
			showSnackMessage(R.string.login_exit_click_info)
			Handler().postDelayed({ backPressedOnce = false }, DELAY_TWO_SECONDS)
		} else {
			super.onBackPressed()
		}
	}
}