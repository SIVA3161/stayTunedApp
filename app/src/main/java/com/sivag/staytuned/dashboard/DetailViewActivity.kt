package com.sivag.staytuned.dashboard

import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import com.sivag.staytuned.R
import com.sivag.staytuned.base.BaseActivity
import com.sivag.staytuned.databinding.ActivityDetailProfileBinding
import kotlinx.android.synthetic.main.activity_detail_profile.*
import kotlinx.android.synthetic.main.content_profile.*

/**
 * Created by Siva G Gurusamy on 30/Aug/2022
 * email : sivaguru3161@gmail.com
 */

class DetailViewActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailProfileBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")

        binding.dtvUserID.text = fullname

        binding.dtvUserEmail.text = email

        binding.imgvUserProfileImg.load(avatar) {
            placeholder(R.drawable.bg_leaf_img)
            transformations(CircleCropTransformation())
        }

        dtvUserFullName.text = fullname
        dtvUserEmail.text = email
        dtvUserEmailContent.text = email


        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }


}