package com.example.almatyparking.data.ParkingPlaceDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlace

@Dao
interface ParkingPlaceDao {

    @Query("SELECT * FROM parkingPlace_table")
    fun getParkingPlaces(): LiveData<List<ParkingPlace>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(parkingPlace: ParkingPlace)

    @Query("DELETE FROM parkingPlace_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM parkingPlace_table WHERE id=:id")
    fun getParkingPlace(id: Int): LiveData<ParkingPlace>

}