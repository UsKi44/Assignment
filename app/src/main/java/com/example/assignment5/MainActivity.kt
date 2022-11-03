package com.example.assignment5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = BooksDbHelper(this)

        val bookName = findViewById<EditText>(R.id.bookName);
        val bookAuthor = findViewById<EditText>(R.id.bookAuthor);

        val addBtn = findViewById<Button>(R.id.addBtn)

        addBtn.setOnClickListener {
//      Book name
            val firstVal = bookName.text.toString();
//      Book author
            val secondVal = bookAuthor.text.toString();

            if(firstVal !== "" && secondVal !== "") {
                db.insertBook(firstVal,secondVal)
            }
        }

    }
}