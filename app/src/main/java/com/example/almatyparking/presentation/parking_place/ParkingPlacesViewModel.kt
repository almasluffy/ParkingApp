package com.example.almatyparking.presentation.parking_place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlace
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlaceDao
import com.example.almatyparking.data.models.ParkingPlaces
import com.example.almatyparking.data.repository.ParkingPlaceRepositoryImpl
import com.example.almatyparking.domain.repository.ParkingPlaceRepository
import com.example.almatyparking.domain.repository.ParkingPlacesRepository
import com.example.almatyparking.extensions.launchSafe
import com.example.almatyparking.utils.NoConnectionException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ParkingPlacesViewModel(private val parkingPlacesRepository: ParkingPlacesRepository) : ViewModel(){

    private val parentJob = SupervisorJob()

    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    private val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    private val _liveData = MutableLiveData<List<ParkingPlaces>>()
    val liveData: LiveData<List<ParkingPlaces>>
        get() = _liveData

    init {
       loadParkingPlaces()
    }

     fun handleError(e: Throwable) {

        if(e is NoConnectionException) {

        }
    }

    fun loadParkingPlaces(){
        uiScope.launchSafe(::handleError) {
            val result = withContext(Dispatchers.IO) {
                val response = parkingPlacesRepository.getParkingPlaces()
                response
            }
            _liveData.postValue(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}