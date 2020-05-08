package fr.alexflex.tvshows

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_show.view.*

class ShowAdapter : RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {

    val shows = arrayOf(
        Show("test1", "show1"),
        Show("test2", "show1"),
        Show("test3", "show1"),
        Show("test4", "show1"),
        Show("test5", "show1"),
        Show("test6", "show1"),
        Show("test7", "show1"),
        Show("test8", "show1"),
        Show("test9", "show1"),
        Show("test10", "show1"),
        Show("test11", "show1")
    )

    fun onItemClicked(index:Int, context: Context){

        val item = shows[index]
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
            ui_subtitle.text = item.show
        }

        override fun onClick(v: View?) {
        if(v != null)
            onItemClicked(adapterPosition, v.context)
        }
    }

}