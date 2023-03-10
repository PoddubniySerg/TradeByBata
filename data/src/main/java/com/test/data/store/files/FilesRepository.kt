package com.test.data.store.files

import com.test.domain.exceptions.SaveFileException
import java.io.File

class FilesRepository {

    fun save(uri: String, content: ByteArray) {
        try {
            val directory = File(uri).parentFile
            directory?.let {
                if (!directory.exists() || !directory.isDirectory) directory.mkdirs()
                File(uri).writeBytes(content)
            }
        } catch (e: Exception) {
            throw SaveFileException("File not saved")
        }
    }

    fun remove(uri: String) {
        File(uri).delete()
    }
}