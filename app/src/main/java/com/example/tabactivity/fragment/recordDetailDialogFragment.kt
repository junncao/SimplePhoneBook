package com.example.tabactivity.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.adapter.ContactRecyclerViewAdapter
import com.example.tabactivity.R
import com.example.tabactivity.adapter.RecordDetailRecViewAdapter
import com.example.tabactivity.beanclass.RecordGroup

class recordDetailDialogFragment(val group:RecordGroup): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!);
        val inflater = activity?.getLayoutInflater();
        val view = inflater?.inflate(R.layout.fragment_record_detail, null);
        builder.setView(view)

        val viewManager = LinearLayoutManager(context)
        val viewAdapter = RecordDetailRecViewAdapter(group.group,parentFragmentManager)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.record_detail_recyclerview)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        builder.setNegativeButton("返回"){ dialogInterface: DialogInterface, i: Int ->
            dismiss()
        }



        return builder.create()
    }
}