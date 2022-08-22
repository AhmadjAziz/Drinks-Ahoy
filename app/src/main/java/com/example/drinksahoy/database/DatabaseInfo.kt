package com.example.drinksahoy.database

import android.provider.BaseColumns

object DatabaseInfo {

    const val SQL_CREATE_TABLE_QUERY =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
                "${TableInfo.COLUMN_ITEM_ID} INTEGER PRIMARY KEY," +
                "${TableInfo.COLUMN_BEER} TEXT)"


    object TableInfo: BaseColumns {
        const val TABLE_NAME = "beerDownload"
        const val COLUMN_ITEM_ID = "itemID"
        const val COLUMN_BEER = "beerData"
    }
}