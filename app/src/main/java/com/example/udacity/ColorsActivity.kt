package com.example.udacity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.word_list.*

class ColorsActivity : AppCompatActivity() {

    /*
    private var mMediaPlayer: MediaPlayer? = null

    private val mCompletionListener = MediaPlayer.OnCompletionListener {
        Toast.makeText(this@ColorsActivity, "I'm done", Toast.LENGTH_SHORT).show()
        releaseMediaPlayer()
    }
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportFragmentManager.beginTransaction().replace(R.id.container, ColorsFragment()).commit()
        /*
        val words = arrayListOf<Word>(Word("red", "красный", R.drawable.color_red, R.raw.color_red), Word("green", "зеленый", R.drawable.color_green, R.raw.color_green),
            Word("brown", "коричневый", R.drawable.color_brown, R.raw.color_brown), Word("gray", "серый", R.drawable.color_gray, R.raw.color_gray),
            Word("black", "черный", R.drawable.color_black, R.raw.color_black), Word("white", "белый", R.drawable.color_white, R.raw.color_white),
            Word("dusty yellow", "пыльно-желтый", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow), Word("mustard yellow", "горчично-желтый", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow))

        val adapter = WordAdapter(this, words, R.color.category_colors)
        val listView = findViewById<ListView>(R.id.list)
        listView.adapter = adapter

        list.setOnItemClickListener { _, _, position, _ ->
            mMediaPlayer = MediaPlayer.create(this, words[position].audioResourceID)
            mMediaPlayer.apply {
                mMediaPlayer?.setOnCompletionListener(mCompletionListener)
                this?.start() }
        }
         */
    }

    /*
    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() { // If the media player is not null, then it may be currently playing a sound.
        mMediaPlayer?.release()
        // Set the media player back to null. For our code, we've decided that
        // setting the media player to null is an easy way to tell that the media player
        // is not configured to play an audio file at the moment.
        mMediaPlayer = null
    }
     */
}
