package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.lang.StringBuilder

open class BaseActivitu:AppCompatActivity() {
    fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
        val result = operation(num1, num2)
        return result
    }

    lateinit var receiver: ForceReceiver
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("")
        receiver = ForceReceiver()
        registerReceiver(receiver, intentFilter)


    }
    fun save(inputText:String){
        try {
            val output = openFileOutput("data",Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        }catch (e:IOException)
        {
            e.printStackTrace()
        }
    }
    fun load():String{
        val content = StringBuilder()
        try {
            val  input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e:IOException)
        {
            e.printStackTrace()
        }
        return content.toString()
    }
    fun saveButton(){
        val editor  = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
        editor.putBoolean("Like",false)
        editor.putString("name", "Tom")
        editor.putInt("age", 28)
        editor.putBoolean("married", false)
        editor.apply()
    }
    fun restoreButton(){
        val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
        val name = prefs.getString("name","")
        val age  =  prefs.getInt("age",10)
        Log.d("tag","name is $name")
    }
    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            AlertDialog.Builder(this@BaseActivitu).apply {
                setTitle("Warning")
                setMessage("You are forced to be offline. Please try to login again.")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    ActivityCollector.finish()// 销毁所有Activity
                    val i = Intent(context, LoginActivity::class.java)
                    context.startActivity(i) // 重新启动LoginActivity
                }
                show()
            }

        }
    }
}