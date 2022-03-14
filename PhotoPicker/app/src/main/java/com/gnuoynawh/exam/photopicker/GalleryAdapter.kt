package com.gnuoynawh.exam.photopicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GalleryAdapter(private val context: Context,
                     private val list: ArrayList<String>)
    : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val layout: View = LayoutInflater.from(context).inflate(R.layout.row_gallery, parent, false)
        return GalleryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val url: String = list[position]

        // image
        Glide.with(context)
            .load(url)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            listener?.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: AppCompatImageView by lazy {
            itemView.findViewById(R.id.imageview)
        }
    }

    var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

}