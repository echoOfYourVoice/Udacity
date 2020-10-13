package com.example.udacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class PhrasesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val words = arrayListOf<Word>(Word("Where are you going?", "Куда ты идёшь?"), Word("What is your name?", "Как твоё имя?"), Word("My name is...", "Меня зовут..."),
            Word("How are you feeling?", "Как ты себя чувствуешь?"), Word("I’m feeling good.", "Мне хорошо"), Word("Are you coming?", "Ты идёшь?"),
            Word("Yes, I’m coming.", "Да, я иду."), Word("Let’s go.", "Пойдём"), Word("Come here.", "Иди сюда."))

        findViewById<ListView>(R.id.list).adapter = WordAdapter(this, words, R.color.category_phrases)
    }
}
