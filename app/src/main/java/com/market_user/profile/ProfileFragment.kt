package com.market_user.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.market_user.R
import com.market_user.databinding.FragmentProfileBinding

class ProfileFragment : Fragment()
{

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var validation: com.market_user.common.Validation
    private var nightMode: Boolean?= false
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)



        initView()
        clickViews()
        initViewModel()
        retrieveViewModel()



    }

    private fun initView()
    {
        validation = com.market_user.common.Validation()
        sharedPreferences = requireActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE)
        nightMode = sharedPreferences.getBoolean("Not Night", false)

        if (binding.switchDarkTheme.isChecked)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }


    private fun clickViews()
    {
        binding
            .imgBtnLogout
            .setOnClickListener {

                Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_loginFragment)
            }

        binding
            .switchDarkTheme
            .setOnClickListener {

                if (binding.switchDarkTheme.isChecked.not())
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    editor = sharedPreferences.edit()
                    editor.putBoolean("Not Night", false)
                }

                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    editor = sharedPreferences.edit()
                    editor.putBoolean("Night", true)
                }

            }

        binding
            .btnEdit
            .setOnClickListener {

                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()

            }
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    private fun retrieveViewModel()
    {
       viewModel
           .retrieveUser()
           .observe(viewLifecycleOwner)
           {

               Glide
                   .with(requireContext())
                   .load(it.image)
                   .placeholder(R.drawable.ic_profile)
                   .error(R.drawable.ic_profile)
                   .into(binding.imgProfile)
               binding.txtName.text = it.name
               binding.txtPhone.text = it.phone
           }
    }
}