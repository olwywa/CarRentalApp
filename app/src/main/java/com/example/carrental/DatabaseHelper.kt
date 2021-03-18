package com.example.carrental

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(val context: Context?):
    SQLiteOpenHelper(context,
        context?.resources?.getString(R.string.database_name),
        null,
        context?.resources?.getInteger(R.integer.database_version) ?: 1
    )
{
    override fun onCreate(db: SQLiteDatabase?) {

        try {
            db!!.execSQL(DatabaseQuery.QUERY_CREATE_TABLE)
            db!!.execSQL(DatabaseQuery.QUERY_CREATE_ORDERS_TABLE)
        } catch (e: Exception) {

            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            Toast.makeText(context, context?.resources?.getString(R.string.error_database_access), Toast.LENGTH_LONG).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}