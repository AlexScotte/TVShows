package fr.alexflex.tvshows.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import fr.alexflex.tvshows.R

class PopularShowsActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.popular_shows_title)
        setSupportActionBar(findViewById(R.id.toolbar))

        showsManager.loadTopShowList(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.popular_show_list_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView

        // Expand actions
        searchItem?.setOnActionExpandListener(object: MenuItem.OnActionExpandListener{

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

                searchView.requestFocus()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {

                showsManager.loadTopShowList(this@PopularShowsActivity)
                return true
            }
        })

        // On text changed
        searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String?): Boolean {
                displayShowsMatching(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })

        return super.onPrepareOptionsMenu(menu)
    }

    private fun displayShowsMatching(text:String?){

        if(text != null && text.length > 2){

            showsManager.loadShowListWithText(text, this)
        }
        else{

            showsManager.loadTopShowList(this)
        }
    }
}
