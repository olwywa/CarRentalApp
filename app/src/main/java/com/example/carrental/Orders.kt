package com.example.carrental

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.Toast
import com.example.carrental.model.Order

class Orders(val context: Context) {

    // pobieranie danych z bazy za pomocą obiektu Cursor

    private fun getOrdersCursor(): Cursor? {

        try {

            val db = DatabaseHelper(context).readableDatabase  // otworz dostep do bazy danych

            val columns = arrayOf<String>(
                DatabaseQuery.COLUMN_ID_ORDERS,
                DatabaseQuery.COLUMN_CARID,
                DatabaseQuery.COLUMN_USERID
            )

            return db.query(
                DatabaseQuery.TABLE_ORDERS_NAME,
                columns,
                null,
                null,
                null,
                null,
                DatabaseQuery.COLUMN_ID_ORDERS
            )

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return null
    }

    fun getOrders(): List<Order> {

        val list = ArrayList<Order>()

        try {

            val cursor =
                getOrdersCursor()  // pobranie cursora z poprzedniej metody i nastepnie dzialanie na tym cursorze
            // cursor = tablica z danymi przez ktore sobie po kolei przechodzimy

            if (cursor != null) {

                while (cursor.moveToNext()) {

                    val orderID =
                        cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_ID_ORDERS))
                    val carID = cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_CARID))
                    val userID = cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_USERID))

                    val order = Order(orderID, carID, userID)

                    list.add(order)
                }
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return list
    }

    private fun getOrderCursor(id: Int): Cursor? {

        try {

            val db = DatabaseHelper(context).readableDatabase  // otworz dostep do bazy danych

            val columns = arrayOf<String>(
                DatabaseQuery.COLUMN_ID_ORDERS,
                DatabaseQuery.COLUMN_CARID,
                DatabaseQuery.COLUMN_USERID
            )

            val selectionArgs = arrayOf<String>(id.toString())

            return db.query(
                DatabaseQuery.TABLE_ORDERS_NAME,
                columns,
                "${DatabaseQuery.COLUMN_ID_ORDERS} = ?",  // == where
                selectionArgs,
                null,
                null,
                DatabaseQuery.COLUMN_ID
            )

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return null
    }

    fun getOrder(id: Int): Order? {

        val cursor = getOrderCursor(id)

        if (cursor != null) {

            if (cursor.moveToNext()) {

                val orderID =
                    cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_ID_ORDERS))
                val carID = cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_CARID))
                val userID =
                    cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_USERID))

                val order = Order(orderID, carID, userID)

                return order
            }
        }
        return null
    }

    fun createOrder(carID: Int, userID: Int) {

        try {

            val db =
                DatabaseHelper(context).writableDatabase  // otworz dostep do bazy z mozliwoscia jej nadpisywania

            if (!db.isReadOnly) {

                val values = ContentValues()
                // przekazanie info
                values.put(DatabaseQuery.COLUMN_CARID, carID)
                values.put(DatabaseQuery.COLUMN_USERID, userID)

                db.insert(DatabaseQuery.TABLE_ORDERS_NAME, null, values)
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun deleteOrder(id: Int) {

        try {

            val db =
                DatabaseHelper(context).writableDatabase  // otworz dostep do bazy z mozliwoscia jej nadpisywania

            val selectionArgs = arrayOf<String>(id.toString())

            if (!db.isReadOnly) {
                db.delete(
                    DatabaseQuery.TABLE_ORDERS_NAME,
                    "${DatabaseQuery.COLUMN_ID_ORDERS} = ?",
                    selectionArgs
                )
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun getOrdersByUserID(userID: String): List<Order> {
        val list = ArrayList<Order>()

        try {
            val columns = arrayOf<String>(
                DatabaseQuery.COLUMN_ID_ORDERS,
                DatabaseQuery.COLUMN_CARID,
                DatabaseQuery.COLUMN_USERID
            )
            val db = DatabaseHelper(context).readableDatabase

            val selection = "${DatabaseQuery.COLUMN_USERID} = ?"
            val selectionArgs = arrayOf<String>(userID)

            val cursor = db.query(
                DatabaseQuery.TABLE_ORDERS_NAME, //Table to query
                columns,        //columns to return
                selection,      //columns for the WHERE clause
                selectionArgs,  //The values for the WHERE clause
                null,
                null,
                null
            )  //The sort order

            if (cursor != null) {

                while (cursor.moveToNext()) {

                    val orderID =
                        cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_ID_ORDERS))
                    val carID = cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_CARID))
                    val userID = cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_USERID))

                    val order = Order(orderID, carID, userID)

                    list.add(order)
                }
            }
            cursor.close()
            db.close()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        return list
    }
}