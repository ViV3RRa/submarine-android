package dk.submarine.viverra.submarine

import android.app.Application
import android.arch.persistence.room.Room
import dk.submarine.viverra.submarine.Util.Prefs
import java.util.*

/**
 * Created by sKrogh on 18/02/2018.
 */

val prefs: Prefs by lazy {
    App.prefs
}

val appDB: AppDatabase by lazy {
    App.appDB
}

class App : Application() {
    companion object {
        lateinit var prefs: Prefs
        lateinit var appDB: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        appDB =  Room.databaseBuilder(this, AppDatabase::class.java, "submarine_db").build()
        setDeviceId()
    }

    private fun setDeviceId() {
        if (prefs.deviceId.isNullOrEmpty()) {
            prefs.deviceId = UUID.randomUUID().toString().replace("-", "")
        }
    }
}