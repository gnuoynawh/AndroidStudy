package com.gnuoynawh.prj.exam.domain.file

import com.gnuoynawh.prj.exam.data.repository.FileRepository
import com.gnuoynawh.prj.exam.domain.UseCase

class GetFileItemUseCase(
    private val fileRepository: FileRepository
): UseCase {

    suspend operator fun invoke(id: Long) {
        fileRepository.getItem(id)
    }
}