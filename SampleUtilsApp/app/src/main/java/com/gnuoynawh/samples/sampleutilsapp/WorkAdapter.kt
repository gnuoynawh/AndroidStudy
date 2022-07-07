package com.gnuoynawh.samples.sampleutilsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WorkAdapter(
    var context: Context,
    var list: ArrayList<Work>
): RecyclerView.Adapter<WorkAdapter.WorkView>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkView {
        val layout =  LayoutInflater.from(context).inflate(R.layout.row_work, parent, false)
        return WorkView(layout)
    }

    override fun onBindViewHolder(holder: WorkView, position: Int) {
        val work: Work = list[position]

        holder.title.text = work.title

        // 리스너
        holder.itemView.setOnClickListener {
            listener?.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    private var listener: OnItemClickListener? = null

    public fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class WorkView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.findViewById(R.id.tv_title)
    }
}