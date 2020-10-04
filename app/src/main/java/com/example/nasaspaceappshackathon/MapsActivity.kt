package com.example.nasaspaceappshackathon

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.scale
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var lat: String
    lateinit var longitude : String
    lateinit var cityName : String
    lateinit var frp : String
    lateinit var confidence : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
         lat = intent.extras!!.getString("latitude")!!
         longitude = intent.extras!!.getString("longitude")!!
        frp = intent.extras!!.getString("frp")!!
            confidence = intent.extras!!.getString("confidence")!!

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(lat.toDouble(), longitude.toDouble())
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(lat.toDouble(), longitude.toDouble(), 1)
        val cityName: String = addresses[0].getAddressLine(0)

//        mMap.addMarker(
//            MarkerOptions()
//                .position(sydney)
//                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.img)))
//        )

        var markerBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.flame)

        val marker = MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(markerBitmap))
//        val mark: Marker = googleMap.addMarker(marker)

        mMap.addMarker(marker.position(sydney).title("City Name: $cityName\n\nFRP: $frp\n\nConfidence:$confidence"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMarkerClickListener {
            AlertDialog.Builder(this@MapsActivity)
                .setTitle("Wild Fire Data")
                .setMessage(
                    "City Name: $cityName\nFRP: $frp\nConfidence:$confidence"
                )
                .setPositiveButton(
                    "Close"
                ) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            false
        }
    }

}
