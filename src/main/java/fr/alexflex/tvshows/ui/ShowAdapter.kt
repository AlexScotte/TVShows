package fr.alexflex.tvshows.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.alexflex.tvshows.R
import fr.alexflex.tvshows.data.Show
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.cell_show.view.*

class ShowAdapter(delegate:Delegate) : RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {

    interface Delegate{
        fun touchedShow(show:Show, index:Int)
    }

    private val delegate = delegate
    private var shows:List<Show> = listOf()

    fun reloadShowlist(list:List<Show>){

        shows = list
        notifyDataSetChanged()
    }

    fun onItemClickedAtIndex(index:Int){

        val item = shows[index]
        delegate.touchedShow(item, index)

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

        fun fillItem(item: Show){

            ui_title.text = item.name

        }

        override fun onClick(v: View?) {
        if(v != null)
            onItemClickedAtIndex(adapterPosition)
        }
    }

}