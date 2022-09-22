package com.market_user.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.market_user.R
import com.market_user.databinding.FragmentSplashBinding

class SplashFragment : Fragment()
{

    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        initViewModel()
        retrieveViewModel()

    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
    }

    private fun retrieveViewModel()
    {
        viewModel
            .postDelay()
            .observe(viewLifecycleOwner)
            {
                if (it)
                {
                    Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment)
                }

                else
                {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
    }

}