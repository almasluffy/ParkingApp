package com.example.almatyparking.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlace
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlaceDao
import com.example.almatyparking.data.repository.ParkingPlaceRepositoryImpl
import com.example.almatyparking.domain.repository.ParkingPlaceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ParkingPlaceViewModel(private val parkingPlaceDao: ParkingPlaceDao ) : ViewModel(){

    private val parentJob = SupervisorJob()

    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    private val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    private val repository: ParkingPlaceRepository

    var liveData: LiveData<List<ParkingPlace>>

    init {
        repository = ParkingPlaceRepositoryImpl(parkingPlaceDao)
        liveData = repository.getAllParkingPlaces()
    }




    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}