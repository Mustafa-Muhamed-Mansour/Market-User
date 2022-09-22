package com.market_user.model

import android.os.Parcel
import android.os.Parcelable

class SellerModel() : Parcelable
{
    lateinit var id: String
    lateinit var randomKey: String
    lateinit var image: String
    lateinit var email: String
    lateinit var name: String
    lateinit var phone: String
    lateinit var address: String

    constructor(parcel: Parcel) : this()
    {
        id = parcel.readString()!!
        randomKey = parcel.readString()!!
        image = parcel.readString()!!
        email = parcel.readString()!!
        name = parcel.readString()!!
        phone = parcel.readString()!!
        address = parcel.readString()!!
    }

    override fun describeContents(): Int
    {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int)
    {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<SellerModel>
    {
        override fun createFromParcel(parcel: Parcel): SellerModel
        {
            return SellerModel(parcel)
        }

        override fun newArray(size: Int): Array<SellerModel?>
        {
            return arrayOfNulls(size)
        }
    }


}