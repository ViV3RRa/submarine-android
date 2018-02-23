package dk.submarine.viverra.submarine.Fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dk.submarine.viverra.submarine.Modle.Product
import dk.submarine.viverra.submarine.R
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import dk.submarine.viverra.submarine.ViewModels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_product_details_list.*
import android.support.v7.app.AppCompatActivity




/**
 * Created by sKrogh on 18/02/2018.
 */

class ProductDetailsListFragment : Fragment(), View.OnLongClickListener {

    lateinit var viewModel: ProductsViewModel
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var recyclerView: RecyclerView

    companion object {

        fun newInstance(): ProductDetailsListFragment {
            return ProductDetailsListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_details_list, container, false)

        (activity as AppCompatActivity).supportActionBar!!.title = "Produkter"

        recyclerView = view.findViewById(R.id.products_recyclerview)

        recyclerViewAdapter = RecyclerViewAdapter(ArrayList(), this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)

        viewModel.getAllProducts().observe(this, Observer<List<Product>> { products -> recyclerViewAdapter.addItems(products!!) })

        return view
    }



    class RecyclerViewAdapter(private var productList: List<Product>, private val longClickListener: View.OnLongClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            return RecyclerViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.product_item_layout, parent, false))
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val product = productList[position]
            holder.eanTextView.text = product.ean
            holder.nameTextView.text = product.name
            holder.tagTextView.text = product.tag
            holder.itemView.tag = product
            holder.itemView.setOnLongClickListener(longClickListener)
        }

        override fun getItemCount(): Int {
            return productList.size
        }

        fun addItems(productList: List<Product>) {
            this.productList = productList
            notifyDataSetChanged()
        }

        class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameTextView: TextView = view.findViewById(R.id.product_item_name)
            val eanTextView: TextView = view.findViewById(R.id.product_item_ean)
            val tagTextView: TextView = view.findViewById(R.id.product_item_tag)

        }
    }

    override fun onLongClick(v: View): Boolean {
        val product = v.tag as Product
        Snackbar.make(products_fragment_container, "ProductName:   " + product.name + "   " + product.ean, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        //viewModel.deleteItem(borrowModel)
        return true
    }
}