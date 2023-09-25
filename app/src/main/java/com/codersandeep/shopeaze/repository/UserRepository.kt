package com.codersandeep.shopeaze.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codersandeep.shopeaze.api.UserAPI
import com.codersandeep.shopeaze.models.SigninRequest
import com.codersandeep.shopeaze.models.SigninResponse
import com.codersandeep.shopeaze.utils.NetworkResponse
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    private val _userResponseLiveData = MutableLiveData<NetworkResponse<SigninResponse>>()
    val userResponseLiveData : LiveData<NetworkResponse<SigninResponse>>
        get() = _userResponseLiveData

    suspend fun signin(signinRequest: SigninRequest) {
        _userResponseLiveData.postValue(NetworkResponse.Loading())
        val response = userAPI.signin(signinRequest)
        if(response.isSuccessful && response.body() != null)
            _userResponseLiveData.postValue(NetworkResponse.Success(response.body()))
        else if (response.errorBody() != null)
            _userResponseLiveData.postValue(NetworkResponse.Error(response.body(),"Wrong userName or password"))
        else
            _userResponseLiveData.postValue(NetworkResponse.Error(response.body(),"Something went wrong"))
    }
}