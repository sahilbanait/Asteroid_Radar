package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class AsteroidAdapter: RecyclerView.Adapter<ViewHolder>(){
     var asteroid: List<Asteroid> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),ViewHolder.LAYOUT, parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.binding.also {
          it.activityMainImageOfTheDay
          it.textView.text = asteroid.toString()
      }

    }

    override fun getItemCount() =asteroid.size
}
class ViewHolder( val binding: FragmentMainBinding): RecyclerView.ViewHolder(binding.root){
    companion object{
        @LayoutRes
        val LAYOUT = R.layout.fragment_detail
    }
}