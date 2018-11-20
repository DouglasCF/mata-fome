package br.com.fornaro.matafome.view.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.matafome.R
import br.com.fornaro.matafome.common.Resource
import br.com.fornaro.matafome.view.MainApplication
import br.com.fornaro.matafome.viewmodel.RestaurantViewModel
import br.com.fornaro.matafome.viewmodel.factory.RestaurantViewModelFactory
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.android.synthetic.main.layout_content_restaurants.*
import javax.inject.Inject

class RestaurantsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: RestaurantViewModelFactory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[RestaurantViewModel::class.java] }
    private val viewAdapter by lazy { RestaurantsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MainApplication).appComponent.inject(this)

        viewModel.getRestaurants().observe(this, Observer { resource ->
            loading.visibility = View.GONE
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let { viewAdapter.setData(it) }
                }
                Resource.Status.ERROR -> {
                }
                Resource.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(activity)
            adapter = viewAdapter
        }
    }
}
