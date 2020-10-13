package com.example.udacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class ColorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        

        val words = arrayListOf<Word>(Word("red", "красный", R.drawable.color_red), Word("green", "зеленый", R.drawable.color_green), Word("brown", "коричневый", R.drawable.color_brown),
            Word("gray", "серый", R.drawable.color_gray), Word("black", "черный", R.drawable.color_black), Word("white", "белый", R.drawable.color_white),
            Word("dusty yellow", "пыльно-желтый", R.drawable.color_dusty_yellow), Word("mustard yellow", "горчично-желтый", R.drawable.color_mustard_yellow))

        val adapter = WordAdapter(this, words, R.color.category_colors)
        val listView = findViewById<ListView>(R.id.list)
        listView.adapter = adapter
    }
}
