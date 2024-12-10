package com.example.tentcentproject

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await
import androidx.work.workDataOf
import com.example.tentcentproject.data.CopyWorker
import com.example.tentcentproject.data.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository,
//    private val workManager: WorkManager
): ViewModel() {
    fun insert(uri: Uri) {
        viewModelScope.launch {
            Timber.tag("MainViewModel").d("insert picture: $uri")
            galleryRepository.insert(uri, System.currentTimeMillis().toString())

//            val sourceImagePath = uri.toString()
//            val copyRequest = OneTimeWorkRequestBuilder<CopyWorker>()
//                .setInputData(workDataOf(
//                    "sourcePath" to sourceImagePath
//                ))
//                .setConstraints(
//                    Constraints.Builder()
//                    .build())
//                .build()
//
//            Timber.tag("MainViewModel").d("Built Request: $copyRequest")
//
//            try {
//                viewModelScope.launch(Dispatchers.IO) {
//                    workManager.getWorkInfoByIdLiveData(copyRequest.id).asFlow().collect { workInfo ->
//                        if (workInfo != null) {
//                            Timber.tag("WorkManager").d("Work status changed: ${workInfo.state}")
//                            Timber.tag("WorkManager").d("outputData: ${workInfo.outputData}")
//                        }
//                    }
//                }
//                workManager.enqueue(copyRequest)
//            } catch (e: Exception) {
//                Timber.tag("MainViewModel").e(e)
//            }
//            Timber.tag("MainViewModel").d("Enqueued Request: $copyRequest")

        }
    }



    fun getItems() = galleryRepository.getItems().cachedIn(viewModelScope)
}