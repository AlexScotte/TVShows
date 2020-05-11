package fr.alexflex.tvshows.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import fr.alexflex.tvshows.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ui_recyclerView.adapter = ShowAdapter()
        //ui_recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item != null){

            when(item.itemId){

                R.id.ui_settings -> displayAddShowActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayAddShowActivity() {

        val intent = Intent(this, PopularShowsActivity::class.java)
        startActivity(intent)
    }
}
