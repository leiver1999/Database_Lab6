package cr.ac.una.lab4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cr.ac.una.lab4.MovimientoService
import cr.ac.una.lab4.dao.MovimientoDAO
import cr.ac.una.lab4.converter.Converters
import cr.ac.una.lab4.entities.Movimiento

@Database(entities = [Movimiento::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movimientoDao(): MovimientoDAO

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "movimiento-database"
                    ).build()
                }
            }
            return instance!!
        }
    }
}