package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_over)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get score from intent
        val score = intent.getIntExtra("time", 0)
        findViewById<TextView>(R.id.score).text = "Score: $score"

        // Get high score from shared preferences
        val sharedPreferences = getSharedPreferences("game_data", MODE_PRIVATE)
        val bestScore = sharedPreferences.getInt("bestScore", 0)
        findViewById<TextView>(R.id.high_score).text = "All Time Best: $bestScore"

        // Go to the game activity
        findViewById<Button>(R.id.home_btn).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }


}