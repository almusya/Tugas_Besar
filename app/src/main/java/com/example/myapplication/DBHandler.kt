package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

class DBHandler(context: Context , name : String? , factory: SQLiteDatabase.CursorFactory? , version: Int) : SQLiteOpenHelper(context , DATABASE_NAME , factory , DATABASE_VERSION) {

    companion object {

        private val DATABASE_NAME = "DataPet.db"
        private val DATABASE_VERSION = 1

        val COSTUMERS_TABLE_NAME = "Costumers"
        val COLUMN_COSTUMERID = "costumerid"
        val COLUMN_COSTUMERNAME = "costumername"
        val COLUMN_MAXCREDIT = "maxcredit"
    }


    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_COSTUMER_TABLE = ("CREATE TABLE $COSTUMERS_TABLE_NAME" +
                "(" + "$COLUMN_COSTUMERID INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "$COLUMN_COSTUMERNAME TEXT , "
                + "$COLUMN_MAXCREDIT DOUBLE DEFAULT 0)")


        db?.execSQL(CREATE_COSTUMER_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getCostumers(mCtx: Context): ArrayList<Costumer> {
        val query = "select * from $COSTUMERS_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val customers = ArrayList<Costumer>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "Tidak Ada Record ", Toast.LENGTH_SHORT).show()
        else {
            while (cursor.moveToNext()) {
                val costumer = Costumer()
                costumer.costumerId = cursor.getInt(cursor.getColumnIndex(COLUMN_COSTUMERID))
                costumer.costumerName = cursor.getString(cursor.getColumnIndex(COLUMN_COSTUMERNAME))
                costumer.maxCredit = cursor.getDouble(cursor.getColumnIndex(COLUMN_MAXCREDIT))
                customers.add(costumer)
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Data Ditemukan ", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return customers
    }

    fun AddCustomer(mCtx: Context , costumer: Costumer){
        val values = ContentValues()
        values.put(COLUMN_COSTUMERNAME,costumer.costumerName)
        values.put(COLUMN_MAXCREDIT,costumer.maxCredit)
        val db = this.writableDatabase
        try {
            db.insert(COSTUMERS_TABLE_NAME, null , values)

            Toast.makeText(mCtx , "Costumer Ditambahkan", Toast.LENGTH_SHORT).show()
        }catch (e : Exception){
            Toast.makeText(mCtx, e.message , Toast.LENGTH_SHORT).show()
        }
        db.close()

    }
}

