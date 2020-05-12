package fr.alexflex.tvshows.ui

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        // Use config below to delete DB in debug
        val config = RealmConfiguration.Builder().inMemory().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }
}