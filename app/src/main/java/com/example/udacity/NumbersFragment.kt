package com.example.udacity

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass.
 */
class NumbersFragment : Fragment() {

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

        val words = arrayListOf(Word("one", "один", R.drawable.number_one, R.raw.number_one),  Word("two", "два", R.drawable.number_two, R.raw.number_two),
            Word("three", "три", R.drawable.number_three, R.raw.number_three), Word("four", "четыре", R.drawable.number_four, R.raw.number_four),
            Word("five", "пять", R.drawable.number_five, R.raw.number_five), Word("six", "шесть", R.drawable.number_six, R.raw.number_six),
            Word("seven", "семь", R.drawable.number_seven, R.raw.number_seven), Word("eight", "восемь", R.drawable.number_eight, R.raw.number_eight),
            Word("nine", "девять", R.drawable.number_nine, R.raw.number_nine), Word("ten", "десять", R.drawable.number_ten, R.raw.number_ten))

        val wordAdapter = WordAdapter(activity!!, words, R.color.primary_dark_color)
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
