package dk.submarine.viverra.submarine.ViewModels

import android.app.Application
import dk.submarine.viverra.submarine.AppDatabase
import android.os.AsyncTask
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.AndroidViewModel
import dk.submarine.viverra.submarine.Modle.Product
import dk.submarine.viverra.submarine.SubmarineApplication


/**
 * Created by sKrogh on 18/02/2018.
 */

class ProductListViewModel(application: Application) : AndroidViewModel(application) {

    private val productList: LiveData<List<Product>>

    private val appDatabase: AppDatabase = this.getApplication<SubmarineApplication>().database

    init {
        productList = appDatabase.productModel().allProducts
    }

    fun getAllProducts(): LiveData<List<Product>> {
        return productList
    }

    fun deleteProduct(product: Product) {
        deleteAsyncTask(appDatabase).execute(product)
    }

    private class deleteAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<Product, Void, Void>() {

        override fun doInBackground(vararg params: Product): Void? {
            db.productModel().deleteProduct(params[0])
            return null
        }

    }
}