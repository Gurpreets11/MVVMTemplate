package com.pack.mvvmtemplate.ui.changepassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pack.mvvmtemplate.R
import com.pack.mvvmtemplate.databinding.FragmentRegisterBinding
import com.pack.mvvmtemplate.ui.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChangePasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegisterBinding.bind(view)

        binding.btnRegister.setOnClickListener {
            // collect fields and call viewModel.register(...)
            // on success navigate to OTP
            findNavController().navigate(R.id.action_register_to_otp)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}