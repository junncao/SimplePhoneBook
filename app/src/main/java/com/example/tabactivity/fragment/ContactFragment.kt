package com.example.tabactivity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.adapter.ContactRecyclerViewAdapter
import com.example.phonebook.adapter.RecordRecyclerViewAdapter
import com.example.tabactivity.R
import com.example.tabactivity.database.AppDatabase
import com.example.tabactivity.database.Contact
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_contact.*


class ContactFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var contactList :List<Contact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contact, container, false)
        getContacts()
        viewManager = LinearLayoutManager(context)
        viewAdapter = ContactRecyclerViewAdapter(contactList)

        recyclerView = root.findViewById<RecyclerView>(R.id.contacts_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return root
    }

    private fun getContacts() {
        val db = AppDatabase.getInstance(context!!)
        contactList = db.contactDao.getAll()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener{
            val editDialog = ContactAddDialogFragment()
            editDialog.show(parentFragmentManager,"Add Contact")
        }
    }


}