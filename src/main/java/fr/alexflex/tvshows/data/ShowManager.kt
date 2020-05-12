package fr.alexflex.tvshows.data

import android.content.Context
import android.content.DialogInterface

class ShowManager(context: Context) {

    private val dataProvider:RemoteDataProvider = RemoteDataProvider(context)
    private val localDataManager:LocalDataManager = LocalDataManager()

    interface Client{
        fun receiveShowList(showList:List<Show>)
    }

    fun loadTopShowList(client:ShowManager.Client){

        dataProvider.getTopTVShows{showList, error -> client.receiveShowList(showList)}
    }

    fun loadMyShowList(client: ShowManager.Client){

        client.receiveShowList(localDataManager.getFavoritesShowsList())
    }

    fun loadShowListWithText(text:String, client:Client){

        dataProvider.getTopTVShowsForQuery(query = text, resultHandler = {showList, error -> client.receiveShowList(showList) })
    }

    /* LOCAL */
    fun addShowToFavorites(show:Show){

        localDataManager.saveShowAsFavorite(show)
    }

    fun removeShowFromFavorites(show:Show){

        localDataManager.removeShowFromFavorites(show)
    }

    fun isShowInFavorites(show:Show):Boolean{

        return localDataManager.isShowInFavorites(show)
    }


}