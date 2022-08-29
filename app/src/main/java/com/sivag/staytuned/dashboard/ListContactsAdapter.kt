package com.sivag.staytuned.dashboard

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.rvTitle
        var desc: TextView = itemView.rvDesc
        var profileImg: ImageView = itemView.rvContactImg
        var onlineIcon: ImageView = itemView.rcImgDot

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_contacts, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = contactsList?.data?.get(position)?.getFullName()
        holder.desc.text = contactsList?.data?.get(position)?.email

        if(contactsList?.data?.get(position)?.userId?.div(2) == 0) {
            holder.onlineIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_red_border_white))
        }else if(contactsList?.data?.get(position)?.userId?.div(3) == 0) {
            holder.onlineIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_yellow_border_white))
        }else {
            holder.onlineIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_accent_border_white))
        }

        val url = contactsList?.data?.get(position)?.avatar
        //Coil is an image loading 3rd party library which helps us to perform various img customization operations
        holder.profileImg.load(url) {
            placeholder(R.drawable.bg_leaf_img)
            transformations(CircleCropTransformation())
        }

    }

    override fun getItemCount(): Int {
        return contactsList?.data?.size?.toInt() ?: 0
    }
}