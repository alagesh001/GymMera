package com.example.gym.navgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.gym.AndroidViewModelProvider
import com.example.gym.screens.LoginScreen
import com.example.gym.screens.MainScreen
import com.example.gym.screens.SelectionScreen
import com.example.gym.viewmodel.GymViewModel

val LocalGymViewModel = staticCompositionLocalOf<GymViewModel> { error("No GymViewModel provided") }

@Composable
fun GymApp(modifier: Modifier = Modifier) {
    GymNavigation(
        modifier
    )
}


@Composable
fun GymNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel: GymViewModel = viewModel(factory = AndroidViewModelProvider.Factory)

    CompositionLocalProvider(LocalGymViewModel provides viewModel) {
        NavHost(
            navController = navController,
            startDestination = Login,
            modifier = modifier
        ) {
            composable<Login> {
                LoginScreen(
                    destination = {
                        navController.navigate(Selection)
                    }
                )
            }
            composable<Main> {
                val args = it.toRoute<Main>()
                MainScreen(
                    gymName = args.name,
                    location = args.location,
                )
            }
            composable<Selection> {
                SelectionScreen(destination = { name, location ->
                    navController.navigate(Main(name, location))
                })
            }
        }
    }
}
