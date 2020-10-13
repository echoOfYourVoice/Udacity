package com.example.udacity

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.list_item.view.*

class WordAdapter(context: Activity, words: ArrayList<Word>, color: Int) : ArrayAdapter<Word>(context, 0, words) {
    private val mColor = color
    /*
    Переопределение функции getView AdapterView (ListView, GridView, etc.)
    @param position
    @param convertView
    @param parent
    @return
     */

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Вынесено в несколько строк для примера

        //parent.setBackgroundColor(mColor)

        val listItemView: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val currentWord = getItem(position)
        val defaultNameTextView = listItemView.findViewById<TextView>(R.id.default_text_view)
        defaultNameTextView.text = currentWord?.mDefaultTranslation
        if (currentWord != null) {
            if (currentWord.hasImage()) listItemView.image.setImageResource(currentWord.mResourceImageId)
            else listItemView.image.visibility = View.GONE
        }
        listItemView.findViewById<TextView>(R.id.rus_text_view).text = currentWord?.mRusTranslation

        val textContainer = listItemView.findViewById<View>(R.id.text_container)

        textContainer.setBackgroundColor(ContextCompat.getColor(context, mColor))

        return listItemView
    }
}