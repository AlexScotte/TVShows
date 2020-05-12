package fr.alexflex.tvshows.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.alexflex.tvshows.R
import fr.alexflex.tvshows.data.Show
import fr.alexflex.tvshows.data.ShowManager
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(), ShowManager.Client, ShowAdapter.Delegate {

    private lateinit var adapter: ShowAdapter
    protected lateinit var showsManager: ShowManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        this.adapter = ShowAdapter(this)
        this.showsManager = ShowManager(this)
        ui_recyclerView.layoutManager = LinearLayoutManager(this)
        ui_recyclerView.adapter = adapter

        //List separator
        ui_recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    //ShowsManager.Client interface
    override fun receiveShowList(showList: List<Show>){

        adapter.reloadShowlist(showList)
    }

    //ShowAdapter.Delegate interface
    override fun touchedShow(show:Show, index:Int){

        val intent = Intent(this, ShowDetailsActivity::class.java)
        intent.putExtra("show", show)
        startActivity(intent)
    }



}
