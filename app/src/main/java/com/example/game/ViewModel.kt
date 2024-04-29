package com.example.game

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(private val application: Application) : ViewModel() {
    private val _bestScore = MutableLiveData<Int>()
    val bestScore: LiveData<Int> = _bestScore

    init {
        // Get high score from shared preferences
        val sharedPreferences = application.getSharedPreferences("game_data", AppCompatActivity.MODE_PRIVATE)
        val bestScore = sharedPreferences.getInt("bestScore", 0)
        _bestScore.value = bestScore
    }

    fun updateBestScore(newScore: Int) {
        // Update best score
        _bestScore.value = newScore
    }
}