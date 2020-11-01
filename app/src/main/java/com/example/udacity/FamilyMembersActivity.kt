package com.example.udacity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.word_list.*

class FamilyMembersActivity : AppCompatActivity() {

    /*
    private var mMediaPlayer: MediaPlayer? = null

    private val mCompletionListener = MediaPlayer.OnCompletionListener {
        Toast.makeText(this, "I'm done", Toast.LENGTH_SHORT).show()
        releaseMediaPlayer()
    }

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportFragmentManager.beginTransaction().replace(R.id.container, NumbersFragment()).commit()

        /*
        val words = arrayListOf<Word>(Word("father", "папа", R.drawable.family_father, R.raw.family_father), Word("mother", "мама", R.drawable.family_mother, R.raw.family_mother),
            Word("son", "сын", R.drawable.family_son, R.raw.family_son), Word("daughter", "дочь", R.drawable.family_daughter, R.raw.family_daughter),
            Word("older brother", "старший брат", R.drawable.family_older_brother, R.raw.family_older_brother), Word("younger brother", "младший брат", R.drawable.family_younger_brother, R.raw.family_younger_brother),
            Word("oldest sister", "старшая сестра", R.drawable.family_older_sister, R.raw.family_older_sister), Word("younger sister", "младшая сестра", R.drawable.family_younger_sister, R.raw.family_younger_sister),
            Word("grandmother", "бабушка", R.drawable.family_grandmother, R.raw.family_grandmother), Word("grandfather", "дедеушка", R.drawable.family_grandfather, R.raw.family_grandfather))

        val adapter = WordAdapter(this, words, R.color.category_family)
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
