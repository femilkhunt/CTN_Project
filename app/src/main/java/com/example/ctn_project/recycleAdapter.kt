package com.example.ctn_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class recycleAdapter(var dataList: ArrayList<dataSummaray>) : RecyclerView.Adapter<recycleAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var accType = view.findViewById<AppCompatTextView>(R.id.homeScreenDetailBtn)
            var password = view.findViewById<TextInputEditText>(R.id.homeScreenAccPasswordBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.define_recycle_view,parent,false)
            return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.accType.text = dataList[position].AccType
    }
}