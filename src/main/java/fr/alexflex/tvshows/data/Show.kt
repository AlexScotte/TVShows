package fr.alexflex.tvshows.data

import java.io.Serializable

data class Show(val id:Int, val name:String, val description:String, val imageUrl:String) : Serializable