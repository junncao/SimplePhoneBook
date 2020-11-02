package com.example.phonebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tabactivity.R
import com.example.tabactivity.database.Contact


class ContactRecyclerViewAdapter(): RecyclerView.Adapter<ContactRecyclerViewAdapter.ContactViewHolder>() {

    lateinit var contactsData: List<Contact>
    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: View =view
        val image: ImageView = view.findViewById(R.id.contact_image)
        val name: TextView = view.findViewById(R.id.contact_name)
        val number: TextView = view.findViewById(R.id.contact_number)

        fun bind(contact: Contact) {
            name.text = contact.name
            number.text = contact.personal_number
            item.setOnClickListener{

            }

        }


    }


    fun setData(contactList: List<Contact>){
        this.contactsData = contactList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(contactsData.get(position))

    override fun getItemCount(): Int = contactsData.size
}