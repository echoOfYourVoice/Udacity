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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.word_list.*

/**
 * A simple [Fragment] subclass.
 */
class PhrasesFragment : Fragment() {

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

        val words = arrayListOf<Word>(Word("Where are you going?", "Куда ты идёшь?", audioResourceID = R.raw.phrase_where_are_you_going), Word("What is your name?", "Как твоё имя?",  audioResourceID = R.raw.phrase_what_is_your_name),
            Word("My name is...", "Меня зовут...", audioResourceID = R.raw.phrase_my_name_is), Word("How are you feeling?", "Как ты себя чувствуешь?", audioResourceID = R.raw.phrase_how_are_you_feeling),
            Word("I’m feeling good.", "Мне хорошо", audioResourceID = R.raw.phrase_im_feeling_good), Word("Are you coming?", "Ты идёшь?", audioResourceID = R.raw.phrase_how_are_you_feeling),
            Word("Yes, I’m coming.", "Да, я иду.", audioResourceID = R.raw.phrase_yes_im_coming), Word("Let’s go.", "Пойдём", audioResourceID = R.raw.phrase_lets_go),
            Word("Come here.", "Иди сюда.", audioResourceID = R.raw.phrase_come_here))

        val wordAdapter = WordAdapter(activity!!, words, R.color.category_phrases)
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
