package com.example.almatyparking.data.ParkingPlaceDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ParkingPlace::class], version = 2, exportSchema = false)
abstract class ParkingPlaceRoomDatabase: RoomDatabase() {

    abstract fun parkingPlaceDao(): ParkingPlaceDao

    companion object {
        @Volatile
        private var INSTANCE: ParkingPlaceRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ParkingPlaceRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParkingPlaceRoomDatabase::class.java,
                    "parkingPlace_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ParkingPlaceDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
    private class ParkingPlaceDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.parkingPlaceDao())
                }
            }
        }

        suspend fun populateDatabase(parkingPlaceDao: ParkingPlaceDao) {
            parkingPlaceDao.deleteAll()

            var parkingPlace = ParkingPlace(
                1,
                "ул. Абылайхана",
                "с 08:00 до 19:00",
                100,
                43.240263,
                76.905654,
                30,
                5
            )
            parkingPlaceDao.insert(parkingPlace)

            parkingPlace = ParkingPlace(
                2,
                "ул. Курмангазы",
                "с 08:00 до 19:00",
                100,
                43.260613,
                76.820057,
                100,
                85


            )
            parkingPlaceDao.insert(parkingPlace)

            parkingPlace = ParkingPlace(
                3,
                "ул. Шевченко",
                "с 08:00 до 19:00",
                100,
                43.228496,
                76.857868,
                100,
                50


            )
            parkingPlaceDao.insert(parkingPlace)

        }
    }
}