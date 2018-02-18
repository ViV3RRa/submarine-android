package dk.submarine.viverra.submarine

import android.arch.persistence.room.*
import dk.submarine.viverra.submarine.Dao.ProductDao
import dk.submarine.viverra.submarine.Modle.Product
import dk.submarine.viverra.submarine.Util.DateConverter


/**
 * Created by sKrogh on 18/02/2018.
 */

@Database(entities = arrayOf(Product::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productModel(): ProductDao
}