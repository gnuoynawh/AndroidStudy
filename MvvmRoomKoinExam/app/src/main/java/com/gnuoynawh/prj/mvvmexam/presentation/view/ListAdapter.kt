package com.gnuoynawh.prj.mvvmexam.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio
import com.gnuoynawh.prj.mvvmexam.databinding.RowAudioBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // 리스트 데이터
    private var audioList: List<Audio> = listOf()

    // 클릭 이벤트
    private lateinit var onItemClickListener: (audio: Audio) -> Unit
    private lateinit var onItemUpdateListener: (audio: Audio) -> Unit
    private lateinit var onItemDeleteListener: (audio: Audio) -> Unit

    // ViewHolder 생성
    inner class MyViewHolder(
        private val binding: RowAudioBinding,
        val onItemClickListener: (audio: Audio) -> Unit,
        val onItemUpdateListener: (audio: Audio) -> Unit,
        val onItemDeleteListener: (audio: Audio) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        // binding
        fun bindView(audio: Audio) = with(binding) {

            // view
            idTextView.text = audio.id.toString()
            fileNameTextView.text = audio.fileName

            // event
            binding.root.setOnClickListener {
                onItemClickListener(audio)
            }

            binding.updateButton.setOnClickListener {
                onItemUpdateListener(audio)
            }

            binding.deleteButton.setOnClickListener {
                onItemDeleteListener(audio)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowAudioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view, onItemClickListener, onItemUpdateListener, onItemDeleteListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(audioList[position])
    }

    override fun getItemCount(): Int {
        return audioList.size
    }

    // 데이터 셋팅 & refresh
    @SuppressLint("NotifyDataSetChanged")
    fun setData(
        audioList: List<Audio>,
        onItemClickListener: (audio: Audio) -> Unit,
        onItemUpdateListener: (audio: Audio) -> Unit,
        onItemDeleteListener: (audio: Audio) -> Unit
    ) {
        this.audioList = audioList
        this.onItemClickListener = onItemClickListener
        this.onItemUpdateListener = onItemUpdateListener
        this.onItemDeleteListener = onItemDeleteListener
        notifyDataSetChanged()
    }
}