package com.example.drinksahoy.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.drinksahoy.Beer
import com.example.drinksahoy.FIRST_INDEX

class DBHelper(context: Context) : SQLiteOpenHelper
    (context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "beerInfo.db"
        const val DATABASE_VERSION = 1
        const val COLUMN_ID = "itemID"
        const val COLUMN_BEER = "beerData"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_BEER_TABLE =
            "CREATE TABLE $DATABASE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_BEER TEXT)"
        db?.execSQL(CREATE_BEER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME")
        onCreate(db)
    }

    fun addBeer(beerData: Beer) {
        val values = ContentValues()
        values.put(COLUMN_BEER, beerData.toString())
        values.put(COLUMN_ID, beerData.id)
        val db = this.writableDatabase
        db.insert(DATABASE_NAME, null, values)
    }

    fun readBeer(): MutableList<Beer> {
        val sql = "select * from $DATABASE_NAME"
        val db = this.readableDatabase
        val storeBeers = arrayListOf<Beer>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor.getString(0))
                val beerData = cursor.getString(1)
                val beerArray = beerData.split(",")
                storeBeers.add(
                    Beer(
                        id, beerArray[0], beerArray[1], beerArray[2] as Double, beerArray[3],
                        beerArray[4], beerArray[5] as ArrayList<String>
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return storeBeers
    }
}
