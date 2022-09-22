package com.market_user.ui

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.logging.Handler

class SplashViewModel : ViewModel()
{

    private var handler: android.os.Handler = android.os.Handler(Looper.getMainLooper())
    private var booleanMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun postDelay(): LiveData<Boolean>
    {
        handler
            .postDelayed(
                {
                    booleanMutableLiveData.postValue(true)

                }, 3000)
        return booleanMutableLiveData
    }

}