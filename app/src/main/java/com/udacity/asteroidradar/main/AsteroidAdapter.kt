package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidBinding
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.domain.Model

class AsteroidAdapter(private val clickListener: AsteroidListener): ListAdapter<Model,RecyclerView.ViewHolder>(AsteroidDiffCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val binding: AsteroidBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),ViewHolder.LAYOUT, parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val asteroidItem = getItem(position)
        holder as ViewHolder
        holder.bind(asteroidItem, clickListener)
    }


}

class ViewHolder( val binding: AsteroidBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(item: Model, clickListener: AsteroidListener) {
        binding.asteroid = item
        binding.executePendingBindings()

    }

    companion object{
        @LayoutRes
        val LAYOUT = R.layout.asteroid
    }
}

/**
 * Allows the RecyclerView to determine which items have changed when the list
 * has been updated.
 */

class AsteroidDiffCallBack: DiffUtil.ItemCallback<Model>() {
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return  oldItem.id== newItem.id
    }
}
/**
 * Asteroid click Listener
 */

class AsteroidListener(val clickListener:(model: Model ) -> Unit){
    fun onClick(model: Model) = clickListener(model)
}