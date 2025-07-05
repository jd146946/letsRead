package com.gun2.letsread.view

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gun2.letsread.R
import com.gun2.letsread.model.UserProfile
import com.gun2.letsread.viewmodel.ProfileViewModel
import java.util.*

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val context = LocalContext.current
    val profile = viewModel.userProfile
    val isSaved = viewModel.isSaved

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { /* TODO: Launch image picker */ }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = profile.firstName,
            onValueChange = { viewModel.onProfileChanged(profile.copy(firstName = it)) },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = profile.lastName,
            onValueChange = { viewModel.onProfileChanged(profile.copy(lastName = it)) },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = profile.dob,
            onValueChange = {},
            label = { Text("Date of Birth") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _: DatePicker, year: Int, month: Int, day: Int ->
                            viewModel.onProfileChanged(profile.copy(dob = "$day/${month + 1}/$year"))
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Gender", style = MaterialTheme.typography.titleMedium)
        val genderOptions = listOf("Male", "Female", "LGBTQ+", "Prefer not to share")
        genderOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { viewModel.onProfileChanged(profile.copy(gender = option)) }
            ) {
                RadioButton(
                    selected = profile.gender == option,
                    onClick = { viewModel.onProfileChanged(profile.copy(gender = option)) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(option)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            viewModel.updateProfile(profile)
        }) {
            Text("Save Profile")
        }

        if (isSaved) {
            Text("Profile Saved!", color = MaterialTheme.colorScheme.primary)
        }
    }
}
