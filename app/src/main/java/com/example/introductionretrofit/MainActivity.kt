package com.example.introductionretrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.introductionretrofit.adapters.MovieAdapter
import com.example.introductionretrofit.databinding.ActivityMainBinding
import com.example.introductionretrofit.models.Movie
import com.example.introductionretrofit.retrofit.Common
import com.example.introductionretrofit.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var retrofitService: RetrofitService
    lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    lateinit var list: ArrayList<Movie>
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        movieAdapter = MovieAdapter(list)


        retrofitService = Common.retrofitService

        retrofitService.getMovie().enqueue(object : Callback<List<Movie>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {

                if (response.isSuccessful && response.body() != null) {

/*                    val list = response.body()
                    list?.forEach {

                        Log.d(TAG, "OnDon:$it")

                    }*/

                    list.addAll(response.body()!!)
                    movieAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {


            }

        })

        binding.rv.adapter = movieAdapter

    }
}