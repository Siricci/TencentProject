package com.example.tentcentproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import timber.log.Timber

@Composable
fun ImagePickerButton(onImageSelected: (Uri) -> Unit) {
    val context = LocalContext.current as ComponentActivity // 获取 Activity 实例

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Timber.tag("ImagePickerButton").d("result: $result")

        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedUris = mutableListOf<Uri>()
            if (data != null) {
                if (data.clipData != null) { // 处理多选
                    for (i in 0 until data.clipData!!.itemCount) {
                        val uri = data.clipData!!.getItemAt(i).uri
                        selectedUris.add(uri)
                    }
                } else if (data.data != null) { // 处理单选 (兼容性)
                    selectedUris.add(data.data!!)
                }
            }
            for (uri in selectedUris) {
                onImageSelected(uri)
            }
        }
    }
    IconButton(onClick = {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/*" // 设置选择的文件类型为 image
            addCategory(Intent.CATEGORY_OPENABLE) //  添加 CATEGORY_OPENABLE，可以过滤不可打开的文件

            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        pickImageLauncher.launch(intent)
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add_button),
            contentDescription = "Filter Button"
        )
    }
}