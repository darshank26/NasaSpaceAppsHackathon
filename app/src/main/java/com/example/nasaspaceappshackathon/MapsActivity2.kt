package com.example.nasaspaceappshackathon

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.clans.fab.FloatingActionButton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {
    lateinit var progress: ProgressBar
    lateinit var listView_details: ListView
    lateinit var fab_call : FloatingActionButton
    lateinit var fab_map : FloatingActionButton
    var cityName: String? = null
    var arrayList_details:ArrayList<FireData> = ArrayList();
    lateinit var json_objectdetail : JSONObject
    private lateinit var mMap: GoogleMap
    lateinit var marker : MarkerOptions
    lateinit var sydney : LatLng
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        progress = findViewById(R.id.progressbas)
        progress.visibility = View.VISIBLE
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//        listView_details = findViewById<ListView>(R.id.listView) as ListView
        run("http://darshankomu.com/nasahackathon/FireDataMap.html")



    }

    fun run(url: String) {
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                progress.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info: JSONArray = json_contact.getJSONArray("info")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details= ArrayList();

                for (i in 0.. size-1) {
                    json_objectdetail=jsonarray_info.getJSONObject(i)
                    var model:FireData= FireData();

                    model.acqDate=json_objectdetail.getDouble("latitude").toString()+""+json_objectdetail.getDouble("longitude").toString()

                    arrayList_details.add(model)

                }

                runOnUiThread {

                    for (i in 0 until arrayList_details.size) {

                        Toast.makeText(this@MapsActivity2,arrayList_details[i].confidence.toString(),Toast.LENGTH_LONG).show()
                        sydney = LatLng(arrayList_details[i].latitude, arrayList_details[i].longitude)

                        var markerBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.flame)

                        marker = MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(markerBitmap))
                        mMap.addMarker(marker.position(sydney))

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

//                        mMap.setOnMarkerClickListener {
//                            AlertDialog.Builder(this@MapsActivity2)
//                                .setTitle("Wild Fire Data")
//                                .setMessage(
//                                    "City Name: $cityName\nFRP: ${json_objectdetail.getString("frp")}\nConfidence:${json_objectdetail.getString("confidence")}"
//                                )
//                                .setPositiveButton(
//                                    "Close"
//                                ) { dialog, _ ->
//                                    dialog.dismiss()
//                                }
//                                .show()
//                            false
//                        }

                    }


                    progress.visibility = View.GONE

                }
            }
        })
//


    }




}
