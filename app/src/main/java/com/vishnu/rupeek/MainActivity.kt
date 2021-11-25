package com.vishnu.rupeek

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishnu.rupeek.databinding.ActivityMainBinding
import com.vishnu.stackoverlfow.data.ApiCallStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        lifecycleScope.launch{

            viewModel.getData()
            Log.d("thread",Thread.currentThread().name)


        }
        observe()

    }

    fun observe() {
        viewModel.apply {
            weatherResponseStatus.observe(this@MainActivity, Observer {
                when (it.status) {
                    ApiCallStatus.LOADING -> {


                    }
                    ApiCallStatus.SUCCESS -> {

                        it.data?.data?.let {
                            binding.rvWeather.apply {
                                layoutManager = LinearLayoutManager(
                                    this@MainActivity,
                                    RecyclerView.VERTICAL,
                                    false
                                )
                                adapter = DataAdapter(it)

                            }
                        }


                    }
                    ApiCallStatus.ERROR -> {

                        val myToast =
                            Toast.makeText(applicationContext, it.errorMessage, Toast.LENGTH_SHORT)
                        myToast.setGravity(Gravity.LEFT, 200, 200)
                        myToast.show()

                    }
                }
            })
        }
    }


}