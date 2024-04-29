package com.example.game

import android.content.Intent
import android.content.SharedPreferences;
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.R.id.*
import com.example.game.R.drawable.*
import java.util.Timer
import java.util.TimerTask

// Import string resources
import com.example.game.R.string.backText as backText

class MainActivity : AppCompatActivity() {
    private val KEY_BEST_SCORE = "bestScore"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Card values
        val images: MutableList<Int> = mutableListOf(acard, bcard, ccard, dcard, ecard, fcard, gcard, hcard, acard, bcard, ccard, dcard, ecard, fcard, gcard, hcard)
        val back = blank
        val backText = backText.toString()

        // Game variables
        var click = 0
        var turnAgain = false
        var lastClicked = -1

        // Time counter
        val timeText = findViewById<TextView>(R.id.timeCounter)

        // SharedPreferences for storing best score
        sharedPreferences = getSharedPreferences("game_data", MODE_PRIVATE)
        val bestScore = sharedPreferences.getInt(KEY_BEST_SCORE, 0) // Default 0 if no score saved
        val bestScoreText = findViewById<TextView>(R.id.allTimeBest)
        bestScoreText.text = "Best: $bestScore"

        // Start the timer
        val timer = Timer()
        var time = 0



        val buttons = arrayOf(
            findViewById<Button>(button),
            findViewById(button2),
            findViewById(button3),
            findViewById(button4),
            findViewById(button5),
            findViewById(button6),
            findViewById(button7),
            findViewById(button8),
            findViewById(button9),
            findViewById(button10),
            findViewById(button11),
            findViewById(button12),
            findViewById(button13),
            findViewById(button14),
            findViewById(button15),
            findViewById(button16)
        )

        images.shuffle()

        for (i in 0..15) {
            buttons[i].text = backText
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                // Increment the timer with 1 second
                if (time == 0) {
                    timer.scheduleAtFixedRate(object : TimerTask() {
                        override fun run() {
                            time++
                            runOnUiThread {
                                timeText.text = "Time: $time"
                            }
                        }
                    }, 1000, 1000)
                }

                if (buttons[i].text == backText && !turnAgain) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (click == 0) {
                        lastClicked = i
                    }
                    click++
                } else if (buttons[i].text !in backText) {
                    buttons[i].setBackgroundResource(back)
                    buttons[i].text = backText
                    click--
                }

                if (click == 2) {
                    turnAgain = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnAgain = false
                        click = 0
                    } else {
                        val last = lastClicked
                        val handler = android.os.Handler()
                        handler.postDelayed({
                            buttons[last].setBackgroundResource(back)
                            buttons[last].text = backText
                            buttons[i].setBackgroundResource(back)
                            buttons[i].text = backText
                            turnAgain = false
                            click = 0
                        }, 500)
                    }
                } else if (click == 0) {
                    turnAgain = false
                }

                // Check if all cards are flipped
                var allMatched = true
                for (button in buttons) {
                    if (button.text in backText) {
                        allMatched = false
                        break
                    }
                }
                if (allMatched) {
                    timer.cancel()
                    val editor = sharedPreferences.edit()
                    if (bestScore == 0 || time < bestScore) {
                        editor.putInt(KEY_BEST_SCORE, time)
                        editor.apply()
                        bestScoreText.text = "Best: $time"
                    }

                    // Go to the game over activity and pass the timer value
                    val intent = Intent(this, OverActivity::class.java)
                    intent.putExtra("time", time)
                    startActivity(intent)
                }
            }
        }
    }
}