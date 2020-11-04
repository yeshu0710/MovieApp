package com.example.movieapp

import Adapter.MovieAdaptor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import okhttp3.*
import pojo.ModelClass
import android.widget.ListView
import java.io.IOException
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var listView_details: ListView
    var arrayList_details:ArrayList<ModelClass> = ArrayList();
    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient()

    private var movieapiUrl = "https://api.themoviedb.org/3/tv/popular?api_key=9c36768816a7e4d04b0f460e6af0a645"
    //val textView = findViewById<TextView>(R.id.text_view_id)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView_details = findViewById<ListView>(R.id.listView) as ListView

        run(movieapiUrl)
        /*val res = run(movieapiUrl)

        Log.i("Responses", res.toString())*/
    }

    fun run(url: String) {
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val str_response = response.body()!!.string()
                Log.i("log_of_response", str_response)
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                Log.i("log_of_jsoncontact", json_contact.toString())
                //creating json array
                val jsonarray_info:JSONArray= json_contact.getJSONArray("results")
                Log.i("json_array_result",jsonarray_info.toString())
                var i:Int = 0
                val size:Int = jsonarray_info.length()
                Log.i("sizeofjsonrray",size.toString())
                arrayList_details= ArrayList(size);
                for (i in 0 until size-1) {
                    val json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    val model = ModelClass();
                    model.name=json_objectdetail.getString("name")
                    model.poster_path=json_objectdetail.getString("poster_path")
                    model.id=json_objectdetail.getString("id").toInt()
                    arrayList_details.add(model)
                }

                runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : MovieAdaptor
                    obj_adapter = MovieAdaptor(applicationContext,arrayList_details)
                    listView_details.adapter=obj_adapter
                }
            }
        })
    }

    /*private fun run(url: String): OkHttpClient {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })

        return client
    }*/
}