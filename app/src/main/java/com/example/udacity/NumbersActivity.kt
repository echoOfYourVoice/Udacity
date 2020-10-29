package com.example.udacity

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.word_list.*


class NumbersActivity : AppCompatActivity() {

    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var mAudioManager: AudioManager

    private val mOnAudioFocusChangeListener = AudioManager.OnAudioFocusChangeListener {

        Log.i("StopMedia", it.toString())

        if (it == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || it == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            //mMediaPlayer?.setVolume(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat(), mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat() / 50) ????
            mMediaPlayer?.pause()
            mMediaPlayer?.seekTo(0)
        }
        else if (it == AudioManager.AUDIOFOCUS_GAIN) mMediaPlayer?.start()
        else if (it == AudioManager.AUDIOFOCUS_LOSS) releaseMediaPlayer()

    }


    private val mCompletionListener = MediaPlayer.OnCompletionListener {
        Toast.makeText(this, "I'm done", Toast.LENGTH_SHORT).show()
        releaseMediaPlayer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        mAudioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        val words = arrayListOf(Word("one", "один", R.drawable.number_one, R.raw.number_one),  Word("two", "два", R.drawable.number_two, R.raw.number_two),
            Word("three", "три", R.drawable.number_three, R.raw.number_three), Word("four", "четыре", R.drawable.number_four, R.raw.number_four),
            Word("five", "пять", R.drawable.number_five, R.raw.number_five), Word("six", "шесть", R.drawable.number_six, R.raw.number_six),
            Word("seven", "семь", R.drawable.number_seven, R.raw.number_seven), Word("eight", "восемь", R.drawable.number_eight, R.raw.number_eight),
            Word("nine", "девять", R.drawable.number_nine, R.raw.number_nine), Word("ten", "десять", R.drawable.number_ten, R.raw.number_ten))

        /* Стандартный метод getView не предусмотрен для использования с несколькими textView

        val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList)

         Поэтому я переопределил метод getView специально для использования с несколькими textView
         */
        val wordAdapter = WordAdapter(this, words, R.color.primary_dark_color)
        val listView = findViewById<ListView>(R.id.list)


        listView.adapter = wordAdapter

            list.setOnItemClickListener { _, _, position, _ ->
                releaseMediaPlayer()

                val result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(this, words[position].audioResourceID)
                    mMediaPlayer.apply {
                        mMediaPlayer?.setOnCompletionListener(mCompletionListener)
                        this?.start()
                    }
                }
            }


        // Пример использования обычного ListView и цикла для заполнения
        // работает медленно и потребляет много памяти
        // изначально отсутствует разделение на блоки и нет скролла

        /*
        val rootView = findViewById<LinearLayout>(R.id.rootView)
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.)
        for (i in 0 until intList.size) {
        //for (i in 0..1000000000) {
            val textView = TextView(this)
            textView.text = intList[i].toString()
            //textView.text = i.toString()
            rootView.addView(textView)
        }
         */

    }

    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() { // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer?.release()
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener)
        }
    }


}

