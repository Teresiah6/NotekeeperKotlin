package com.example.android.notekeeperkt

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.notekeeperkt.DataManager
import com.example.android.notekeeperkt.NoteActivity
import com.example.android.notekeeperkt.NoteRecyclerAdapter
import com.example.android.notekeeperkt.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.app_bar_items.*
import kotlinx.android.synthetic.main.content_items.*

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val noteLayoutManager by lazy {   LinearLayoutManager(this)}

    private val noteRecyclerAdapter by lazy {  NoteRecyclerAdapter(this, DataManager.notes) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setSupportActionBar(toolbar as Toolbar?)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, NoteActivity::class.java))

        }

        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar as Toolbar?, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_notes -> {
                handleSelection("Notes")
            }
            R.id.nav_courses -> {
                handleSelection("Courses")

            }
            R.id.nav_share -> {
                handleSelection("Don't you think you have shared enough")

            }
            R.id.nav_send -> {
                handleSelection("send")

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(listItems,message,Snackbar.LENGTH_LONG).show()

    }
}
