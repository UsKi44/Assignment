package com.example.assignment5

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class BooksDbHelper(context: Context) :
    SQLiteOpenHelper(context, DbConfig.DATABASE_NAME, null, DbConfig.VERSION) {

    companion object {
        private const val SQL_CREATE_TABLE = "CREATE TABLE ${Books.TABLE_NAME} " +
                "${Books.BOOK_COLUMNS.ID} INTEGER PRIMARY KEY, " +
                "${Books.BOOK_COLUMNS.BOOK_NAME} TEXT, " +
                "${Books.BOOK_COLUMNS.BOOK_AUTHOR} TEXT"

        private const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${Books.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_TABLE)
        onCreate(db)
    }

    //  Add book
    fun insertBook(bookName: String, author: String) {
        val value = ContentValues().apply {
            put(Books.BOOK_COLUMNS.BOOK_NAME, bookName)
            put(Books.BOOK_COLUMNS.BOOK_AUTHOR, author)
        }

        writableDatabase.insert(Books.TABLE_NAME, null, value)
    }

    //    Only update book author
    fun updateBookAuthor(bookId: Int, bookAuthor: String) {
        val cv = ContentValues().apply {
            put(Books.BOOK_COLUMNS.BOOK_AUTHOR, bookAuthor)
        }
        val where = "${Books.BOOK_COLUMNS.ID} = ?"
        val whereArgs = arrayOf(bookId.toString())

        writableDatabase.update(Books.TABLE_NAME, cv, where, whereArgs)
    }

    fun deleteBook(bookId: Int) {
        val where = "${Books.BOOK_COLUMNS.ID} = ?"
//        ეს ისევ სტრინგში რატომ გადაგვყავს არ ვიცი ისედაც სტრინგია.
        val whereArgs = arrayOf(bookId.toString())

        writableDatabase.delete(Books.TABLE_NAME, where, whereArgs)
    }

    fun deleteAll() {
        writableDatabase.delete(Books.TABLE_NAME, null, null)
    }
}