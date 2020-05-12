package fr.alexflex.tvshows.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import fr.alexflex.tvshows.R
import fr.alexflex.tvshows.data.Show
import fr.alexflex.tvshows.data.ShowManager
import kotlinx.android.synthetic.main.activity_show_details.*

class ShowDetailsActivity : AppCompatActivity() {

    private lateinit var showManager: ShowManager
    private lateinit var show: Show

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        show = intent.getSerializableExtra("show") as Show
        showManager = ShowManager(this)
        updateFavoriteButtonTitle()
        ui_title.text = show.name
        ui_description.text = show.description
        Glide.with(this).load(show.imageUrl).into(ui_image)

    }

    private fun updateFavoriteButtonTitle() {
        if (showManager.isShowInFavorites(show)) {
            ui_favorite_btn.text = getString(R.string.details_remove_favorites)
        } else {
            ui_favorite_btn.text = getString(R.string.details_add_favorites)
        }
    }


    fun changeFavoritesStatusButtonTouched(button: View) {
        if (showManager.isShowInFavorites(show)) {
            showManager.removeShowFromFavorites(show)
        } else {
            showManager.addShowToFavorites(show)
        }
        updateFavoriteButtonTitle()
    }
}
