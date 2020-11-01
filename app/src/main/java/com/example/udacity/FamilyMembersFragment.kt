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
class FamilyMembersFragment : Fragment() {

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

        val words = arrayListOf<Word>(Word("father", "папа", R.drawable.family_father, R.raw.family_father), Word("mother", "мама", R.drawable.family_mother, R.raw.family_mother),
            Word("son", "сын", R.drawable.family_son, R.raw.family_son), Word("daughter", "дочь", R.drawable.family_daughter, R.raw.family_daughter),
            Word("older brother", "старший брат", R.drawable.family_older_brother, R.raw.family_older_brother), Word("younger brother", "младший брат", R.drawable.family_younger_brother, R.raw.family_younger_brother),
            Word("oldest sister", "старшая сестра", R.drawable.family_older_sister, R.raw.family_older_sister), Word("younger sister", "младшая сестра", R.drawable.family_younger_sister, R.raw.family_younger_sister),
            Word("grandmother", "бабушка", R.drawable.family_grandmother, R.raw.family_grandmother), Word("grandfather", "дедеушка", R.drawable.family_grandfather, R.raw.family_grandfather))

        val wordAdapter = WordAdapter(activity!!, words, R.color.category_family)
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
