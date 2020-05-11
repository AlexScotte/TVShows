package fr.alexflex.tvshows.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.alexflex.tvshows.R
import kotlinx.android.synthetic.main.activity_popular_shows.*

class PopularShowsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_shows)

        ui_popular_show_list.adapter = ShowAdapter()
        ui_popular_show_list.layoutManager = LinearLayoutManager(this)
    }
}
