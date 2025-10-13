package com.pack.mvvmtemplate.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pack.mvvmtemplate.R
import com.pack.mvvmtemplate.databinding.FragmentLoginBinding
import com.pack.mvvmtemplate.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        binding.tvForgot.setOnClickListener {
            findNavController().navigate(R.id.changePasswordFragment)
        }

        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> {
                    // show loader
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
                    // navigate to home
                    findNavController().navigate(R.id.action_login_to_home)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), state.message ?: "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
