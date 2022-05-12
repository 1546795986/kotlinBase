package com.example.broadcasttest

import android.app.backup.BackupAgent
import android.provider.ContactsContract
import okhttp3.CacheControl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface AppService {
    //GET http://example.com/get_data.json
    //表示当调用getAppData()方法时Retrofit会发起一条GET请求，地址就是我们在@GET注解中传入的具体参数
    @GET("get_data.json")
    fun getAppData(): Call<List<App>>
}
//GET http://example.com/<page>/get_data.json
interface ExampleService{
    @GET("{page}/get_data.json")
    fun getData(@Path("page") page:Int):Call<Date>
}
//GET http://example.com/get_data.json?u=<user>&t=<token>
interface ExampleService01{
    @GET("get_data.json")
    fun getData(@Query("u")user:String,@Query("t")token:String):Call<Date>
}
//DELETE http://example.com/data/<id>
interface ExampleService02{
    @DELETE("data/{id}")
    fun deleteData(@Path("id")id:String):Call<ResponseBody>

}
//POST http://example.com/data/create
//{"id": 1, "content": "The description for this data."}
interface ExampleServiceO3{
    @POST("data/create")
    fun create(@Body data: ContactsContract.Contacts.Data):Call<ResponseBody>

}
//GET http://example.com/get_data.json
//User-Agent: okhttp
//Cache-Control: max-age=0
interface ExampleService04{
    @GET("get_data.json")
    fun getData(@Header("User-Agent")userAgent: String,
    @Header("Cache-Control")cacheControl: String):Call<Date>

}