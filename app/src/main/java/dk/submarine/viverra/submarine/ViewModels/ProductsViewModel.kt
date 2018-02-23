package dk.submarine.viverra.submarine.ViewModels

import android.app.Application
import dk.submarine.viverra.submarine.AppDatabase
import android.os.AsyncTask
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.AndroidViewModel
import dk.submarine.viverra.submarine.Modle.Product
import dk.submarine.viverra.submarine.appDB


/**
 * Created by sKrogh on 18/02/2018.
 */

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    private val productList: LiveData<List<Product>>

    init {
        productList = appDB.productModel().allProducts
    }

    fun getAllProducts(): LiveData<List<Product>> {
        return productList
    }

    fun deleteProduct(product: Product) {
        deleteAsyncTask(appDB).execute(product)
    }

    private class deleteAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<Product, Void, Void>() {

        override fun doInBackground(vararg params: Product): Void? {
            db.productModel().deleteProduct(params[0])
            return null
        }

    }
}