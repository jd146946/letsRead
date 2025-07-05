package com.gun2.letsread.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gun2.letsread.R

sealed class BottomNavItem(val route: String, val label: String, val icon: @Composable () -> Unit) {
    object Home : BottomNavItem("home", "Home", { Icon(Icons.Filled.Home, contentDescription = "Home") })
    object Books : BottomNavItem("books", "Books", { Icon(painterResource(id = R.drawable.books), contentDescription = "Books Icon", modifier = Modifier.size(24.dp)) })
    object Profile : BottomNavItem("profile", "Profile", { Icon(painterResource(id = R.drawable.profile), contentDescription = "Profile Icon", modifier = Modifier.size(24.dp)) })
    object Theme : BottomNavItem("theme", "Theme", { Icon(painterResource(id = R.drawable.palette), contentDescription = "Theme Icon", modifier = Modifier.size(24.dp)) })
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Books,
    BottomNavItem.Profile,
    BottomNavItem.Theme
)

@Composable
fun MainScaffold(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { item.icon() },
                        label = { Text(item.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            // Add BooksScreen and ThemeScreen as needed
        }
    }
}