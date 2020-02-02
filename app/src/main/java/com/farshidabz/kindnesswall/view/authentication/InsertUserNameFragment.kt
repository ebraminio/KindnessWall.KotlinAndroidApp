package com.farshidabz.kindnesswall.view.authentication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.farshidabz.kindnesswall.BaseFragment
import com.farshidabz.kindnesswall.R
import com.farshidabz.kindnesswall.data.local.UserInfoPref
import com.farshidabz.kindnesswall.data.model.CustomResult
import com.farshidabz.kindnesswall.databinding.FragmentInsertUsernameBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class InsertUserNameFragment : BaseFragment() {

    private val viewModel by sharedViewModel<AuthenticationViewModel>()
    private var authenticationInteractor: AuthenticationInteractor? = null

    lateinit var binding: FragmentInsertUsernameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_insert_username, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        authenticationInteractor = context as AuthenticationActivity
    }

    override fun configureViews() {
        binding.sendUsernameTextView.setOnClickListener {
            if (binding.userNameEditText.text.isEmpty()) {
                showToastMessage(getString(R.string.insert_user_name))
            } else {
                viewModel.updateUserProfile(
                    binding.userNameEditText.text.toString(),
                    UserInfoPref.image
                ).observe(viewLifecycleOwner) {
                    when (it.status) {
                        CustomResult.Status.LOADING -> {
                            showProgressDialog()
                        }

                        CustomResult.Status.SUCCESS -> {
                            dismissProgressDialog()
                            authenticationInteractor?.onAuthenticationComplete(binding.sendUsernameTextView)
                        }
                        else -> {
                            showToastMessage("")
                        }
                    }
                }
            }
        }

        binding.skipAuthenticationTextView.setOnClickListener { activity?.finish() }
    }
}
