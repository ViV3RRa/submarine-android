package dk.submarine.viverra.submarine.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.submarine.viverra.submarine.Modle.ProductItem
import dk.submarine.viverra.submarine.R
import dk.submarine.viverra.submarine.ViewModels.ProductItemsViewModel
import kotlinx.android.synthetic.main.fragment_budget_overview.*

/**
 * Created by sKrogh on 18/02/2018.
 */

class BudgetOverviewFragment : Fragment() {

    lateinit var viewModel: ProductItemsViewModel

    companion object {

        fun newInstance(): BudgetOverviewFragment {
            return BudgetOverviewFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_budget_overview, container, false)
        (activity as AppCompatActivity).supportActionBar!!.title = "Home"

        viewModel = ViewModelProviders.of(this).get(ProductItemsViewModel::class.java)

        viewModel.getAllProductItems().observe(this, Observer<List<ProductItem>> { products -> number_of_productitems.text = products!!.size.toString() })

        return view
    }
}