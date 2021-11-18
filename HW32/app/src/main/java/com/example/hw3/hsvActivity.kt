package com.example.hw3

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Half.toFloat
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

class hsvActivity : AppCompatActivity() {
    private lateinit var seekBarHue : SeekBar
    private lateinit var seekBarSaturation : SeekBar
    private lateinit var seekBarValue: SeekBar
    private lateinit var textViewHue: TextView
    private lateinit var textViewSaturation: TextView
    private lateinit var textViewValue: TextView
    private lateinit var hueSquare : View
    private lateinit var hexColorText : TextView
    private lateinit var locationButton: Button
    var hsv = FloatArray(3)
    var currentColor: Int = 0
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationtext: String
    private lateinit var constraintLayout: View


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hsv)
        //iniitialize UI
        seekBarHue = findViewById(R.id.seekBarHue)
        seekBarValue = findViewById(R.id.seekBarValue)
        seekBarSaturation = findViewById(R.id.seekBarSaturation)
        textViewHue = findViewById(R.id.textViewHue)
        textViewSaturation = findViewById(R.id.textViewSaturation)
        textViewValue = findViewById(R.id.textViewValue)
        hueSquare = findViewById(R.id.hue_square)
        hexColorText= findViewById(R.id.textViewHexHSV)
        locationButton=findViewById(R.id.buttonLocation)
        constraintLayout=findViewById(R.id.constraintLayout)

        var redprogress= intent.extras?.getInt("Red")!!
        var blueprogress= intent.extras?.getInt("Blue")!!
        var greenprogress= intent.extras?.getInt("Green")!!
        currentColor=Color.rgb(redprogress, greenprogress, blueprogress)
        Color.colorToHSV(currentColor, hsv)
        hueSquare.setBackgroundColor(currentColor)

        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                textViewHue.text = "Hue"
                textViewSaturation.text = "Saturation"
                textViewValue.text = "Value"
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                textViewHue.text =
                    "Hue: " + (seekBarHue.progress.toFloat() / 100).toString()
                textViewSaturation.text =
                    "Saturation: " + (seekBarSaturation.progress.toFloat() / 100).toString()
                textViewValue.text =
                    "Value: " + (seekBarValue.progress.toFloat() / 100).toString()
            }

        }
        //seekbar hue

        seekBarHue.max=36000
        seekBarSaturation.max=100
        seekBarValue.max=100


        val click1: Button = findViewById(R.id.buttonMAIN)
        click1.setOnClickListener() {
            val mainIntent= Intent(this, MainActivity::class.java)
            mainIntent.putExtra("Red", redprogress)
            mainIntent.putExtra("Blue", blueprogress)
            mainIntent.putExtra("Green", greenprogress)
            startActivity(mainIntent)

        }

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)
        locationButton.setOnClickListener(){
            getLocation()
        }



        seekBarHue.progress=(hsv[0]*100).toInt()
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                textViewHue.text = "Hue"
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                textViewHue.text =
                    "Hue: " + (seekBarHue.progress.toFloat() / 100).toString()
            }

        }
        seekBarHue.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, p2: Boolean) {

                hsv[0] = seekBarHue.progress.toFloat() / 100
                currentColor = Color.HSVToColor(hsv)
                redprogress = Color.red(currentColor)
                greenprogress = Color.green(currentColor)
                blueprogress = Color.blue(currentColor)
                hueSquare.setBackgroundColor(Color.rgb(redprogress, greenprogress, blueprogress))
                hexColorText.text = resources.getString(
                    R.string.hexString,
                    Integer.toHexString(redprogress).toUpperCase(),
                    Integer.toHexString(greenprogress).toUpperCase(),
                    Integer.toHexString(blueprogress).toUpperCase()
                )
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        textViewHue.text = "Hue"
                    }
                    Configuration.ORIENTATION_PORTRAIT -> {
                        textViewHue.text =
                            "Hue: " + (seekBarHue.progress.toFloat() / 100).toString()
                    }

                }
            }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
        //seekBar Saturation
        seekBarSaturation.progress=(hsv[1]*100).toInt()
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                textViewSaturation.text = "Saturation"
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                textViewSaturation.text =
                    "Saturation: " + (seekBarSaturation.progress.toFloat() / 100).toString()
            }

        }
        seekBarSaturation.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, p2: Boolean) {

                hsv[1] = seekBarSaturation.progress.toFloat()/ 100
                currentColor = Color.HSVToColor(hsv)
                redprogress = Color.red(currentColor)
                greenprogress = Color.green(currentColor)
                blueprogress = Color.blue(currentColor)
                hueSquare.setBackgroundColor(Color.rgb(redprogress, greenprogress, blueprogress))
                hexColorText.text = resources.getString(
                    R.string.hexString,
                    Integer.toHexString(redprogress).toUpperCase(),
                    Integer.toHexString(greenprogress).toUpperCase(),
                    Integer.toHexString(blueprogress).toUpperCase()
                )
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        textViewSaturation.text = "Saturation"
                    }
                    Configuration.ORIENTATION_PORTRAIT -> {
                        textViewSaturation.text =
                            "Saturation: " + (seekBarSaturation.progress.toFloat() / 100).toString()
                    }

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
        //seekBarValue
        seekBarValue.progress=(hsv[2]*100).toInt()
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                textViewValue.text = "Value"
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                textViewValue.text =
                    "Value: " + (seekBarValue.progress.toFloat() / 100).toString()
            }

        }
        seekBarValue.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, p2: Boolean) {

                hsv[2] = seekBarValue.progress.toFloat() / 100
                currentColor = Color.HSVToColor(hsv)
                redprogress = Color.red(currentColor)
                greenprogress = Color.green(currentColor)
                blueprogress = Color.blue(currentColor)
                hueSquare.setBackgroundColor(Color.rgb(redprogress, greenprogress, blueprogress))
                hexColorText.text = resources.getString(
                    R.string.hexString,
                    Integer.toHexString(redprogress).toUpperCase(),
                    Integer.toHexString(greenprogress).toUpperCase(),
                    Integer.toHexString(blueprogress).toUpperCase()
                )
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        textViewValue.text = "Value"
                    }
                    Configuration.ORIENTATION_PORTRAIT -> {
                        textViewValue.text =
                            "Value: " + (seekBarValue.progress.toFloat() / 100).toString()
                    }

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })


    }
    private fun getLocation(){
        val task=fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            var snackbar =Snackbar.make(constraintLayout, "Location permissions are not granted", Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction("Retry", View.OnClickListener {
                snackbar.dismiss()
                getLocation()
            })
            snackbar.show()
        }
        task.addOnSuccessListener {
            if(it!=null){
                locationtext=getColorString("${it.latitude}".toDouble())
            }
        }

    }

    private fun getColorString(latitude : Double) : String {
        seekBarValue.progress=((latitude % 1) * 100000).toInt()
        seekBarSaturation.progress=((latitude % 1) * 100000).toInt()
        seekBarHue.progress=((latitude % 1) * 100000).toInt()
        return resources.getString(
            R.string.locationString,
            ((latitude % 1) * 100000).roundToInt().toString().padStart(6, '0')
        )
    }

}