package fr.alexflex.tvshows.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import fr.alexflex.tvshows.R

class MyShowsActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.my_shows_title)
    }

    override fun onResume() {
        super.onResume()
        showsManager.loadMyShowList(this)
    }

    /* Toolbar menu */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item != null){

            when(item.itemId){

                R.id.ui_settings -> displayPopularShowActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayPopularShowActivity() {

        val intent = Intent(this, PopularShowsActivity::class.java)
        startActivity(intent)
    }
}
