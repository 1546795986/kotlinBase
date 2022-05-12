package com.example.broadcasttest

import android.os.Bundle
import android.os.Looper
import android.os.Message
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Handler
import kotlin.concurrent.thread

class AndroidThreadTest:AppCompatActivity() {
    val updateText = 1
//    val handler = object  : Handler(){
//        override fun handleMessage(msg:Message){
//            when(msg.what){
//
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        fun sd(){
            thread {
                val msg = Message()
                msg.what = updateText

            }

        }
    }
}