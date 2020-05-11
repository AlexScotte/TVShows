package fr.alexflex.tvshows.data

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.StringBuilder
import java.net.URLEncoder

class RemoteDataProvider(context:Context) {

    private val requestQueue = Volley.newRequestQueue(context)
    private val imageBaseUrl:String = "https://image.tmdb.org/t/p/w780/"
    fun getTopTVShows(resultHandler: (showList: List<Show>, error: String?) -> Unit){

        getShowListForURL(ApiUrls.Popular.buildUrl(), resultHandler)
    }

    fun getTopTVShowsForQuery(query:String, resultHandler: (showList: List<Show>, error: String?) -> Unit){

        getShowListForURL(ApiUrls.SearchShow.addUrlParameter("query", query).buildUrl(), resultHandler)
    }

    private fun getShowListForURL(url:String, resultHandler:(showList:List<Show>, error:String?) -> Unit){

        val request = JsonObjectRequest(url, null,
        Response.Listener{ result->

            resultHandler(this.parseShowListResponse(result), null)
        }, Response.ErrorListener { error ->

            resultHandler(listOf(), error.localizedMessage)
        })

        requestQueue.add((request))
    }

    private fun parseShowListResponse(jsonReponseBody:JSONObject):List<Show>{

        var showsList = mutableListOf<Show>()
        val items = jsonReponseBody.getJSONArray("results")
        for (i in 0 until items.length()){

            val item = items.getJSONObject(i)
            if(item != null){

                val id = item.getInt("id")
                val name = item.getString("name")
                val description = item.getString("overview")
                val imagePath = item.getString("backgdrop_path")
                showsList.add(Show(id, name, description, imageBaseUrl + imagePath))
            }
        }

        return showsList
    }
}

enum class ApiUrls(val path:String) {

    Popular("/tv/popular"),
    SearchShow("/search/tv")
    ;

    private val queryParam = mutableMapOf<String, String>()
    private val apiBaseUrl = "https://api.themoviedb.org/3"
    private val apiKey: String = "91c54a22cd512dd63e53f10bfffc5883"

    fun buildUrl(): String {

        addUrlParameter("api_key", apiKey)

        val builder = StringBuilder(apiBaseUrl)
        builder.append(path)

        for (entry in queryParam){

            if (builder.contains("?")){
                builder.append("&")
            }
            else{
                builder.append("?")
            }
            builder.append(entry.key + "=" + URLEncoder.encode(entry.value, "UTF-8"))
        }

        return builder.toString()
    }

    fun addUrlParameter(key:String, value:String) : ApiUrls{

        queryParam[key] = value
        return this
    }
}