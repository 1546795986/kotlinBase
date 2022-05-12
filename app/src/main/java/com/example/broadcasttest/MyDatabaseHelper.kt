package com.example.broadcasttest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context:Context,name :String,version:Int):SQLiteOpenHelper(context,name,null,version) {

    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text," +
            "category_id integer)"
    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(createBook)
        db!!.execSQL(createCategory)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p1<=1){
            db!!.execSQL(createCategory)
        }
        if (p1<=2){
            db!!.execSQL("alter table Book add column category_id integer")
        }
        db!!.execSQL("drop table if exists Book")
        db!!.execSQL("drop table if exists Category")
        db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)", arrayOf("The Da Vinci Code", "Dan Brown", "454", "16.96"))
        db.execSQL("delete from Book where pages > ?", arrayOf("500"))
        val cursor = db.rawQuery("select * from Book", null)
        
        onCreate(db)

    }


}