package com.gnuoynawh.prj.exam

public interface MainContract {

    interface View {
        fun updateList(list: List<File>)
        fun showToast(text: String)
    }

    interface Presenter {
        fun addItem()
    }
}