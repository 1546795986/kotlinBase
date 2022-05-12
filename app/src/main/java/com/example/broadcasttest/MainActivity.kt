package com.example.broadcasttest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.lang.NullPointerException
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    val a=1
    val b= 3
    val c= 4
    val d = max(a,b,c)
    lateinit var downloadBinder :MyService.DownloadBinder
    lateinit var timeChangeReceiver:TimeChangeReceiver
    private val connection = object :ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            downloadBinder = p1 as MyService.DownloadBinder
            downloadBinder.a()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

    }
    fun getRetifitPrivate() {
        val appService11= ServiceCreator.create(AppService::class.java)


    }

    fun getRetrofit(){
        val retrofit=Retrofit.Builder()
        .baseUrl("htt")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val appService = retrofit.create(AppService::class.java)
        appService.getAppData().enqueue(object :Callback<List<App>>{
            override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                val list = response.body()
                if (list!=null)
                {
                    for (app in list)
                    {
                        Log.d("TAG","id is ${app.id}")
                    }
                }
            }

            override fun onFailure(call: Call<List<App>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent2 = Intent(this,MyService::class.java)
        bindService(intent2,connection,Context.BIND_AUTO_CREATE)
        unbindService(connection)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        setContentView(R.layout.activity_main)
        fun sendNotification(){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
                    manager.createNotificationChannel(channel)
            }
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("This is a Title")
                .setContentText("This is a text")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_foreground))
                .build()
            manager.notify(1,notification)
        }
        fun pendingIntent(){
            val intent = Intent(this,NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this,0,intent,0)
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("This is a Title")
                .setContentText("This is a text")
                .setStyle(NotificationCompat.BigTextStyle().bigText("henda "))
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources,   R.drawable.ic_launcher_foreground)))

                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_foreground))
                .setContentIntent(pi)
                .setAutoCancel(true)
                    /*
                    * 或者manger.cancel(1)
                    * */
                .build()
            manager.notify(1,notification)

        }
        fun highIntentPending(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val  channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
                manager.createNotificationChannel(channel)
                val channel2 = NotificationChannel("important","Important",NotificationManager.IMPORTANCE_HIGH)
                manager.createNotificationChannel(channel2)
                fun sendNotice(){
                    val intent = Intent(this,NotificationActivity::class.java)
                    val pi = PendingIntent.getActivity(this,0,intent,0)
                    val notification = NotificationCompat.Builder(this,"imporant")
                        .setContentIntent(pi)
                        .build()
                    manager.notify(2,notification)
                    
                }
            }
        }
        val values = cvOf("name" to "Game of Thrones","author" to "George Martin")
        getSharedPreferences("data",Context.MODE_PRIVATE).open {
            putBoolean("ok",true)
            putBoolean("married",false)
        }
        try {
            val intent1 = Intent(Intent.ACTION_CALL)
            intent1.data = Uri.parse("tel:10086")
            startActivity(intent1)
        }catch (e:SecurityException)
        {
            e.printStackTrace()
        }

        fun call() {

            
        }

        fun makeCall(){
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
//            }else call()
        }


        val dbHe = MyDatabaseHelper(this,"Okk",3)
        fun kiss(){
            val db = dbHe.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book",null,null)
                if (true){
                    throw NullPointerException()
                }
                val valuse = ContentValues().apply {
                    put("name","Game of Threons")
                    put("author","sadfas")
                    put("pages",232)
                }
                db.insert("Book",null,valuse)
                db.setTransactionSuccessful()
            }catch (e:Exception)
            {
                e.printStackTrace()
            }finally {
                db.endTransaction()
            }

        }
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)
        val intent = Intent("com.example.broadcasttest.MY_BROADCAST")
        intent.setPackage(packageName)
        sendBroadcast(intent)
        val intent1 = Intent("com.example.broadcastbestpractice.FORCE_OFFLINE")
        sendBroadcast(intent1)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        dbHelper.writableDatabase
    }
    fun dbCreate(){
        val dbHelper = MyDatabaseHelper(this,"Bookstore.db",1)
        val db = dbHelper.writableDatabase
        val values1 =ContentValues().apply {
            put("name","The Da Vinci Code")
            put("author","DanBrown")
        }
        db.insert("Book",null,values1)
        val values2 = ContentValues().apply {
            // 开始组装第二条数据
            put("name", "The Lost Symbol")
            put("author", "Dan Brown")
            put("pages", 510)
            put("price", 19.95)
        }
        db.insert("Book", null, values2) // 插入第二条数据
        val values3 = ContentValues().apply {
            put("price",10.55)
        }
        db.update("Book",values3,"name=?", arrayOf("The Da Vinci Code"))
        db.delete("Book","pages>?", arrayOf("600"))
        val cursor = db.query("Book", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val columnIndex = cursor.getColumnIndex("name")
                val name = cursor.getString(columnIndex)
                val columnIndex1 = cursor.getColumnIndex("author")
                val author = cursor.getString(columnIndex)
                val columnIndex2 = cursor.getColumnIndex("price")
                val price = cursor.getInt(columnIndex2)


            }while (cursor.moveToNext())
        }
        cursor.close()

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(this@MainActivity, "Time has changed", Toast.LENGTH_SHORT).show()
        }

    }
}


