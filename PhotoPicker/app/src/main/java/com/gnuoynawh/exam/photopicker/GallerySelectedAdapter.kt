package com.gnuoynawh.exam.photopicker

import android.content.Context
import android.graphics.Picture
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GallerySelectedAdapter(private val context: Context,
                             private val list: ArrayList<Photo>)
    : RecyclerView.Adapter<GallerySelectedAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val layout: View = LayoutInflater.from(context).inflate(R.layout.row_gallery_selected, parent, false)
        return GalleryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val p: Photo = list[position]

        // image
        Glide.with(context)
            .load(p.url)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            listener?.onClick(position)
        }

        // selected?
        holder.checkBox.isChecked = p.selected
        holder.checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            p.selected = isChecked
            checkListener?.onCheckedChanged(compoundButton, isChecked, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getCheckedCount(): Int {
        var count = 0
        for (p in list) {
            if (p.selected) count++
        }
        return count
    }

    fun setChecked(isChecked: Boolean, position: Int) {
        list[position].selected = isChecked
        notifyItemChanged(position)
    }

    fun getSelectedItems(): List<Photo> {
        val temp = ArrayList<Photo>()
        for (p in list) {
            if (p.selected) temp.add(p)
        }
        return temp
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: AppCompatImageView by lazy {
            itemView.findViewById(R.id.imageview)
        }

        val checkBox: AppCompatCheckBox by lazy {
            itemView.findViewById(R.id.checkbox)
        }
    }

    var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    var checkListener: OnCheckedChangeListener? = null

    fun setOnCheckedChangeListener(checkListener: OnCheckedChangeListener) {
        this.checkListener = checkListener
    }

    interface OnCheckedChangeListener {
        fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean, position: Int)
    }
}