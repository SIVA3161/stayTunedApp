package com.sivag.staytuned.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sivag.staytuned.base.BaseFragment
import com.sivag.staytuned.databinding.FragmentRegisterBinding

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */

class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}