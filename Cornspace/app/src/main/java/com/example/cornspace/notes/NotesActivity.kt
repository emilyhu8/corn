package com.example.cornspace.notes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cornspace.EventsActivity
import com.example.cornspace.LinksActivity
import com.example.cornspace.MainActivity
import com.example.cornspace.R

private lateinit var linkButton: ImageButton
private lateinit var homeButton: ImageButton
private lateinit var eventButton: ImageButton
class NotesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_main)

        replaceFragment(HomeFragment.newInstance(),false)

        linkButton=findViewById(R.id.linkButton)
        homeButton=findViewById(R.id.homeButton)
        eventButton=findViewById(R.id.eventButton)

        linkButton.setOnClickListener{
            val intent= Intent(this, LinksActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        eventButton.setOnClickListener{
            val intent= Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }
    }


    fun replaceFragment(fragment:Fragment, istransition:Boolean){
        val fragmentTransition = supportFragmentManager.beginTransaction()

        if (istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.add(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragments = supportFragmentManager.fragments
        if (fragments.size == 0){
            finish()
        }
    }
}