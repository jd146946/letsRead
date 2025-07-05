package com.gun2.letsread.model

interface UserRepository {
    fun getUserProfile(): UserProfile
    fun updateUserProfile(profile: UserProfile)
}

class InMemoryUserRepository : UserRepository {
    private var userProfile = UserProfile()
    override fun getUserProfile() = userProfile
    override fun updateUserProfile(profile: UserProfile) {
        userProfile = profile
    }
}