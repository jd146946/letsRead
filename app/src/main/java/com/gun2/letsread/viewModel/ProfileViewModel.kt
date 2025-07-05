package com.gun2.letsread.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gun2.letsread.model.UserProfile

class ProfileViewModel : ViewModel() {
    var userProfile by mutableStateOf(UserProfile())
        private set

    var isSaved by mutableStateOf(false)
        private set

    fun updateProfile(newProfile: UserProfile) {
        userProfile = newProfile
        isSaved = true
    }

    fun onProfileChanged(newProfile: UserProfile) {
        userProfile = newProfile
        isSaved = false
    }
}