package dk.submarine.viverra.submarine.Dao

import dk.submarine.viverra.submarine.Util.DateConverter
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import dk.submarine.viverra.submarine.Modle.Product


/**
 * Created by sKrogh on 18/02/2018.
 */

@Dao
@TypeConverters(DateConverter::class)
interface ProductDao {

    @get:Query("select * from products")
    val allProducts: LiveData<List<Product>>

    @Query("select * from products where ean = :arg0")
    fun getProductbyId(ean: String): Product

    @Insert(onConflict = REPLACE)
    fun addProduct(product: Product)

    @Update(onConflict = REPLACE)
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

}
