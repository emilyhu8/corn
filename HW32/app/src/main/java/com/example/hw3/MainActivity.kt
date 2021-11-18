package com.example.hw3

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val max = 255

    private lateinit var seekBarRed : SeekBar
    private lateinit var seekBarBlue : SeekBar
    private lateinit var seekBarGreen: SeekBar
    private lateinit var textViewRed: TextView
    private lateinit var textViewBlue: TextView
    private lateinit var textViewGreen: TextView
    private lateinit var colorSquare : View
    private lateinit var hexColorText : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Initialize the UI components
        seekBarRed = findViewById(R.id.seekBarRed)
        seekBarGreen = findViewById(R.id.seekBarGreen)
        seekBarBlue = findViewById(R.id.seekBarBlue)
        textViewRed = findViewById(R.id.textViewRed)
        textViewGreen = findViewById(R.id.textViewGreen)
        textViewBlue = findViewById(R.id.textViewBlue)
        colorSquare = findViewById(R.id.color_square)
        hexColorText = findViewById(R.id.textViewHexColor)


        /*
        Retrieve the data from HSV activity
        seekBarRed.progress= intent.extras?.getInt("Red")!!
        seekBarBlue.progress= intent.extras?.getInt("Blue")!!
        seekBarGreen.progress= intent.extras?.getInt("Green")!!
        colorSquare.setBackgroundColor(
            Color.rgb(seekBarRed.progress, seekBarGreen.progress, seekBarBlue.progress)
        )

         */
        setUpSeekbar(seekBarRed, textViewRed, resources.getString(R.string.red))
        setUpSeekbar(seekBarGreen, textViewGreen, resources.getString(R.string.green))
        setUpSeekbar(seekBarBlue, textViewBlue, resources.getString(R.string.blue))


        // Initialize the square's color on onCreate()
        regenerateColor()

        val click: Button = findViewById(R.id.buttonHSV)
        click.setOnClickListener() {
            var intent = Intent(this, hsvActivity::class.java)
            intent.putExtra("Red", seekBarRed.progress)
            intent.putExtra("Blue", seekBarBlue.progress)
            intent.putExtra("Green", seekBarGreen.progress)
            startActivity(intent);
        }


        val share: Button =findViewById(R.id.sharebutton)
        share.setOnClickListener(){
            val message = "Check out this color: " + hexColorText.text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here")
            startActivity(Intent.createChooser(shareIntent, "Share text via"))


        }
    }

    private fun initialSetUp(sb: SeekBar, tv: TextView, color: String) {
        // Set initial color to random number

            val randNum = Random.nextInt(0, 256)
            sb.progress = randNum
            tv.text = resources.getString(R.string.seekbarLabel, color, randNum)

    }

    private fun setUpSeekbar(sb: SeekBar, tv: TextView, color : String) {
        // Set the max value of seekbar to max hexcode - 255
        sb.max = max
        initialSetUp(sb, tv, color)

        sb.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                regenerateColor()

                // Set TextView based on orientation
                updateSeekBarTextView(tv, color, p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    // Modifies text label next to SeekBar depending on device orientation
    private fun updateSeekBarTextView(tv: TextView, color: String, progress: Int) {
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                tv.text = color
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                tv.text = resources.getString(R.string.seekbarLabel, color, progress)
            }
        }
    }

    // Regenerates the color of the color square.
    private fun regenerateColor() {
        colorSquare.setBackgroundColor(
            Color.rgb(seekBarRed.progress, seekBarGreen.progress, seekBarBlue.progress)
        )

        hexColorText.text = resources.getString(
            R.string.hexString,
            Integer.toHexString(seekBarRed.progress).toUpperCase(),
            Integer.toHexString(seekBarGreen.progress).toUpperCase(),
            Integer.toHexString(seekBarBlue.progress).toUpperCase()
        )


    }
}