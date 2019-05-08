package com.applover.markiz.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.applover.markiz.R
import com.applover.markiz.base.ApploverFragment
import com.applover.markiz.data.model.NetworkState
import com.applover.markiz.databinding.FragmentLoginBinding
import com.applover.markiz.ui.success.SuccessFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * author marcinm on 2019-05-06.
 */
class LoginFragment : ApploverFragment(), KodeinAware, View.OnClickListener {

	companion object {
		private const val VALUE_FIVE = 5
	}

	override val kodein by kodein()
	private val viewModelFactory: LoginViewModelFactory by instance()
	private lateinit var loginViewModel: LoginViewModel


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
		val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
		binding.apply { this.viewmodel = loginViewModel }
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		button_login_login.setOnClickListener(this)

		loginViewModel.networkState.observe(viewLifecycleOwner, Observer {
			it.getContent()?.let { state ->
				observeLoadingState(state)
			}
			it.getContentIfNotHandled()?.let { state ->
				observeNetworkState(state)
			}
		})
	}


	private fun observeLoadingState(state: NetworkState) {
		if (state.state == NetworkState.State.LOADING) {
			showLoadingDialog(true)
		} else {
			showLoadingDialog(false)
		}
	}

	private fun observeNetworkState(state: NetworkState) {
		when (state.state) {

			NetworkState.State.LOADING -> { }

			NetworkState.State.SUCCESS -> startSuccessFragment()

			NetworkState.State.UNAUTHORIZED -> showSnackMessage(R.string.login_error_unauthorized)

			NetworkState.State.ERROR_SERVER -> showSnackMessage(R.string.login_error_server)

			NetworkState.State.ERROR_UNKNOWN -> showSnackMessage(R.string.login_error_unknown)

			NetworkState.State.NO_INTERNET_CONNECTION -> showSnackMessage(R.string.login_error_network_connection)

		}
	}

	override fun onClick(view: View) {
		when (view.id) {
			R.id.button_login_login -> {
				login()
			}
		}
	}

	private fun login() {
		if (validate()) {
			loginViewModel.loginRequest()
		}
	}

	private fun startSuccessFragment() {
		addFragmentWithBackStack(SuccessFragment())
	}

	private fun validate(): Boolean {
		var isValid = true
		isValid = validateEmail(isValid)
		isValid = validatePassword(isValid)
		return isValid
	}

	private fun validateEmail(isValid: Boolean): Boolean {
		var isEmailValid = isValid
		when {
			edittext_login_email.text.toString().isEmpty() -> {
				textinputlayout_login_email.error = getString(R.string.login_validation_not_empty)
				isEmailValid = false
			}
			!android.util.Patterns.EMAIL_ADDRESS.matcher(edittext_login_email.text.toString()).matches() -> {
				textinputlayout_login_email.error = getString(R.string.login_validation_incorrect_email)
				isEmailValid = false
			}
			else -> textinputlayout_login_email.isErrorEnabled = false
		}
		return isEmailValid
	}

	private fun validatePassword(isValid: Boolean): Boolean {
		var isPasswordValid = isValid
		when {
			edittext_login_password.text.toString().isEmpty() -> {
				textinputlayout_login_password.error = getString(R.string.login_validation_not_empty)
				isPasswordValid = false
			}
			edittext_login_password.text.toString().length < VALUE_FIVE -> {
				textinputlayout_login_password.error = getString(R.string.login_validation_short_password)
				isPasswordValid = false
			}
			else -> textinputlayout_login_password.isErrorEnabled = false
		}
		return isPasswordValid
	}
}