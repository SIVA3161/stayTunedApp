package com.sivag.staytuned.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sivag.staytuned.MainActivity
import com.sivag.staytuned.R
import com.sivag.staytuned.base.BaseFragment
import com.sivag.staytuned.database.RegisterDatabase
import com.sivag.staytuned.database.RegisterRepository
import com.sivag.staytuned.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.item_login_layout.*

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */


class LoginFragment : BaseFragment() {

    private lateinit var parentActivity : MainActivity
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel
    private var navHostFragment : NavHostFragment? = null
    private var navController : NavController? = null

    //user data declarations
    private var userLoginEmail: String = ""
    private var userLoginPassword: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(LoginViewModel::class.java)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parentActivity = activity as MainActivity

        navHostFragment = parentActivity.supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment!!.navController

        getUserLoginCredentials()

        viewModel.errorToastUsername.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isGenuineUser.observe(viewLifecycleOwner, Observer { isGenuineUser ->
            if (isGenuineUser == true ) {
                Toast.makeText(requireContext(), "Successfully Logged In..", Toast.LENGTH_LONG).show()
                val navHostFragment = parentActivity.supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
                val navController = navHostFragment.navController

                navController.navigate(R.id.userDashboardFragment)
            }else {
                Toast.makeText(requireContext(), "Invalid Credentials", Toast.LENGTH_LONG).show()
            }
        })

        notRegisteredBtn.setOnClickListener {
            navController?.navigate(R.id.signUpFragment)
        }

        titleLogin.setOnClickListener {
            viewModel.deleteUserDBRequest()
        }

    }

    private fun getUserLoginCredentials() {
        dtvEmailLoginEt.addTextChangedListener(loginTextWatcher)
        dtvPasswordLoginEt.addTextChangedListener(loginTextWatcher)

        binding.btnLogin.setOnClickListener {
            if (loginFormValidation()) {
                viewModel.userPasswordValidation(userEmail = userLoginEmail, userPwd = userLoginPassword)
            } else {
                if (userLoginEmail.isNullOrEmpty()) dtvEmailLoginEt.error = getString(com.sivag.staytuned.R.string.empty_email)
                else if (!Patterns.EMAIL_ADDRESS.matcher(userLoginEmail).matches()) dtvEmailLoginEt.error = getString(com.sivag.staytuned.R.string.invalid_email_address)
                else if (userLoginPassword.isNullOrEmpty()) dtvPasswordLoginEt.error = getString(com.sivag.staytuned.R.string.empty_password)
                else return@setOnClickListener
            }
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            userLoginEmail = dtvEmailLoginEt.text.toString().trim()
            userLoginPassword = dtvPasswordLoginEt.text.toString().trim()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    private fun loginFormValidation(): Boolean {

        return !userLoginEmail.isNullOrEmpty() ||
                Patterns.EMAIL_ADDRESS.matcher(userLoginEmail).matches() || !userLoginPassword.isNullOrEmpty() || !(userLoginPassword.length > 6)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

}