package com.example.udacity

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass.
 */
class ColorsFragment : Fragment() {

    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var mAudioManager: AudioManager

    private val mOnAudioFocusChangeListener = AudioManager.OnAudioFocusChangeListener {

        if (it == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || it == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            //mMediaPlayer?.setVolume(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat(), mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat() / 50) ????
            mMediaPlayer?.pause()
            mMediaPlayer?.seekTo(0)
        }
        else if (it == AudioManager.AUDIOFOCUS_GAIN) mMediaPlayer?.start()
        else if (it == AudioManager.AUDIOFOCUS_LOSS) releaseMediaPlayer()

    }


    private val mCompletionListener = MediaPlayer.OnCompletionListener {
        Toast.makeText(context, "I'm done", Toast.LENGTH_SHORT).show()
        releaseMediaPlayer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.word_list, container, false)

        mAudioManager = activity?.getSystemService(AppCompatActivity.AUDIO_SERVICE) as AudioManager

        val words = arrayListOf<Word>(Word("red", "красный", R.drawable.color_red, R.raw.color_red), Word("green", "зеленый", R.drawable.color_green, R.raw.color_green),
            Word("brown", "коричневый", R.drawable.color_brown, R.raw.color_brown), Word("gray", "серый", R.drawable.color_gray, R.raw.color_gray),
            Word("black", "черный", R.drawable.color_black, R.raw.color_black), Word("white", "белый", R.drawable.color_white, R.raw.color_white),
            Word("dusty yellow", "пыльно-желтый", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow), Word("mustard yellow", "горчично-желтый", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow))

        val wordAdapter = WordAdapter(activity!!, words, R.color.category_colors)
        val listView = rootView.findViewById<ListView>(R.id.list)

        listView.adapter = wordAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            releaseMediaPlayer()

            val result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(activity, words[position].audioResourceID)
                mMediaPlayer.apply {
                    mMediaPlayer?.setOnCompletionListener(mCompletionListener)
                    this?.start()
                }
            }

        }

        return rootView
    }

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

}
