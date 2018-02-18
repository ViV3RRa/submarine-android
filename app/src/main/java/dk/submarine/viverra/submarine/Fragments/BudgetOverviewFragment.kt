package dk.submarine.viverra.submarine.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.submarine.viverra.submarine.R

/**
 * Created by sKrogh on 18/02/2018.
 */

class BudgetOverviewFragment : Fragment() {

    companion object {

        fun newInstance(): BudgetOverviewFragment {
            return BudgetOverviewFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_budget_overview, container, false)
        (activity as AppCompatActivity).supportActionBar!!.title = "Home"

        return view
    }
}