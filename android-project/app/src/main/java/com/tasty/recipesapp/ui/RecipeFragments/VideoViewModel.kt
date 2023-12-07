package com.tasty.recipesapp.ui.RecipeFragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private val _isVideoReady = MutableLiveData<Boolean>()
    val isVideoReady: LiveData<Boolean> get() = _isVideoReady

    fun setVideoPath(videoUrl: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Simulate a background task, replace with actual code to set video path
                Thread.sleep(2000)
                _isVideoReady.postValue(true)
            } catch (e: IOException) {
                e.printStackTrace()
                _isVideoReady.postValue(false)
            }
        }
    }
}