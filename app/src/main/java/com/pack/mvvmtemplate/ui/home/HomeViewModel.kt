package com.pack.mvvmtemplate.ui.home



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
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _homeState = MutableLiveData<Resource<String>>()
    val homeState: LiveData<Resource<String>> = _homeState

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _homeState.value = Resource.Error("Enter email and password")
            return
        }

        viewModelScope.launch {
            _homeState.value = Resource.Loading
            val result = authRepository.login(AuthModels.LoginRequest(email, password))
            if (result.isSuccess) {
                val resp = result.getOrNull()!!
                _homeState.value = Resource.Success(resp.token)
            } else {
                _homeState.value = Resource.Error(result.exceptionOrNull()?.message)
            }
        }
    }
}