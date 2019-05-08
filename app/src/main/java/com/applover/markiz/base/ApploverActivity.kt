package com.applover.markiz.base

import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.applover.markiz.R
import com.google.android.material.snackbar.Snackbar

/**
 * author marcinm on 2019-05-06.
 */
abstract class ApploverActivity : AppCompatActivity() {

	companion object {
		const val FRAGMENT_CONTAINER = R.id.fragment_container
	}

	private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
		beginTransaction().func().commit()
	}

	fun addFragment(fragment: Fragment, frameId: Int) {
		supportFragmentManager.inTransaction { add(frameId, fragment) }
	}

	fun showSnackMessage(@StringRes message: Int) {
		Snackbar.make(getRootView(), message, Snackbar.LENGTH_LONG).show()
	}

	private fun getRootView(): ViewGroup {
		return this.findViewById(android.R.id.content) as ViewGroup
	}
}