package fr.alexflex.tvshows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ui_recyclerView.adapter = ShowAdapter()
        ui_recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
