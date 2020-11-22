package com.example.tabactivity.adapter

import android.icu.text.AlphabeticIndex
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.adapter.ContactRecyclerViewAdapter
import com.example.phonebook.adapter.RecordRecyclerViewAdapter
import com.example.tabactivity.R
import com.example.tabactivity.beanclass.Record
import com.example.tabactivity.database.RegionQuery

class RecordDetailRecViewAdapter(val groupList: List<Record>, val parentFragmentManager: FragmentManager): RecyclerView.Adapter<RecordDetailRecViewAdapter.RecordDetailViewHolder>()  {
    class RecordDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date_record)
        val type: TextView = view.findViewById(R.id.type_record)
        val duration: TextView = view.findViewById(R.id.duration_record)
        val location: TextView = view.findViewById(R.id.location_record)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordDetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_item_recycle, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return RecordDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordDetailViewHolder, position: Int) {
        val data = groupList[position]
        holder.date.text = data.date
        holder.type.text = when(data.type){
            1 -> "呼入"
            2 -> "呼出"
            3 -> "未接通"
            else -> "挂断"
        }
        holder.duration.text = data.duration.toString()+"秒"


        holder.location.text = RegionQuery.getReferenceRegion(data.number)

    }

    override fun getItemCount(): Int {
        return groupList.size
    }
}