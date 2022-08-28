package com.sivag.staytuned.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.sivag.staytuned.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.item_register_layout.*

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */

class RegisterFragment : BaseFragment() {

    private lateinit var parentActivity : MainActivity
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel : RegisterViewModel
    private var navHostFragment : NavHostFragment? = null
    private var navController : NavController? = null


    private var userFullName: String = ""
    private var userEmail: String = ""
    private var userPassword: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(RegisterViewModel::class.java)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parentActivity = activity as MainActivity

        navHostFragment = parentActivity.supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment!!.navController

        getUserEnteredData()

        viewModel.errorToastUsername.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Email Already Registered", Toast.LENGTH_SHORT).show()
            }
        })


        viewModel.userDetailsLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("UserDetails","$it")
        })

        viewModel.isSuccessfulRegistration.observe(viewLifecycleOwner, Observer { isSuccessfulReg ->
            if (isSuccessfulReg == true) {
                navController!!.navigate(R.id.loginFragment)
                Toast.makeText(requireContext(),"Registration Successful! \nKindly login with your credentials",Toast.LENGTH_LONG).show()
            }

        })

        viewModel.users.observe(viewLifecycleOwner,Observer {
            println("List of registered Users : $it")
        })


        btnAlreadyHaveAccount.setOnClickListener {
            val navHostFragment = parentActivity.supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
            val navController = navHostFragment.navController

            navController.navigate(R.id.loginFragment)
        }

    }


    private fun getUserEnteredData() {
        dtvFullnameRegisterEt.addTextChangedListener(textWatcher)
        dtvEmailRegisterEt.addTextChangedListener(textWatcher)
        dtvPasswordRegisterEt.addTextChangedListener(textWatcher)

        btnRegister.setOnClickListener {
            if (formValidation()) {
                viewModel.saveUserToRDB(userFullName = userFullName, userEmail = userEmail, userPassword = userPassword)
            } else {
                if (userFullName.isNullOrEmpty()) dtvFullnameRegisterEt.error = getString(com.sivag.staytuned.R.string.empty_fullname)
                else if (userEmail.isNullOrEmpty()) dtvEmailRegisterEt.error = getString(com.sivag.staytuned.R.string.empty_email)
                else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) dtvEmailRegisterEt.error = getString(com.sivag.staytuned.R.string.invalid_email_address)
                else if (userPassword.isNullOrEmpty()) dtvPasswordRegisterEt.error = getString(com.sivag.staytuned.R.string.empty_password)
                else return@setOnClickListener
            }
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            userFullName = dtvFullnameRegisterEt.text.toString().trim()
            userEmail = dtvEmailRegisterEt.text.toString().trim()
            userPassword = dtvPasswordRegisterEt.text.toString().trim()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    private fun formValidation(): Boolean {

        return !userFullName.isNullOrEmpty() || !userEmail.isNullOrEmpty() ||
                Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() || !userPassword.isNullOrEmpty() || userPassword.length > 6
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}