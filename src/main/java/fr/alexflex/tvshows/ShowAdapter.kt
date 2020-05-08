package fr.alexflex.tvshows

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.cell_show.view.*

class ShowAdapter : RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {

    private var _realm: Realm
    private val shows:RealmResults<Show>

    init {

        _realm  = Realm.getDefaultInstance()

        shows = loadShowFromDB()
        if(shows.size == 0){

            _realm.beginTransaction()
            for (i in 1..100){

                val show = Show()
                show.showName = "Show ${i}"

                for(j in 1..100){

                    show.name = "Name ${j}"
                    _realm.copyToRealm(show)
                }
            }
            _realm.commitTransaction()
        }
    }

    private fun loadShowFromDB() : RealmResults<Show>{

        return  _realm.where(Show::class.java).findAll()
    }

    fun onItemClicked(index:Int, context: Context){

        val item = shows[index]
        if(item != null)
            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {

        // 1 - Load XML view
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.cell_show, parent, false)

        // 2 - Build viewholder to control the view
        val holder = ShowViewHolder(rootView)

        // 3 - Return the viewholder
        return holder
    }

    override fun getItemCount(): Int {

        return shows.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {

        // 1 - Get item
        val item = shows[position]

        // 2 - Fill the item
        if(item != null)
            holder.fillItem(item)
    }



    inner class ShowViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView), View.OnClickListener {

        private val ui_title = rootView.title
        private val ui_subtitle = rootView.subtitle

        init{

            rootView.setOnClickListener(this)
        }

        fun fillItem(item:Show){

            ui_title.text = item.name
            ui_subtitle.text = item.showName
        }

        override fun onClick(v: View?) {
        if(v != null)
            onItemClicked(adapterPosition, v.context)
        }
    }

}