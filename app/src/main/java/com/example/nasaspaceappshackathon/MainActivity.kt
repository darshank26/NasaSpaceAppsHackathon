package com.example.nasaspaceappshackathon

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.github.clans.fab.FloatingActionButton
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var progress:ProgressBar
    lateinit var listView_details: ListView
    lateinit var fab_call : FloatingActionButton
    lateinit var fab_map : FloatingActionButton
     var cityName: String? = null
    var arrayList_details:ArrayList<FireData> = ArrayList();
    lateinit var json_objectdetail : JSONObject
    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progressbas)
        progress.visibility = View.VISIBLE
        listView_details = findViewById<ListView>(R.id.listView) as ListView
        run("http://darshankomu.com/nasahackathon/FireData.html")
        fab_call = findViewById(R.id.fab_call)
        fab_map = findViewById(R.id.fab_map)
        fab_call.setOnClickListener  {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:1234567890")
            startActivity(intent)
        }

        fab_map.setOnClickListener {
            val intent = Intent(this@MainActivity,MapsActivity2::class.java)
            startActivity(intent)
        }
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
                var jsonarray_info:JSONArray= json_contact.getJSONArray("info")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details= ArrayList();
                for (i in 0.. size-1) {
                     json_objectdetail=jsonarray_info.getJSONObject(i)
                    var model:FireData= FireData();
                    model.frp=json_objectdetail.getDouble("frp").toString().toDouble()
                    model.acqDate = "Date: "+json_objectdetail.getString("acq_date")
                    model.acqTime = "Time: "+json_objectdetail.getString("acq_time")

                    arrayList_details.add(model)


                }

                runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : CustomAdapter = CustomAdapter(applicationContext,arrayList_details)
                    listView_details.adapter=obj_adapter

                    listView_details.setOnItemClickListener(OnItemClickListener { parent, view, position, id -> //here you can use the position to determine what checkbox to check
                        val intent = Intent(this@MainActivity,MapsActivity::class.java)
                        intent.putExtra("latitude",json_objectdetail.getString("latitude") ); // getText() SHOULD NOT be static!!!
                        intent.putExtra("longitude",json_objectdetail.getString("longitude") ); // getText() SHOULD NOT be static!!!
                        intent.putExtra("frp",json_objectdetail.getString("frp") ); // getText() SHOULD NOT be static!!!
                        intent.putExtra("confidence",json_objectdetail.getString("confidence") ); // getText() SHOULD NOT be static!!!

                        startActivity(intent)

                         })
                    progress.visibility = View.GONE

                }
            }
        })
//

    }


}