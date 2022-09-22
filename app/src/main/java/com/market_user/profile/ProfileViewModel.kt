package com.market_user.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.market_user.model.UserModel

class ProfileViewModel : ViewModel()
{

    private var booleanMutableLiveDataUserModel: MutableLiveData<UserModel> = MutableLiveData()
    private var booleanMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var stringMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var retUserReference: DatabaseReference = FirebaseDatabase.getInstance().reference


    fun retrieveUser(): LiveData<UserModel>
    {
        retUserReference
            .child("Users")
            .child(auth.uid!!)
            .addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if (snapshot.exists())
                    {
                        val model = snapshot.getValue(UserModel::class.java)
                        booleanMutableLiveDataUserModel.postValue(model!!)
                    }

                    else
                    {
                        stringMutableLiveData.value = snapshot.value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError)
                {
                    stringMutableLiveData.value = error.message
                }
            })

        return booleanMutableLiveDataUserModel
    }


    fun editOfUser(randomKey: String, image: String, email: String, name: String, phone: String): LiveData<Boolean>
    {
        val userModel = UserModel(auth.uid!!, randomKey, image, email, name, phone)

        retUserReference
            .child("Users")
            .child(auth.uid!!)
            .setValue(userModel)
            .addOnCompleteListener {

                if (it.isSuccessful)
                {
                    booleanMutableLiveData.postValue(true)
                }

                else
                {
                    stringMutableLiveData.value = it.exception!!.message
                    booleanMutableLiveData.postValue(false)
                }

            }

        return booleanMutableLiveData
    }

}