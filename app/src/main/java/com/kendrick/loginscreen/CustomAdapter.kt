package com.kendrick.loginscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(var context: Context, var data:ArrayList<Product>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtName:TextView
        var mTxtQtty:TextView
        var mTxtPrice:TextView
        init {
            this.mTxtName = row?.findViewById(R.id.mTvName) as TextView
            this.mTxtQtty = row?.findViewById(R.id.mTvQtty) as TextView
            this.mTxtPrice = row?.findViewById(R.id.mTvPrice) as TextView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.item_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Product = getItem(position) as Product
        viewHolder.mTxtName.text = item.name
        viewHolder.mTxtQtty.text = item.qtty
        viewHolder.mTxtPrice.text = item.price
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}