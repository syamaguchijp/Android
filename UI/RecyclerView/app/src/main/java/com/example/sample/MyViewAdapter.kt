package com.example.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyViewAdapter(private val context: Context, private val itemClickListener: ItemClickListener,
                    private val list: List<RowData>) : RecyclerView.Adapter<MyViewHolder>() {

    // タップ時のリスナー
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private var mRecyclerView : RecyclerView? = null
    private var rowDataList: List<RowData> = list

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        Logging.d("")
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {

        Logging.d("")
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    // レイアウトの設定
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        Logging.d("")
        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.row, parent, false)
        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }
        return MyViewHolder(mView)
    }

    // Viewの設定
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Logging.d("")
        holder?.let {
            val rowData = rowDataList.get(position)
            it.rowTextView.text = rowData.title
            Glide.with(context).load(rowData.user.profile_image_url).into(it.rowImageView)
        }
    }

    override fun getItemCount(): Int {

        Logging.d("")
        return rowDataList.size
    }

    override fun onViewRecycled(holder: MyViewHolder) {

        Logging.d("")
        super.onViewRecycled(holder)
        holder.rowTextView.text = ""
        Glide.with(context).clear(holder.rowImageView)
    }
}