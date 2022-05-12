package com.example.broadcasttest

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class PhotoTake:AppCompatActivity() {
    val takePhoto =1
    lateinit var imageUri : Uri
    lateinit var outputImage:File
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

    }
    fun takePhotoBtn(view:View){
        outputImage = File(externalCacheDir,"output_image.jpg")
        if (outputImage.exists())
        {
            outputImage.delete()
        }
        outputImage.createNewFile()

    }
}