package com.example.tentcentproject

import androidx.lifecycle.ViewModel
import com.example.tentcentproject.data.GalleryRepository
import com.example.tentcentproject.data.model.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
): ViewModel() {
    suspend fun getAll() = galleryRepository.getAll()
    suspend fun setUri(id: Int, uri: String) = galleryRepository.setUri(id, uri)
    suspend fun insert(picture: Picture) = galleryRepository.insert(picture)
    suspend fun update(picture: Picture) = galleryRepository.update(picture)
}