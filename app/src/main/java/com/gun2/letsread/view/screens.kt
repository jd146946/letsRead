package com.gun2.letsread.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gun2.letsread.R

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("books") { BookScreen(navController) }
        composable("theme") { ThemeScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier){
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Books", "Profile", "Theme")

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        icon = {
                            when (title) {
                                "Home" -> Icon(Icons.Filled.Home, contentDescription = "Home")
//                                "Books" -> Image(
//                                painter = painterResource(id = R.drawable.books),
//                                  contentDescription = "Books Icon",
//                                  modifier = Modifier.size(24.dp)
//                                )
//                                "Profile" -> Icon(Icons.Filled.Person, contentDescription = "Profile")
//                                "Theme" -> Image(
//                                    painter = painterResource(id = R.drawable.palette),
//                                   contentDescription = "Theme Icon",
//                                    modifier = Modifier.size(24.dp)
//                                )
                                else -> Icon(Icons.Filled.Home, contentDescription = null)
                            }
                        },
                        label = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(96.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (selectedTab) {
                    0 -> Text("Home Screen")
                    1 -> Text("Books Screen")
                    2 -> Text("Profile Screen")
                    3 -> Text("Theme Screen")
                }
                Spacer(modifier = Modifier.height(16.dp))
//                Button(onClick = {
//                    Toast.makeText(context, "Home Button Clicked ", Toast.LENGTH_SHORT).show()}){
//                    Text("Click Me")
//                }
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier){

}

@Composable
fun BookScreen(navController: NavController, modifier: Modifier = Modifier){

}

@Composable
fun ThemeScreen(navController: NavController, modifier: Modifier = Modifier){

}