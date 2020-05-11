package fr.alexflex.tvshows.data

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.io.Serializable

@RealmClass
open class Show() : RealmObject(), Serializable {

    var id:Int=0
    var name:String=""
    var description:String=""
    var imageUrl:String=""

    constructor(id:Int, name:String, description:String, imageUrl:String) : this(){

        this.id = id
        this.name = name
        this.description = description
        this.imageUrl = imageUrl
    }
}