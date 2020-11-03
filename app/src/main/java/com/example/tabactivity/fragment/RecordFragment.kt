package com.example.tabactivity.fragment

import android.database.Cursor
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.adapter.RecordRecyclerViewAdapter
import com.example.tabactivity.R
import com.example.tabactivity.beanclass.Record
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [RecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecordFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val callUri: Uri = CallLog.Calls.CONTENT_URI
    private val recordList = ArrayList<Record>()
    private val columns = arrayOf(
        CallLog.Calls.CACHED_NAME // 通话记录的联系人
        , CallLog.Calls.NUMBER // 通话记录的电话号码
        , CallLog.Calls.DATE // 通话记录的日期
        , CallLog.Calls.DURATION // 通话时长
        , CallLog.Calls.TYPE
    ) // 通话类型}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_record, container, false)
        getContentCallLog()
        viewManager = LinearLayoutManager(context)
        viewAdapter = RecordRecyclerViewAdapter(recordList)

        recyclerView = root.findViewById<RecyclerView>(R.id.record_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return root
    }


    private fun getContentCallLog() {
        val cursor: Cursor? = context!!.getContentResolver().query(
            callUri,  // 查询通话记录的URI
            columns, null, null, CallLog.Calls.DEFAULT_SORT_ORDER // 按照时间逆序排列，最近打的最先显示
        )
        Log.i("RecordFragment", "cursor count:" + (cursor?.getCount() ?: "null"))
        if (cursor != null) {
            while (cursor.moveToNext()) {

                val name: String? =
                    cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)) //姓名
                val number: String = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)) //号码
                val dateLong: Long = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE)) //获取通话日期
                val date: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(dateLong))
                val duration: Int? =
                    cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION)) //获取通话时长，值为多少秒
                val type: Int =
                    cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE)) //获取通话类型：1.呼入2.呼出3.未接
                val temp = Record(name, number, date, duration, type)
                recordList.add(temp)

                Log.i(
                    "RecordFragment", temp.toString())


            }
        }
    }



}