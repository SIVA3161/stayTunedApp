package com.sivag.staytuned.base

import androidx.fragment.app.Fragment

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */
abstract class BaseFragment : Fragment() {


    fun showKeyboard() {
        requireActivity().apply {
            val parent = activity as BaseActivity
            parent.showKeyboard()
        }
    }

    fun hideKeyboard() {
        requireActivity().apply {
            val parent = activity as BaseActivity
            parent.hideKeyboard()
        }
    }
}