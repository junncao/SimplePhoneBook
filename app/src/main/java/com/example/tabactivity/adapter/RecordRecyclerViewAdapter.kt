package com.example.phonebook.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabactivity.R
import com.example.tabactivity.beanclass.RecordGroup
import com.example.tabactivity.database.RegionQuery
import com.example.tabactivity.fragment.recordDetailDialogFragment
import kotlin.collections.ArrayList


class RecordRecyclerViewAdapter(private val recordData: ArrayList<RecordGroup>, private val context: Context?,val parentFragmentManager: FragmentManager): RecyclerView.Adapter<RecordRecyclerViewAdapter.RecordViewHolder>() {



    class RecordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: View =view
        val name: TextView = view.findViewById(R.id.name_record)
        val date: TextView = view.findViewById(R.id.date_record)
        val type: TextView = view.findViewById(R.id.type_record)
        val duration: TextView = view.findViewById(R.id.duration_record)
        val location: TextView = view.findViewById(R.id.location_record)
        val image_more:ImageView = view.findViewById(R.id.image_more)


        fun bind(group: RecordGroup, context: Context?,parentFragmentManager: FragmentManager) {
            val data = group.group[0]
            name.text = if(data.name!=null) data.name else data.number
            date.text = data.date

            type.text = when(data.type){
                1 -> "呼入"
                2 -> "呼出"
                3 -> "未接通"
                else -> "挂断"
            }
            duration.text = data.duration.toString()+"秒"


            location.text = RegionQuery.getReferenceRegion(data.number)

            item.setOnClickListener{

                val intent = Intent()
                intent.setAction(Intent.ACTION_CALL)
                val data = Uri.parse("tel:"+group.number)
                intent.setData(data)
                context?.startActivity(intent)


            }
            image_more.setOnClickListener {
                val recordDetail = recordDetailDialogFragment(group)
                recordDetail.show(parentFragmentManager,"record detail")
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_item, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) = holder.bind(recordData[position],context,parentFragmentManager)

    override fun getItemCount(): Int = recordData.size


}