package com.example.nasaspaceappshackathon


public class FireData{
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var acqDate: String? = null
    var acqTime: String? = null
    var type: String? = null
    var satellite: String? = null
    var frp: Double = 0.0
    var confidence: String? = null
    var brightT31: Double = 0.0
    var track: Int? = null
    var daynight: String? = null
    var version: String? = null
    var brightness: Double = 0.0
    var scan: Double = 0.0
    var instrument: String? = null

    constructor(latitude: Double,longitude:Double,acqDate:String,acqTime: String,type:String,satellite:String,
                frp:Double,confidence:String,brightT31: Double,track:Int,daynight:String,version:String,brightness:Double,
                scan:Double,instrument:String
    ) {
        this.latitude = latitude
        this.longitude = longitude
        this.acqDate = acqDate
        this.acqTime = acqTime
        this.type = type
        this.satellite = satellite
        this.frp = frp
        this.confidence = confidence
        this.brightT31 = brightT31
        this.track = track
        this.daynight = daynight
        this.version = version
        this.brightness = brightness
        this.scan = scan
        this.instrument = instrument


    }

    constructor()
}