package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MenuActivity : AppCompatActivity() {
    private lateinit var viewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        //viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // Set high score
        //viewModel.bestScore.observe(this, Observer { score ->
            // Update UI with the best score
            //findViewById<TextView>(R.id.score).text = "$score"
        //})

        // Get high score from shared preferences
        val sharedPreferences = getSharedPreferences("game_data", MODE_PRIVATE)
        val bestScore = sharedPreferences.getInt("bestScore", 0)
        findViewById<TextView>(R.id.score).text = "$bestScore"

        // Go to the game activity
        findViewById<Button>(R.id.startGame).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}