package com.example.tabactivity.fragment

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabactivity.R
import com.example.tabactivity.beanclass.Contact
import com.example.tabactivity.database.ViewModel
import com.example.tabactivity.ui.place.PlaceAdapter
import com.sunnyweather.android.ui.place.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_contact_detail.*
import kotlinx.android.synthetic.main.fragment_place.*


class ContactDetailDialogFragment(val contact: Contact): DialogFragment() {
    lateinit var viewModel: ViewModel
    private lateinit var adapter: PlaceAdapter
    lateinit var name_view: EditText
    lateinit var personal_number_view: EditText
    lateinit var work_number_view: EditText
    lateinit var home_number_view: EditText
    lateinit var see_weather:Button
    lateinit var left_btn:Button
    lateinit var right_btn:Button
    lateinit var personal_number_btn:ImageButton
    lateinit var work_number_btn:ImageButton
    lateinit var home_number_btn:ImageButton


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
            personal_number_btn=view?.findViewById(R.id.personal_number_btn)
            work_number_btn = view?.findViewById(R.id.work_number_btn)
            home_number_btn = view?.findViewById(R.id.home_number_btn)
            see_weather = view.findViewById(R.id.see_weather)
        }

        personal_number_view?.apply {
            setText("${contact.personal_number}")
            background = null
        }

        work_number_view?.apply {
            setText("${contact.work_number}")
            background = null
        }
        home_number_view?.apply {
            setText("${contact.home_number}")
            background = null
        }
        name_view?.apply {
            setText("${contact.name}")
            background = null
        }

        left_btn?.setOnClickListener {
            if(left_btn.text =="编辑" ){
                left_btn.text = "取消"
                right_btn?.visibility = View.VISIBLE

                personal_number_view?.apply {

                    background = originBackground
                }
                work_number_view?.apply {
                    background = originBackground

                }
                home_number_view?.apply {
                    background = originBackground

                }
                name_view?.apply {
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

                val modifiedContact = Contact(
                    now_name,
                    now_personal_number,
                    now_work_number,
                    now_home_number
                )
                viewModel.insertContact(modifiedContact)


            }else{

            }
            right_btn.visibility = View.GONE
            dismiss()

        }

        personal_number_btn.setOnClickListener {
            if(!contact.personal_number.isNullOrBlank()){
                call(contact.personal_number)
            }
        }

        work_number_btn.setOnClickListener {
            if(!contact.work_number.isNullOrBlank()){
                call(contact.work_number)
            }
        }

        home_number_btn.setOnClickListener {
            if(!contact.home_number.isNullOrBlank()){
                call(contact.home_number)
            }
        }

        see_weather.setOnClickListener {
            val intent = Intent(context,WeatherMainActivity::class.java)
            intent.putExtra("name",contact.name)
            var phone = ""
            if(contact.personal_number.isNullOrBlank()){
                if(contact.home_number.isNullOrBlank()){
                    phone = contact.work_number.toString()
                }else{
                    phone = contact.home_number
                }
            }else{
                phone = contact.personal_number
            }
            intent.putExtra("phone",phone)
            startActivity(intent)
        }






        return builder.create()
    }

    private fun call(number:String?){
        val intent = Intent()
        intent.setAction(Intent.ACTION_CALL)
        intent.setData(Uri.parse("tel:"+number))
        startActivity(intent)
    }


    }

