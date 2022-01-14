package com.example.cornspace

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Repository private constructor() {
    companion object {
        private const val BASE_URL = "https://cornspacebackend.herokuapp.com/joke/"
        private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        private val jokeJsonAdapter = moshi.adapter(Joke::class.java)
        private val jokeListType =newParameterizedType(List::class.java, Joke::class.java)
        private val jokeListJsonAdapter: JsonAdapter<List<Joke>> = moshi.adapter(jokeListType)

        lateinit var joke: Joke
        var localEventList = mutableListOf<Event>()
        var name: String = "friend"

        //var jokes=listOf("How does the moon cut his hair? He eclipses it!", "Where do fruits go on vacation? Pear-is!", "What does a sprinter eat before a race? Nothing, they fast!")


        private val client = OkHttpClient()

        fun fetchJoke(successHandler: ((Joke) -> Unit)? = null) {
            val requestGet = Request.Builder().url(BASE_URL).build()
            client.newCall(requestGet).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: okhttp3.Call, response: Response) {
                    response.use {
                        if (!it.isSuccessful) {
                            Log.e("NETWORK ERROR", it.message)
                            throw IOException("Post unsuccessful")
                        }
                        val responseBody = response.body
                        responseBody?.let {
                            val bodyString = responseBody.string()
                            try {
                                jokeJsonAdapter.fromJson(bodyString)?.let {
                                    joke = it
                                    if (successHandler != null) {
                                        //pass the newly fetched list from cloud to UI
                                        successHandler(joke)
                                    }
                                }
                            }
                            catch(e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            })
        }

    }
}

