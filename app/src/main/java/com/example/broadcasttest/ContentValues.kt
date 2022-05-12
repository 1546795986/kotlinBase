package com.example.broadcasttest

import android.content.ContentValues

fun cvOf(vararg pairs: Pair<String,Any?>):ContentValues{
    //实vararg对应的就是Java中的可变参数
    //列表，我们允许向这个方法传入0个、1个、2个甚至任意多个Pair类型的参数
    val cv = ContentValues()
    for (pair in pairs){
        val key = pair.first
        val values = pair.second
        when(values){
            is Int -> cv.put(key, values)
            is Long -> cv.put(key, values)
            is Short -> cv.put(key, values)
            is Float -> cv.put(key, values)
            is Double -> cv.put(key, values)
            is Boolean -> cv.put(key, values)
            is String -> cv.put(key, values)
            is Byte -> cv.put(key, values)
            is ByteArray -> cv.put(key, values)
            null -> cv.putNull(key)
        }
    }
    return cv
}