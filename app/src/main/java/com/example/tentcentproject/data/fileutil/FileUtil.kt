package com.example.tentcentproject.data.fileutil

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class FileUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // 给出图片的文件路径
    private val externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    /**
     * store file from uri
     * @param uri src file
     * @param filename dest file name
     */
    suspend fun storePictureFromUri(uri: Uri, filename: String): String? = withContext(Dispatchers.IO) {
        val contentResolver = context.contentResolver

        val destinationFile = File(externalFilesDir, filename)

        try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val outputStream: OutputStream = FileOutputStream(destinationFile)

            inputStream?.use { input ->
                outputStream.use {output ->
                    input.copyTo(output)
                }
            }

            return@withContext filename
        } catch (e : Exception) {
            //TODO specify the exception
            e.printStackTrace()
            return@withContext null
        }
    }

    /**
     * make uri from filename
     * @param filename file name
     */
    fun makeUriFromFilename(filename: String): Uri {
        return Uri.fromFile(File(externalFilesDir, filename))
    }
}