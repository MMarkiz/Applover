package com.applover.markiz.base

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.applover.markiz.R
import com.applover.markiz.ui.dialog.LoadingDialog


/**
 * author marcinm on 2019-05-06.
 */
abstract class ApploverFragment : Fragment() {

	companion object {
		const val FRAGMENT_CONTAINER = R.id.fragment_container
	}

	private lateinit var loadingDialog: LoadingDialog

	fun addFragmentWithBackStack(fragment: Fragment) {
		fragmentManager!!.beginTransaction().add(FRAGMENT_CONTAINER, fragment).addToBackStack(null).commit()
	}

	fun showLoadingDialog(show: Boolean) {
		if (show) {
			loadingDialog = LoadingDialog()
			loadingDialog.setTargetFragment(this, 0)
			loadingDialog.show(fragmentManager!!, null)
		} else if (::loadingDialog.isInitialized) {
			loadingDialog.dismiss()
		}

	}

	protected fun showSnackMessage(@StringRes message: Int) {
		(activity as ApploverActivity).showSnackMessage(message)
	}
}