package com.gnuoynawh.prj.exam.domain.file

import com.gnuoynawh.prj.exam.data.entity.File
import com.gnuoynawh.prj.exam.data.repository.FileRepository
import com.gnuoynawh.prj.exam.domain.UseCase

class AddFileItemUseCase(
    private val fileRepository: FileRepository
): UseCase {

    suspend operator fun invoke(file: File) {
        return fileRepository.addItem(file)
    }
}