package com.market_user.opinion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.market_user.model.OpinionModel
import com.market_user.model.SellerModel
import com.market_user.model.UserModel

class OpinionViewModel : ViewModel()
{

    private var booleanMutableLiveDataOpinionModel: MutableLiveData<ArrayList<OpinionModel>> = MutableLiveData()
    private var booleanMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var stringMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var opinionModel: ArrayList<OpinionModel> = ArrayList()
    private var randomKey: String = FirebaseDatabase.getInstance().reference.push().key.toString()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var retSellerReference: DatabaseReference = FirebaseDatabase.getInstance().reference



    fun opinionUser(id: String, opinion: String): LiveData<Boolean>
    {
        retSellerReference
            .child("Users")
            .child(auth.uid!!)
            .addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if (snapshot.exists())
                    {
                        val model = snapshot.getValue(UserModel::class.java)
                        val opinionModel = OpinionModel(auth.uid!!, randomKey, model!!.name, model.image, opinion, 0, 0)

                        retSellerReference
                            .child("Sellers")
                            .child(id)
                            .child("Opinions")
                            .child(randomKey)
                            .setValue(opinionModel)

                        booleanMutableLiveData.postValue(true)
                    }

                    else
                    {
                        booleanMutableLiveData.postValue(false)
                        stringMutableLiveData.value = snapshot.value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError)
                {
                    booleanMutableLiveData.postValue(false)
                    stringMutableLiveData.value = error.message
                }

            })

        return booleanMutableLiveData
    }

    fun retrieveOpinion(id: String): LiveData<ArrayList<OpinionModel>>
    {
        retSellerReference
            .child("Sellers")
            .child(id)
            .child("Opinions")
            .addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    opinionModel.clear()

                    if (snapshot.exists())
                    {
                        for (snap in snapshot.children)
                        {
                            val model = snap.getValue(OpinionModel::class.java)
                            opinionModel.add(model!!)
                        }

                        booleanMutableLiveDataOpinionModel.postValue(opinionModel)
                    }

                    else
                    {
                        booleanMutableLiveData.postValue(false)
                        stringMutableLiveData.value = snapshot.value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError)
                {
                    booleanMutableLiveData.postValue(false)
                    stringMutableLiveData.value = error.message
                }

            })

        return booleanMutableLiveDataOpinionModel
    }

}