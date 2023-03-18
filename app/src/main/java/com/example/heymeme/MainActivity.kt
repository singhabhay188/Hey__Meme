package com.example.heymeme

import MySingleton
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var memeImage: ImageView
    private lateinit var generateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memeImage = findViewById(R.id.imageView)
        generateButton = findViewById(R.id.nextb)

        loadmeme()

        generateButton.setOnClickListener {
            loadmeme()
        }
    }

    /*  WITHOUT SINGLETON CLASS
    private fun loadmeme(){
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility= View.VISIBLE
        //VOLLEY REQUEST FOR API
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                //we need to get url from the json object and using that load our image
                var imageurl = response.getString("url")
                Picasso.with(this).load(imageurl).into(memeImage,object:Callback{
                    override fun onSuccess(){
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(){
                        progressBar.visibility = View.GONE
                    }

                })
            },
            { error ->
                Toast.makeText(this, "Error in fetching", Toast.LENGTH_SHORT).show()
            })
        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest)
    }
    */
    private fun loadmeme(){
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility= View.VISIBLE
        //VOLLEY REQUEST FOR API
        val url = "https://meme-api.com/gimme"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                //we need to get url from the json object and using that load our image
                var imageurl = response.getString("url")
                Picasso.with(this).load(imageurl).into(memeImage,object:Callback{
                    override fun onSuccess(){
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(){
                        progressBar.visibility = View.GONE
                    }

                })
            },
            { error ->
                Toast.makeText(this, "Error in fetching", Toast.LENGTH_SHORT).show()
            })
        // Add the request to the RequestQueue
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

}