package com.example.map

import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.map.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PolylineOptions
import java.lang.ref.WeakReference

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationObserverCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationObserver: LocationObserver
    private var currentMaker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")

        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Logging.context = applicationContext

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationObserver = LocationObserver(this, this)
        locationObserver.callbackRef = WeakReference<LocationObserverCallback>(this)
        locationObserver.start()
    }

    override fun onMapReady(googleMap: GoogleMap) {

        Logging.d("")

        mMap = googleMap

        val ikebukuro = LatLng(35.72895914395796, 139.7105163770693)
        currentMaker = mMap.addMarker(MarkerOptions().position(ikebukuro).title("Ikebukuro"))
        val zoomValue = 15.0f // 1.0f 〜 21.0f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ikebukuro, zoomValue))
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isCompassEnabled = true
    }

    // LocationObserverCallback
    override fun didUpdateLocation(location: Location) {

        Logging.d("")

        Logging.writeLocationFile("${location.latitude},${location.longitude}")

        mMap.clear()

        val polyLines = PolylineOptions()
            .color(Color.RED)
            .width(5f)
        val locationList =  Logging.readLocationFile()
        for (point in locationList){
            val temp = point.split(",")
            Logging.d("### ${temp[0].toDouble()} ${temp[1].toDouble()}")
            polyLines.add(LatLng(temp[0].toDouble(), temp[1].toDouble()))
        }
        mMap.addPolyline(polyLines)

        val latLng = LatLng(location.latitude, location.longitude)
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        currentMaker?.remove()
        currentMaker = mMap.addMarker(MarkerOptions().position(latLng).title("You are here."))

        Toast.makeText(applicationContext, "${location.latitude} ${location.longitude}", Toast.LENGTH_LONG).show()
    }
}