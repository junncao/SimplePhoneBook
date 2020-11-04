package com.example.tabactivity.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.tabactivity.R
import com.example.tabactivity.beanclass.Contact
import com.example.tabactivity.database.AppDatabase
import com.example.tabactivity.database.ViewModel


class ContactDetailDialogFragment(val contact: Contact): DialogFragment() {
    lateinit var viewModel: ViewModel


    lateinit var name_view: EditText
    lateinit var personal_number_view: EditText
    lateinit var work_number_view: EditText
    lateinit var home_number_view: EditText
    lateinit var left_btn:Button
    lateinit var right_btn:Button
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!);
        val inflater = activity?.getLayoutInflater();
        val view = inflater?.inflate(R.layout.fragment_contact_detail, null);
        builder.setView(view)

        viewModel = ViewModelProviders.of(activity!!).get(ViewModel::class.java)
        val originBackground = EditText(context!!).background
        if(view!=null){
            name_view = view?.findViewById<EditText>(R.id.name_dialog)
            personal_number_view = view?.findViewById<EditText>(R.id.personal_number)
            work_number_view = view?.findViewById<EditText>(R.id.work_number)
            home_number_view = view?.findViewById<EditText>(R.id.home_number)
            left_btn = view?.findViewById<Button>(R.id.left_btn)
            right_btn = view?.findViewById<Button>(R.id.right_btn)

        }

        personal_number_view?.apply {
            setText("${contact.personal_number}")
            inputType = InputType.TYPE_NULL
            background = null
        }
        work_number_view?.apply {
            setText("${contact.work_number}")
            inputType = InputType.TYPE_NULL
            background = null
        }
        home_number_view?.apply {
            setText("${contact.home_number}")
            inputType = InputType.TYPE_NULL
            background = null
        }
        name_view?.apply {
            setText("${contact.name}")
            inputType = InputType.TYPE_NULL
            background = null
        }

        left_btn?.setOnClickListener {
            if(left_btn.text =="编辑" ){
                left_btn.text = "取消"
                right_btn?.visibility = View.VISIBLE

                personal_number_view?.apply {
                    inputType = InputType.TYPE_CLASS_TEXT
                    background = originBackground
                }
                work_number_view?.apply {
                    inputType = InputType.TYPE_CLASS_TEXT
                    background = originBackground

                }
                home_number_view?.apply {
                    inputType = InputType.TYPE_CLASS_TEXT
                    background = originBackground

                }
                name_view?.apply {
                    inputType = InputType.TYPE_CLASS_TEXT
                    background = originBackground

                }
            }
            else{
                dismiss()
            }
        }

        right_btn?.setOnClickListener {
            val now_name = name_view?.text.toString()
            val now_personal_number = personal_number_view?.text.toString()
            val now_work_number = work_number_view?.text.toString()
            val now_home_number = home_number_view?.text.toString()
            if (now_name!=contact.name || now_personal_number!=contact.personal_number
                || now_work_number!=contact.work_number || now_home_number!=contact.home_number) {

                viewModel.deleteContact(contact)

                val modifiedContact = Contact(now_name,now_personal_number,now_work_number,now_home_number)
                viewModel.insertContact(modifiedContact)


            }else{

            }
            right_btn.visibility = View.GONE
            dismiss()

        }






        return builder.create()
    }









    }
