package fr.alexflex.tvshows.data

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

class LocalDataManager {

    private val realm: Realm = Realm.getDefaultInstance()

    fun saveShowAsFavorite(show:Show){

        if(isShowInFavorites(show)){

            val newFav = LocalShowFavorite()
            newFav.id = show.id
            newFav.name = show.name
            newFav.description = show.description
            newFav.imageUrl = show.imageUrl
        }
    }

    private fun getFavoritesShowsList():List<Show>{

        return realm.where(LocalShowFavorite::class.java).findAll().toList().map { localShowFavorite -> Show(localShowFavorite.id, localShowFavorite.name, localShowFavorite.description, localShowFavorite.imageUrl) }
    }

    private fun isShowInFavorites(show: Show): Boolean {

        return realm.where(LocalShowFavorite::class.java).equalTo("id", show.id).count() > 0
    }
}

@RealmClass
open class LocalShowFavorite : RealmObject(){

    @PrimaryKey var id = 0
    var name=""
    var description=""
    var imageUrl=""
}