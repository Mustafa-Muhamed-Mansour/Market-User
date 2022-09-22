package com.market_user.register

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.market_user.model.UserModel

class RegisterViewModel : ViewModel()
{

    private var booleanMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var stringMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var randomKey: String = FirebaseDatabase.getInstance().reference.push().key.toString()
    private var userRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var userReference: StorageReference = FirebaseStorage.getInstance().reference.child("Images").child("Images-Users").child(randomKey)


    fun registerUser(image: String, email: String,  password: String, name: String, phone: String): LiveData<Boolean>
    {
        auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful)
                {
                    booleanMutableLiveData.postValue(true)

                    userReference
                        .putFile(Uri.parse(image))
                        .addOnSuccessListener {

                            booleanMutableLiveData.postValue(true)

                            userReference
                                .downloadUrl
                                .addOnSuccessListener { img ->

                                    val userModel = UserModel(auth.uid!!, randomKey, img.toString(), email, name, phone)

                                    userRef
                                        .child("Users")
                                        .child(auth.uid!!)
                                        .setValue(userModel)

                                    booleanMutableLiveData.postValue(true)

                                }.addOnFailureListener { except ->

                                    stringMutableLiveData.value = except.message
                                    booleanMutableLiveData.postValue(false)
                                }

                        }.addOnFailureListener{ ex ->

                            stringMutableLiveData.value = ex.message
                            booleanMutableLiveData.postValue(false)

                        }
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