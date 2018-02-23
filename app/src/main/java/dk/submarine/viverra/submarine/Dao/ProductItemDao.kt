package dk.submarine.viverra.submarine.Dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import dk.submarine.viverra.submarine.Modle.ProductItem
import dk.submarine.viverra.submarine.Util.DateConverter

/**
 * Created by sKrogh on 23/02/2018.
 */

@Dao
@TypeConverters(DateConverter::class)
interface ProductItemDao {

    @get:Query("select * from product_items")
    val allProductItems: LiveData<List<ProductItem>>

    @Query("select * from product_items where uuid = :arg0")
    fun getProductbyUuid(uuid: String): ProductItem

    @Insert(onConflict = REPLACE)
    fun addProductItem(productItem: ProductItem)

    @Update(onConflict = REPLACE)
    fun updateProductItem(productItem: ProductItem)

    @Delete
    fun deleteProductItem(productItem: ProductItem)
}