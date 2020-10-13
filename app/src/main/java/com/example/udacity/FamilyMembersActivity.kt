package com.example.udacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class FamilyMembersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val words = arrayListOf<Word>(Word("father", "папа", R.drawable.family_father), Word("mother", "мама", R.drawable.family_mother), Word("son", "сын", R.drawable.family_son),
            Word("daughter", "дочь", R.drawable.family_daughter), Word("older brother", "старший брат", R.drawable.family_older_brother),
            Word("younger brother", "младший брат", R.drawable.family_younger_brother), Word("oldest sister", "старшая сестра", R.drawable.family_older_sister),
            Word("younger sister", "младшая сестра", R.drawable.family_younger_sister), Word("grandmother", "бабушка", R.drawable.family_grandmother),
            Word("grandfather", "дедеушка", R.drawable.family_grandfather))

        val adapter = WordAdapter(this, words, R.color.category_family)
        val listView = findViewById<ListView>(R.id.list)
        listView.adapter = adapter
    }
}
