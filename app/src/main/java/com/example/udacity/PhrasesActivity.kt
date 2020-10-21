package com.example.udacity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.word_list.*

class PhrasesActivity : AppCompatActivity() {

    private lateinit var mMediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val words = arrayListOf<Word>(Word("Where are you going?", "Куда ты идёшь?", audioResourceID = R.raw.phrase_where_are_you_going), Word("What is your name?", "Как твоё имя?",  audioResourceID = R.raw.phrase_what_is_your_name),
            Word("My name is...", "Меня зовут...", audioResourceID = R.raw.phrase_my_name_is), Word("How are you feeling?", "Как ты себя чувствуешь?", audioResourceID = R.raw.phrase_how_are_you_feeling),
            Word("I’m feeling good.", "Мне хорошо", audioResourceID = R.raw.phrase_im_feeling_good), Word("Are you coming?", "Ты идёшь?", audioResourceID = R.raw.phrase_how_are_you_feeling),
            Word("Yes, I’m coming.", "Да, я иду.", audioResourceID = R.raw.phrase_yes_im_coming), Word("Let’s go.", "Пойдём", audioResourceID = R.raw.phrase_lets_go),
            Word("Come here.", "Иди сюда.", audioResourceID = R.raw.phrase_come_here))

        findViewById<ListView>(R.id.list).adapter = WordAdapter(this, words, R.color.category_phrases)

        list.setOnItemClickListener { _, _, position, _ ->
            mMediaPlayer = MediaPlayer.create(this, words[position].audioResourceID)
            mMediaPlayer.start()
        }
    }
}
