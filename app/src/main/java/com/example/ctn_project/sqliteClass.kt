package com.example.ctn_project

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqliteClass(context : Context, nothing: Nothing?) : SQLiteOpenHelper(context, DATABASE,null ,VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ACC_TYPE + " TEXT, " +
                USER_NAME + " TEXT," +
                ACC_PASS + " TEXT" + ")")

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addData(accType: String, userEmail: String, accPassword: String) {
        var values = ContentValues()

        values.put(ACC_TYPE,accType)
        values.put(USER_NAME,userEmail)
        values.put(ACC_PASS,accPassword)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getData(): Cursor? {
        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }


        companion object{
            var TABLE_NAME = "Datas"

            var ACC_TYPE = "ACCOUNT TYPE"

            var USER_NAME = "EMAIL USERNAME"

            var ACC_PASS = "PASSWORD"

            val DATABASE = "DATAAS"

            val VERSION = 1
        }

}