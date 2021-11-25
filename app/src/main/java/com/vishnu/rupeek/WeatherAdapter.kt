package com.vishnu.rupeek


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.vishnu.rupeek.data.response.WeatherResponse
import com.vishnu.rupeek.databinding.ItemLayoutBinding
import com.vishnu.rupeek.utils.dateFormat


class DataAdapter(val data: List<WeatherResponse.Data>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    open class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            binding.tvCelisus.text = data[position].temp.toString().plus("\u2103" )
            binding.tvDate.text = dateFormat(data[position].temp.toString())
            binding.tvRain.text = data[position].rain.toString()
            binding.tvWind.text = data[position].wind.toString()

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}

