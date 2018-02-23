package dk.submarine.viverra.submarine.ViewModels

import android.app.Application
import dk.submarine.viverra.submarine.AppDatabase
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import dk.submarine.viverra.submarine.Modle.ProductItem
import dk.submarine.viverra.submarine.appDB

/**
 * Created by sKrogh on 23/02/2018.
 */

class ProductItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val productItemList: LiveData<List<ProductItem>>

    init {
        productItemList = appDB.productItemModel().allProductItems
    }

    fun getAllProductItems(): LiveData<List<ProductItem>> {
        return productItemList
    }

    fun deleteProductItem(productItem: ProductItem) {
        deleteAsyncTask(appDB).execute(productItem)
    }

    private class deleteAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<ProductItem, Void, Void>() {

        override fun doInBackground(vararg params: ProductItem): Void? {
            db.productItemModel().deleteProductItem(params[0])
            return null
        }

    }
}