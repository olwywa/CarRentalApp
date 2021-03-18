package com.example.carrental

object DatabaseQuery {

    // -- USERS
    const val TABLE_NAME = "Users"  // nazwa tabeli danych
    const val COLUMN_ID = "ID"
    const val COLUMN_NAME = "Name"
    const val COLUMN_SURNAME = "Surname"
    const val COLUMN_PASSWORD = "Password"
    const val COLUMN_NICKNAME = "Nickname"

    // -- ORDERS
    const val TABLE_ORDERS_NAME = "Orders"  // nazwa tabeli danych
    const val COLUMN_ID_ORDERS = "ID"
    const val COLUMN_STATUS = "IsOrderedStatus"
    const val COLUMN_CARID = "CarID"
    const val COLUMN_USERID = "UserID"

    val QUERY_CREATE_TABLE = "Create Table $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY ASC AUTOINCREMENT, " +
            " $COLUMN_NAME TEXT, $COLUMN_SURNAME TEXT, $COLUMN_NICKNAME TEXT, $COLUMN_PASSWORD TEXT)"

    val QUERY_CREATE_ORDERS_TABLE = "Create Table $TABLE_ORDERS_NAME($COLUMN_ID_ORDERS INTEGER PRIMARY KEY ASC AUTOINCREMENT, " +
            " $COLUMN_STATUS BOOLEAN, $COLUMN_CARID INTEGER, $COLUMN_USERID INTEGER)"

}