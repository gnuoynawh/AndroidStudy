package com.gnuoynawh.prj.exam

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.prj.exam.databinding.RowFilesBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // 리스트 데이터
    private var fileList: List<File> = listOf()

    // 클릭 이벤트
    private lateinit var onItemClickListener: (file: File) -> Unit

    // ViewHolder 생성
    inner class MyViewHolder(
        private val binding: RowFilesBinding,
        val onItemClickListener: (file: File) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        // binding
        fun bindView(file: File) = with(binding) {

            // view
            idTextView.text = file.id.toString()
            fileNameTextView.text = file.fileName

            // event
            binding.root.setOnClickListener {
                onItemClickListener(file)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowFilesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(fileList[position])
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    // 데이터 셋팅 & refresh
    @SuppressLint("NotifyDataSetChanged")
    fun setData(fileList: List<File>, onItemClickListener: (file: File) -> Unit) {
        this.fileList = fileList
        this.onItemClickListener = onItemClickListener
        notifyDataSetChanged()
    }
}