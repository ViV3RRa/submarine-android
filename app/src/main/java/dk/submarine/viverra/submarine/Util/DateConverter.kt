package dk.submarine.viverra.submarine.Util

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by sKrogh on 18/02/2018.
 */

internal object DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }
}