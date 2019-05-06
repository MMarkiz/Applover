package com.applover.markiz.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.applover.markiz.R

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

	fun replaceFragment(fragment: Fragment, frameId: Int) {
		supportFragmentManager.inTransaction { replace(frameId, fragment) }
	}
}