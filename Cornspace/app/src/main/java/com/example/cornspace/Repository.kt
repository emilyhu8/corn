package com.example.cornspace

import android.app.DownloadManager
import android.telecom.Call
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.sql.Types
import javax.security.auth.callback.Callback

class Repository private constructor(){
    companion object {
        private const val BASE_URL = "http://143.198.115.54:8080/"
        private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        private val eventJsonAdapter = moshi.adapter(Event::class.java)
        //private val eventListType = Types.newParameterizedType(List::class.java, Event::class.java)
        //private val eventListJsonAdapter : JsonAdapter<List<Event>> = moshi.adapter(eventListType)


        var localEventList = mutableListOf<Event>()
        var name: String=""


        private val client = OkHttpClient()

        fun fetchEventsList(successHandler: ((List<Event>) -> Unit)? = null) {
            val requestGet = Request.Builder().url(BASE_URL + "events/").build()
            /*
            client.newCall(requestGet).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!it.isSuccessful) {
                            Log.e("NETWORK ERROR", it.message)
                            throw IOException("Post unsuccessful")
                        }
                        val note = response.body!!.string()
                        var eventList = eventListJsonAdapter.fromJson(note)!! as MutableList<Event>
                        if (successHandler != null) {
                            //pass the newly fetched list from cloud to UI
                            successHandler(eventList)
                        }

                    }
                }
            })

             */
        }

        fun updateEvent(newEvent: Event) {
            val eventJsonString = eventJsonAdapter.toJson(newEvent)
            val requestPost = Request.Builder().url(BASE_URL + "posts/")
                .post(eventJsonString.toRequestBody(("application/json; charset=utf-8").toMediaType())).build()
            client.newCall(requestPost).execute().use {
                if (!it.isSuccessful) {
                    Log.e("NETWORK ERROR", it.message)
                    throw IOException("event edit unsuccessful")
                }
                Log.d("NETWORK RESPONSE", it.body!!.string())
            }
        }

        fun initializeEventList(){

        }
    }
}