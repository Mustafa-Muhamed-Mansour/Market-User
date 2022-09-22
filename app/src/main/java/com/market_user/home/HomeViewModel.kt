package com.market_user.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.DataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.market_user.model.OpinionModel
import com.market_user.model.SellerModel
import com.market_user.model.UserModel

class HomeViewModel : ViewModel()
{

    private var booleanMutableLiveDataSellerModel: MutableLiveData<ArrayList<SellerModel>> = MutableLiveData()
    private var stringMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var sellerModel: ArrayList<SellerModel> = ArrayList()
    private var retSellerReference: DatabaseReference = FirebaseDatabase.getInstance().reference


    fun retrieveSeller(): LiveData<ArrayList<SellerModel>>
    {
        retSellerReference
            .child("Sellers")
            .addValueEventListener(object : ValueEventListener
            {

                override fun onDataChange(snapshot: DataSnapshot)
                {
                    sellerModel.clear()

                    if (snapshot.exists())
                    {
                        for (snap in snapshot.children)
                        {
                            val model = snap.getValue(SellerModel::class.java)
                            sellerModel.add(model!!)
                        }

                        booleanMutableLiveDataSellerModel.postValue(sellerModel)
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

        return booleanMutableLiveDataSellerModel
    }
}