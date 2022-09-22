package com.market_user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel()
{

    private var booleanMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var stringMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()


    fun loginUser(email: String, password: String): LiveData<Boolean>
    {
        auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful)
                {
                    booleanMutableLiveData.postValue(true)
                }

                else
                {
                    booleanMutableLiveData.postValue(false)
                    stringMutableLiveData.value = it.exception!!.toString()
                }

            }

        return booleanMutableLiveData
    }
}