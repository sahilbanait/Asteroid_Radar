package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.domain.Model

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, Factory(activity?.application)).get(MainViewModel::class.java)

    }

    private var viewModelAdapter: AsteroidAdapter? = AsteroidAdapter(AsteroidListener { model ->
        viewModel.onAsteroidClicked(model)

    })



    override fun onCreateView(inflater: LayoutInflater, coner: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        binding.asteroidRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
            recyclerAdapter()
        }
        viewModel.navigateToSelectedAsteroid.observe(viewLifecycleOwner, Observer { model ->

            if (model != null){
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(model))
                viewModel.displayAsteroidDetailsComplete()
            }
        })
        return binding.root
    }

    private fun recyclerAdapter() {

        viewModel.list.observe(viewLifecycleOwner,{ list ->
            list?.apply {
                viewModelAdapter?.submitList(list)
            }
            binding.asteroidRecycler.adapter as AsteroidAdapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.next_week -> viewModel.updateAsteroidOptionList(MainViewModel.MenuOption.SHOW_NEXT_WEEK)
            R.id.today -> viewModel.updateAsteroidOptionList(MainViewModel.MenuOption.SHOW_TODAY)
            R.id.saved -> viewModel.updateAsteroidOptionList(MainViewModel.MenuOption.SHOW_SAVED)
            else -> viewModel.updateAsteroidOptionList(MainViewModel.MenuOption.SHOW_DEFAULT)
        }
        return true
    }
}
