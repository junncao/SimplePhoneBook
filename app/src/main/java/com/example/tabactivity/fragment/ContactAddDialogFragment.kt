package com.example.tabactivity.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.tabactivity.R
import com.example.tabactivity.database.AppDatabase
import com.example.tabactivity.beanclass.Contact

class ContactAddDialogFragment : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!);
        val inflater = activity?.getLayoutInflater();
        val view = inflater?.inflate(R.layout.fragment_dialog, null);
        builder.setView(view)

        builder.setPositiveButton("保存"){ dialogInterface: DialogInterface, i: Int ->
            val db = AppDatabase.getInstance(context!!)
            val name = view!!.findViewById<EditText>(R.id.name_dialog)?.text.toString()
            val personal_number = view?.findViewById<EditText>(R.id.personal_number)?.text.toString()
            val work_number = view?.findViewById<EditText>(R.id.work_number)?.text.toString()
            val home_number = view?.findViewById<EditText>(R.id.home_number)?.text.toString()
            if(name.isEmpty() || (personal_number.isEmpty() && work_number.isEmpty() && home_number.isEmpty())){
                Toast.makeText(context,"请输入正确的格式！",Toast.LENGTH_SHORT)
            }else{
                Log.d("Dialog","name:$name personal number:$personal_number" +
                        "work_numer:$work_number home_number:$home_number")
                db.contactDao.insert(Contact(name,personal_number,work_number, home_number))
                dismiss()
            }
        }
        builder.setNegativeButton("取消"){ dialogInterface: DialogInterface, i: Int ->
            dismiss()

        }
        return builder.create();
    }


}