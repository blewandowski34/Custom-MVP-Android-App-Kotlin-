package com.example.capstonenoerrors

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Communicator {

    private lateinit var toggle : ActionBarDrawerToggle

    private val mvpFragment = Mvp()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationLayout: DrawerLayout = findViewById(R.id.navigation_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)





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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


       if(item.itemId == R.id.settings){
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.fragment_container, Sliders()).commit()

        }
        else if (item.itemId == R.id.mvp){
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.fragment_container, mvpFragment).commit()
        }
        return true
    }

    override fun passSliderData(sliderData: IntArray) {
        val bundle = Bundle()
        bundle.putIntArray("options", sliderData)
        mvpFragment.arguments = bundle


    }


}


