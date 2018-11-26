package br.com.fornaro.matafome.view.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.matafome.databinding.FragmentCartBinding
import br.com.fornaro.matafome.view.MainApplication
import br.com.fornaro.matafome.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[CartViewModel::class.java] }
    private val viewAdapter by lazy { CartAdapter({ viewModel.decreaseQuantity(it) }, { viewModel.increaseQuantity(it) }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MainApplication).appComponent.inject(this)

        val safeArgs = CartFragmentArgs.fromBundle(arguments)
        safeArgs.payment?.let { viewModel.setPaymentMethod(it) }

        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        assignToBinding()
        setupRecyclerView()
        setupPaymentButton()
    }

    private fun assignToBinding() {
        binding.viewModel = viewModel
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

    private fun setupPaymentButton() {
        paymentButton.setOnClickListener {
            val action = CartFragmentDirections.nextAction()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}
