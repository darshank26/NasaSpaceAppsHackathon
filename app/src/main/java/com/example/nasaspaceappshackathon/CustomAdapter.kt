package com.example.nasaspaceappshackathon

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class CustomAdapter(context: Context,arrayListDetails:ArrayList<FireData>) : BaseAdapter(){

    private val layoutInflater: LayoutInflater
    private val arrayListDetails:ArrayList<FireData>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails=arrayListDetails
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.tvEmail.text = "Frp: " +arrayListDetails.get(position).frp.toString()
        listRowHolder.tvDate.text = arrayListDetails.get(position).acqDate
        listRowHolder.tvTime.text = arrayListDetails.get(position).acqTime

        if(arrayListDetails.get(position).frp>20.0)
        {
            listRowHolder.linearLayout.setCardBackgroundColor(Color.parseColor("#C7FF6F6F"))
            listRowHolder.tvDate.setTextColor(Color.parseColor("#FFFFFF"))
            listRowHolder.tvTime.setTextColor(Color.parseColor("#FFFFFF"))
            listRowHolder.tvEmail.setTextColor(Color.parseColor("#FFFFFF"))

        }
        else
        {
            listRowHolder.linearLayout.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            listRowHolder.tvDate.setTextColor(Color.parseColor("#000000"))
            listRowHolder.tvTime.setTextColor(Color.parseColor("#000000"))
            listRowHolder.tvEmail.setTextColor(Color.parseColor("#000000"))
        }

        return view
    }
}

private class ListRowHolder(row: View?) {
    public val tvEmail: TextView
    public val tvDate: TextView
    public val tvTime: TextView

    public val linearLayout: CardView

    init {
        this.tvEmail = row?.findViewById<TextView>(R.id.tvEmail) as TextView
        this.tvDate = row?.findViewById<TextView>(R.id.tvdate) as TextView
        this.tvTime = row?.findViewById<TextView>(R.id.tvtime) as TextView

        this.linearLayout = row?.findViewById<CardView>(R.id.linearLayout) as CardView
    }
}