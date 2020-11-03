package com.example.tabactivity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.adapter.ContactRecyclerViewAdapter
import com.example.tabactivity.R
import com.example.tabactivity.beanclass.Contact
import com.example.tabactivity.database.ViewModel
import kotlinx.android.synthetic.main.fragment_contact.*


class ContactFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ContactRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var contactList :LiveData<List<Contact>>
    lateinit var viewModel: AndroidViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contact, container, false)
        viewManager = LinearLayoutManager(context)
        viewAdapter = ContactRecyclerViewAdapter(parentFragmentManager)
        recyclerView = root.findViewById<RecyclerView>(R.id.contacts_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener{
            val editDialog = ContactAddDialogFragment()
            editDialog.show(parentFragmentManager,"Add Contact")
        }

        viewModel = ViewModelProviders.of(activity!!).get(ViewModel::class.java)
        (viewModel as ViewModel).getContacts().observe(viewLifecycleOwner){
            if (it.size>0){
                viewAdapter.setData(it)
                recyclerView.adapter = viewAdapter
            }
        }

    }


}