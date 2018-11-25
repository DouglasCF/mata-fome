package br.com.fornaro.matafome.view.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.matafome.R
import br.com.fornaro.matafome.view.MainApplication
import br.com.fornaro.matafome.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.layout_content_restaurants.*
import javax.inject.Inject

class CartFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[CartViewModel::class.java] }
    private val viewAdapter by lazy { CartAdapter({ viewModel.decreaseQuantity(it) }, { viewModel.increaseQuantity(it) }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MainApplication).appComponent.inject(this)

        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupViewModel() {
        viewModel.getCartItems().observe(this, Observer {
            viewAdapter.setData(it)

            if (it.isEmpty()) {
                activity?.finish()
            }
        })
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
