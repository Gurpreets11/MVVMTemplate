package com.pack.mvvmtemplate.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pack.mvvmtemplate.data.model.AuthModels
import com.pack.mvvmtemplate.data.repository.AuthRepository
import com.pack.mvvmtemplate.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<Resource<String>>()
    val loginState: LiveData<Resource<String>> = _loginState

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = Resource.Error("Enter email and password")
            return
        }

        viewModelScope.launch {
            _loginState.value = Resource.Loading
            val result = authRepository.login(AuthModels.LoginRequest(email, password))
            if (result.isSuccess) {
                val resp = result.getOrNull()!!
                _loginState.value = Resource.Success(resp.token)
            } else {
                _loginState.value = Resource.Error(result.exceptionOrNull()?.message)
            }
        }
    }
}
