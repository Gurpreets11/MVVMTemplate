package com.pack.mvvmtemplate.ui.register

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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerState = MutableLiveData<Resource<String>>()
    val registerState: LiveData<Resource<String>> = _registerState

    fun register(email: String, name: String, mobileNumber: String, password: String) {
        if (email.isBlank()) {
            _registerState.value = Resource.Error("Enter email..")
            return
        }

        if (name.isBlank()) {
            _registerState.value = Resource.Error("Enter name..")
            return
        }

        if (mobileNumber.isBlank()) {
            _registerState.value = Resource.Error("Enter mobile number..")
            return
        }

        if (mobileNumber.length < 10) {
            _registerState.value = Resource.Error("Enter valid mobile number..")
            return
        }

        if (password.isBlank()) {
            _registerState.value = Resource.Error("Enter password..")
            return
        }

        if (password.length < 6) {
            _registerState.value = Resource.Error("Enter valid password..")
            return
        }

        viewModelScope.launch {
            _registerState.value = Resource.Loading
            val result = authRepository.register(AuthModels.RegisterRequest(email,name,mobileNumber, password))
            if (result.isSuccess) {
                val resp = result.getOrNull()!!
                _registerState.value = Resource.Success(resp.token)
            } else {
                _registerState.value = Resource.Error(result.exceptionOrNull()?.message)
            }
        }
    }
}