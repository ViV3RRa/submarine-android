package dk.submarine.viverra.submarine

import android.app.Application
import android.arch.persistence.room.Room

/**
 * Created by sKrogh on 18/02/2018.
 */

class SubmarineApplication : Application() {

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, AppDatabase::class.java, "submarine_db").build()
    }
}