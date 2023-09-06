package com.gnuoynawh.prj.mvvmexam.domain.audio

import androidx.lifecycle.LiveData
import com.gnuoynawh.prj.mvvmexam.data.entity.Audio
import com.gnuoynawh.prj.mvvmexam.data.repository.AudioRepository
import com.gnuoynawh.prj.mvvmexam.domain.UseCase

class UpdateAudioItemUseCase(
    private val audioRepository: AudioRepository
): UseCase {

    suspend operator fun invoke(audio: Audio) {
        return audioRepository.updateItem(audio)
    }
}