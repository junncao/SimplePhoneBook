package com.example.tabactivity.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.tabactivity.R
import com.example.tabactivity.beanclass.Contact
import com.example.tabactivity.database.AppDatabase


class ContactDetailDialogFragment(val contact: Contact): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!);
        val inflater = activity?.getLayoutInflater();
        val view = inflater?.inflate(R.layout.fragment_contact_detail, null);
        builder.setView(view)
        val name_view = view?.findViewById<EditText>(R.id.name_dialog)
        val personal_number_view = view?.findViewById<EditText>(R.id.personal_number)
        val work_number_view = view?.findViewById<EditText>(R.id.work_number)
        val home_number_view = view?.findViewById<EditText>(R.id.home_number)

        personal_number_view?.apply {
            setText("私人电话：${contact.personal_number}")
            inputType = InputType.TYPE_NULL
            background = null
        }
        work_number_view?.apply {
            setText("工作电话：${contact.work_number}")
            inputType = InputType.TYPE_NULL
            background = null
        }
        home_number_view?.apply {
            setText("家庭电话：${contact.home_number}")
            inputType = InputType.TYPE_NULL
            background = null
        }
        name_view?.apply {
            setText("名字：${contact.name}")
            inputType = InputType.TYPE_NULL
            background = null
        }


        return builder.create()
    }









    }
