package com.market_user.common

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

class Validation
{

    fun checkEmail(context: Context, email: String): String
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
        }

        return email
    }

    fun checkPassword(context: Context, password: String): String
    {
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show()
        }

        return password
    }

    fun checkName(context: Context, name: String): String
    {
        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
        }

        return name
    }

    fun checkPhone(context: Context, phone: String): String
    {
        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(context, "Please enter your phone", Toast.LENGTH_SHORT).show()
        }

        return phone
    }

    fun checkOpinion(context: Context, opinion: String): String
    {
        if (TextUtils.isEmpty(opinion))
        {
            Toast.makeText(context, "Please enter your opinion", Toast.LENGTH_SHORT).show()
        }

        return opinion
    }
}