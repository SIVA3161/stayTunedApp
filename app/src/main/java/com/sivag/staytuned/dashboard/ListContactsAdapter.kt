package com.sivag.staytuned.dashboard

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.sivag.staytuned.R
import com.sivag.staytuned.data.model.ContactsModel
import com.sivag.staytuned.data.model.Data
import kotlinx.android.synthetic.main.item_contacts.view.*

/**
 * Created by Siva G Gurusamy on 28/Aug/2022
 * email : sivaguru3161@gmail.com
 */
class ListContactsAdapter(private val context: Context, private val contactsList: ContactsModel?) : RecyclerView.Adapter<ListContactsAdapter.ViewHolder> () {

    private lateinit var mListener: onItemClickListener

    interface  onItemClickListener {
        fun onItemClick(position: Int, )
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class ViewHolder(itemView: View, listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, data: Data) {
            val title: TextView = itemView.rvTitle
            val desc: TextView = itemView.rvDesc
            val profileImg: ImageView = itemView.rvContactImg
            val onlineIcon: ImageView = itemView.rcImgDot

            title.text = data.getFullName()
            desc.text =  data.email

            if(data.userId.div(2) == 0) {
                onlineIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_red_border_white))
            }else if(data.userId.div(3) == 0) {
                onlineIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_yellow_border_white))
            }else {
                onlineIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_accent_border_white))
            }

            val url = data.avatar
            //Coil is an image loading 3rd party library which helps us to perform various img customization operations
            profileImg.load(url) {
                placeholder(R.drawable.bg_leaf_img)
                transformations(CircleCropTransformation())
            }


        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_contacts, parent, false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val user = contactsList?.data?.get(position)
        contactsList?.data?.get(position)?.let { holder.bind(context,it) }

    }

    override fun getItemCount(): Int {
        return contactsList?.data?.size?.toInt() ?: 0
    }
}