package com.gnuoynawh.prj.mvvmexam.domain.audio

import androidx.lifecycle.LiveData
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio
import com.gnuoynawh.prj.mvvmexam.data.repository.AudioRepository
import com.gnuoynawh.prj.mvvmexam.domain.UseCase

class DeleteAudioItemUseCase(
    private val audioRepository: AudioRepository
): UseCase {

    suspend operator fun invoke(id: Long) {
        return audioRepository.deleteItem(id)
    }
}