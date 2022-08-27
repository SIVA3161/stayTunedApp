package com.sivag.staytuned.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.sivag.staytuned.MainActivity
import com.sivag.staytuned.R
import com.sivag.staytuned.base.BaseFragment
import com.sivag.staytuned.databinding.FragmentLoginBinding
import com.sivag.staytuned.extension.navigateTo
import com.sivag.staytuned.extension.stackFragmentInActivity
import com.sivag.staytuned.signup.RegisterFragment
import kotlinx.android.synthetic.main.item_login_layout.*

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */
class LoginFragment : BaseFragment() {

    private lateinit var parentActivity : MainActivity
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parentActivity = activity as MainActivity



        notRegisteredBtn.setOnClickListener {
            val navHostFragment = parentActivity.supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
            val navController = navHostFragment.navController

            navController.navigate(R.id.signUpFragment)
        }

        binding.btnLogin.setOnClickListener {
                Toast.makeText(parentActivity,"Hello",Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

}