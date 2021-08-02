package com.example.sample

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val rowTextView: TextView = view.findViewById(R.id.rowTextView)
    val rowImageView: ImageView = view.findViewById(R.id.rowImageView)

    init {
        // layoutの初期設定
    }
}