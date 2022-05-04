package com.example.capstonenoerrors

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Communicator {

    private lateinit var toggle: ActionBarDrawerToggle

    //instance of Mvp fragment in order to pass arguments through
    private val mvpFragment = Mvp()

    var sliderData: IntArray = IntArray(8)
    var playerArray = mutableListOf<Player>()
    var playerStatsArray = mutableListOf<PlayerStats>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Initializes Navigation Layout and View
        val navigationLayout: DrawerLayout = findViewById(R.id.navigation_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        //Makes the Navigation Layout usable
        toggle = ActionBarDrawerToggle(this, navigationLayout, R.string.open, R.string.close)
        toggle.isDrawerSlideAnimationEnabled = true
        toggle.toolbarNavigationClickListener
        navigationLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container, Sliders()).commit()

    }

    //Navigation menu implementation.
    //Replaces the fragment with new selected fragment.
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.settings) {
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.fragment_container, Sliders()).commit()

        } else if (item.itemId == R.id.mvp) {
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.fragment_container, mvpFragment).commit()
        }
        return true
    }

    //This method passes the data gathered by the sliders on the sliders_fragment as an argument
    //for the mvp fragment
    //params @sliderData: IntArray an array of ints from the user input on the sliders
    override fun passSliderData(sliderData: IntArray) {
        val bundle = Bundle()
        bundle.putIntArray("sliders", sliderData)
        mvpFragment.arguments = bundle

    }
}



