package com.example.almatyparking.presentation.parking_place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.almatyparking.R
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlace
import com.example.almatyparking.extensions.ParkingPlaceViewModel
import com.example.almatyparking.presentation.payment.listToPay.PaymentPayPresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject
import java.util.Observer
import javax.inject.Inject


class ParkingPlaceFragment : Fragment(),
    OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private lateinit var map: GoogleMap
    private val viewModel: ParkingPlaceViewModel by inject()

    companion object {
        fun newInstance() : ParkingPlaceFragment =
            ParkingPlaceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_place, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData()
    }

    fun bindViews(view: View) = with(view) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@ParkingPlaceFragment)
    }


    fun setData(){

        viewModel.liveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { parkingPlaceList ->
            parkingPlaceList.map { parkingPlace ->
                val currentLatLng = parkingPlace.latitude?.let { latitude ->
                    parkingPlace.longitude?.let { longitude ->
                        LatLng(latitude, longitude)
                    }
                }
                if((parkingPlace.availablePlaces!!/parkingPlace.freePlaces!!).toDouble() <= 1.05){
                    map.addMarker(
                        currentLatLng?.let { latlng ->
                            MarkerOptions()
                                .position(latlng)
                                .title(parkingPlace.address)
                                .snippet(parkingPlace.freePlaces.toString() + " out of " + parkingPlace.availablePlaces.toString())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        }
                    )
                }
                else if((parkingPlace.availablePlaces/parkingPlace.freePlaces).toDouble() in 1.05..2.1) {
                    map.addMarker(
                        currentLatLng?.let { latlng ->
                            MarkerOptions()
                                .position(latlng)
                                .title(parkingPlace.address)
                                .snippet(parkingPlace.freePlaces.toString() + " out of " + parkingPlace.availablePlaces.toString())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                        }
                    )
                }

                else{
                    map.addMarker(
                        currentLatLng?.let { latlng ->
                            MarkerOptions()
                                .position(latlng)
                                .title(parkingPlace.address)
                                .snippet(parkingPlace.freePlaces.toString() + " out of " + parkingPlace.availablePlaces.toString())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        }
                    )

                }

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
    }


    override fun onMarkerClick(p0: Marker?): Boolean {
        val bundle = Bundle()
        Toast.makeText(getActivity(), p0!!.snippet, Toast.LENGTH_SHORT).show();
//        p0?.alpha?.let { bundle.putInt("id", p0?.alpha.toInt()) }
//        bundle.putInt("id", p0?.id!!.toInt())
        return false
    }


}

