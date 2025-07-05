package com.gun2.letsread.model

data class UserProfile(
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = "",
    val gender: String = "Prefer not to share",
    val profilePictureRes: Int? = null // resource id or url
)
