package com.example.udacity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView


class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        val words = arrayListOf(Word("one", "один", R.drawable.number_one),  Word("two", "два", R.drawable.number_two), Word("three", "три", R.drawable.number_three),
            Word("four", "четыре", R.drawable.number_four), Word("five", "пять", R.drawable.number_five), Word("six", "шесть", R.drawable.number_six),
            Word("seven", "семь", R.drawable.number_seven), Word("eight", "восемь", R.drawable.number_eight), Word("nine", "девять", R.drawable.number_nine),
            Word("ten", "десять", R.drawable.number_ten))

        /* Стандартный метод getView не предусмотрен для использования с несколькими textView

        val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList)

         Поэтому я переопределил метод getView специально для использования с несколькими textView
         */
        val wordAdapter = WordAdapter(this, words, R.color.primary_dark_color)
        val listView = findViewById<ListView>(R.id.list)
        listView.adapter = wordAdapter

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
}

