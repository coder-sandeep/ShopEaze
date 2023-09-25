package com.codersandeep.shopeaze.ui.auth

import android.text.TextUtils
import androidx.appcompat.widget.ThemedSpinnerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codersandeep.shopeaze.models.SigninRequest
import com.codersandeep.shopeaze.models.SigninResponse
import com.codersandeep.shopeaze.repository.UserRepository
import com.codersandeep.shopeaze.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){

    val userResponseLiveData: LiveData<NetworkResponse<SigninResponse>>
        get() = userRepository.userResponseLiveData
    fun userLogin(signinRequest: SigninRequest)  {
        viewModelScope.launch { userRepository.signin(signinRequest)
        }
    }

//    fun validateCredentials(emailAddress: String, userName: String, password: String,
//                            isLogin: Boolean) : Pair<Boolean, String> {
//
//        var result = Pair(true, "")
//        if(TextUtils.isEmpty(emailAddress) || (!isLogin && TextUtils.isEmpty(userName)) || TextUtils.isEmpty(password)){
//            result = Pair(false, "Please provide the credentials")
//        }
//        else if(!ThemedSpinnerAdapter.Helper.isValidEmail(emailAddress)){
//            result = Pair(false, "Email is invalid")
//        }
//        else if(!TextUtils.isEmpty(password) && password.length <= 5){
//            result = Pair(false, "Password length should be greater than 5")
//        }
//        return result
//    }
}