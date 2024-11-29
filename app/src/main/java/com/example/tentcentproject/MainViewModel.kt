package com.example.tentcentproject

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.tentcentproject.data.GalleryRepository
import com.example.tentcentproject.data.model.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
): ViewModel() {
    suspend fun insert(uri: Uri, filename: String) = galleryRepository.insert(uri, filename)
}