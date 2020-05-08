package fr.alexflex.tvshows

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Show() : RealmObject() {

    var showName:String=""
    var name:String=""

    constructor(name:String, show:String) : this(){

        this.showName = show
        this.name = name
    }
}