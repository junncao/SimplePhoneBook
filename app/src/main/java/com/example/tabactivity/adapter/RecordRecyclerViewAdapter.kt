package com.example.phonebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.tabactivity.R
import com.example.tabactivity.database.Record

class RecordRecyclerViewAdapter(private val recordData:ArrayList<Record>): RecyclerView.Adapter<RecordRecyclerViewAdapter.RecordViewHolder>() {
    class RecordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: View =view
        val name: TextView = view.findViewById(R.id.name_record)
        val date: TextView = view.findViewById(R.id.date_record)
        val type: TextView = view.findViewById(R.id.type_record)
        val duration: TextView = view.findViewById(R.id.duration_record)
        val location: TextView = view.findViewById(R.id.location_record)


        fun bind(record: Record) {
            name.text = if(record.name!=null) record.name else record.number
            date.text = record.date

            type.text = when(record.type){
                1 -> "呼入"
                2 -> "呼出"
                3 -> "未接通"
                else -> "Unknown"
            }
            duration.text = record.duration.toString()+"秒"
            location.text = "Location"

            item.setOnClickListener{

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_item, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) = holder.bind(recordData[position])

    override fun getItemCount(): Int = recordData.size
}