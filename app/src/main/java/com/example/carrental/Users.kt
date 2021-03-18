package com.example.carrental

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.Toast
import com.example.carrental.model.User

// pobiera dane, zapisuje je do bazy danych

class Users(val context: Context) {

    // pobieranie danych z bazy za pomocą obiektu Cursor

    private fun getUsersCursor(): Cursor? {

        try {

            val db = DatabaseHelper(context).readableDatabase  // otworz dostep do bazy danych

            val columns = arrayOf<String>(
                DatabaseQuery.COLUMN_ID,
                DatabaseQuery.COLUMN_NAME,
                DatabaseQuery.COLUMN_SURNAME,
                DatabaseQuery.COLUMN_NICKNAME,
                DatabaseQuery.COLUMN_PASSWORD
            )

            return db.query(
                DatabaseQuery.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                DatabaseQuery.COLUMN_ID
            )

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return null
    }

    fun getUsers(): List<User> {

        val list = ArrayList<User>()

        try {

            val cursor =
                getUsersCursor()  // pobranie cursora z poprzedniej metody i nastepnie dzialanie na tym cursorze
            // cursor = tablica z danymi przez ktore sobie po kolei przechodzimy

            if (cursor != null) {

                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_NAME))
                    val surname =
                        cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_SURNAME))
                    val nickname =
                        cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_NICKNAME))
                    val password =
                        cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_PASSWORD))

                    val user = User(id, name, surname, nickname, password)

                    list.add(user)

                }
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return list
    }

    private fun getUserCursor(id: Int): Cursor? {

        try {

            val db = DatabaseHelper(context).readableDatabase  // otworz dostep do bazy danych

            val columns = arrayOf<String>(
                DatabaseQuery.COLUMN_ID,
                DatabaseQuery.COLUMN_NAME,
                DatabaseQuery.COLUMN_SURNAME,
                DatabaseQuery.COLUMN_NICKNAME,
                DatabaseQuery.COLUMN_PASSWORD
            )

            val selectionArgs = arrayOf<String>(id.toString())

            return db.query(
                DatabaseQuery.TABLE_NAME,
                columns,
                "${DatabaseQuery.COLUMN_ID} = ?",  // == where
                selectionArgs,      // zwraca konkretnego uzytkownika dla danego ID
                null,
                null,
                DatabaseQuery.COLUMN_ID
            )

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return null

    }

    fun getUserByNameAndPassword(nickname: String, password: String): Int {

        val listUsersLogged = getUsers()
        for (item in listUsersLogged) {
            if (item.nickname.equals(nickname) && item.password.equals(password)) {
                return item.id
            }
        }
        return -1
    }

    fun getUser(id: Int): User? {

        val cursor = getUserCursor(id)

        if (cursor != null) {

            if (cursor.moveToNext()) {

                val id =
                    cursor.getInt(cursor.getColumnIndex(DatabaseQuery.COLUMN_ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_NAME))
                val surname = cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_SURNAME))
                val nickname =
                    cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_NICKNAME))
                val password =
                    cursor.getString(cursor.getColumnIndex(DatabaseQuery.COLUMN_PASSWORD))

                val user = User(id, name, surname, nickname, password)

                return user
            }
        }
        return null
    }

    // ------------------------
    // umieszczenie uzytkownika w bazie danych

    fun createUser(name: String, surname: String, nickname: String, password: String) {

        try {

            val db =
                DatabaseHelper(context).writableDatabase  // otworz dostep do bazy z mozliwoscia jej nadpisywania

            if (!db.isReadOnly) {

                val values = ContentValues()
                // przekazanie info
                values.put(DatabaseQuery.COLUMN_NAME, name)
                values.put(DatabaseQuery.COLUMN_SURNAME, surname)
                values.put(DatabaseQuery.COLUMN_NICKNAME, nickname)
                values.put(DatabaseQuery.COLUMN_PASSWORD, password)

                db.insert(DatabaseQuery.TABLE_NAME, null, values)
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    // manipulacja uzytkownikami

    fun updateUser(user: User) {

        val db = DatabaseHelper(context).writableDatabase

        if (!db.isReadOnly) {

            val values = ContentValues()
            // przekazanie info
            values.put(DatabaseQuery.COLUMN_NAME, user.name)
            values.put(DatabaseQuery.COLUMN_SURNAME, user.surname)
            values.put(DatabaseQuery.COLUMN_NICKNAME, user.nickname)
            values.put(DatabaseQuery.COLUMN_PASSWORD, user.password)

            val selectionArgs = arrayOf<String>(user.id.toString())

            db.update(
                DatabaseQuery.TABLE_NAME,
                values,
                "${DatabaseQuery.COLUMN_ID} = ?",
                selectionArgs
            )
        }
    }

    fun deleteUser(id: Int) {

        try {

            val db =
                DatabaseHelper(context).writableDatabase  // otworz dostep do bazy z mozliwoscia jej nadpisywania

            val selectionArgs = arrayOf<String>(id.toString())

            if (!db.isReadOnly) {
                db.delete(DatabaseQuery.TABLE_NAME, "${DatabaseQuery.COLUMN_ID} = ?", selectionArgs)
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun checkUserExists(nickname: String, password: String): Boolean {

        var flagBool: Boolean = false

        try {
            val columns = arrayOf(DatabaseQuery.COLUMN_ID)
            val db = DatabaseHelper(context).readableDatabase

            val selection =
                "${DatabaseQuery.COLUMN_NICKNAME} = ? AND ${DatabaseQuery.COLUMN_PASSWORD} = ?"
            val selectionArgs = arrayOf<String>(nickname, password)

            val cursor = db.query(
                DatabaseQuery.TABLE_NAME, //Table to query
                columns,        //columns to return
                selection,      //columns for the WHERE clause
                selectionArgs,  //The values for the WHERE clause
                null,
                null,
                null
            )  //The sort order

            val cursorCount = cursor.count
            cursor.close()
            db.close()

            if (cursorCount > 0) {
                flagBool = true
                return flagBool
            } else {
                flagBool = false
                return flagBool
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        return flagBool
    }
}