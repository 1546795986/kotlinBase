package com.example.broadcasttest

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

 object Utill:AppCompatActivity() {
    fun getOkhttp(){
        try {
            val client = OkHttpClient()
            val request  = Request.Builder()
                .url("htt")
                .build()
            val response = client.newCall(request).execute()
            val responseData = response.body?.string()
            if (responseData!=null) {
                //
            }
        }catch (e:Exception)
        {
            e.printStackTrace()
        }

    }
    fun paresJsoon(jsonData:String){
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length())
            {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name  = jsonObject.getString("name")
                val verson = jsonObject.getString("verson")

            }
        }catch (e:java.lang.Exception)
        {
            e.printStackTrace()
        }
    }
    fun solveWithGson(jsondata:String){
        val gson = Gson()
        val typeOf = object : TypeToken<List<App>>() {}.type
        val appList = gson.fromJson<List<App>>(jsondata,App::class.java)
        for (app in appList)
        {
            Log.d("TAG","is is ${app.id}")
        }

    }
     fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
         val client = OkHttpClient()
         val request = Request.Builder()
             .url(address)
             .build()
         client.newCall(request).enqueue(callback)
     }
}