package com.tasty.recipesapp.ui.ProfileFragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.data.repositories.PreferencesManager
import com.tasty.recipesapp.data.repositories.RecipeRepository
import com.tasty.recipesapp.providers.RepositoryProvider

class UserProfileViewModel() : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> get() = _userEmail

    init {
        // Load user credentials from local storage
        val (name, email) = RepositoryProvider.preferencesManager.getUserCredentials()
        _userName.value = name
        _userEmail.value = email
    }

    fun updateUserProfile(name: String, email: String) {
        RepositoryProvider.preferencesManager.saveUserCredentials(name, email)
        _userName.value = name
        _userEmail.value = email
    }
}

