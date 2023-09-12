package com.gnuoynawh.prj.exam

import com.gnuoynawh.prj.exam.data.entity.File
import kotlinx.coroutines.Job

public interface MainContract {

    interface View {
        fun updateList(list: List<File>)
        fun showToast(text: String)
    }

    interface Presenter {
        fun addItem()
        fun updateItem(file: File)
        fun deleteItem(file: File)
    }
}