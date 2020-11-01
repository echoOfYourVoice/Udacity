package com.example.udacity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        /*
        Для этого нужно создавать отдельный класс

        val clickListener = NumbersClickListener()
        // Поиск необходимого элемента по id
        val numbers = findViewById<TextView>(R.id.numbers)
        // Установка clickListener на этот элемент
        numbers.setOnClickListener(clickListener)
         */


        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager)

        viewPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout.setupWithViewPager(viewPager)

        // NUMBERS
        /*
        findViewById<TextView>(R.id.numbers).setOnClickListener {
            val i = Intent(this, NumbersActivity::class.java)
            startActivity(i)
        }

        //FAMILY NUMBERS
        findViewById<TextView>(R.id.family).setOnClickListener {
            val i = Intent(this, FamilyMembersActivity::class.java)
            startActivity(i)
        }

        //COLORS
        findViewById<TextView>(R.id.colors).setOnClickListener {
            val i = Intent(this, ColorsActivity::class.java)
            startActivity(i)
        }

        //PHRASES
        findViewById<TextView>(R.id.phrases).setOnClickListener {
            val i = Intent(this, PhrasesActivity::class.java)
            startActivity(i)
        }

    }
    */

/*
    Для этой реализации необходимо устанавливать действие в activity_main.xml

   fun openNumbersList(view: View) {
        /* Пример неявного намерения
        val i: Intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, "Just kotlin order for ....")
            putExtra(Intent.EXTRA_TEXT, "123456")}
         if (i.resolveActivity(packageManager) != null) startActivity(i)
         */
        // А это намерение уже явное
        val i: Intent = Intent(this, NumbersActivity::class.java)
        startActivity(i)
    }
 */

    }
}