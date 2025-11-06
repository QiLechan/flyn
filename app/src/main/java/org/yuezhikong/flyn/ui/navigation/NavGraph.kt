package org.yuezhikong.flyn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.yuezhikong.flyn.ui.home.ConsultationScreen
import org.yuezhikong.flyn.ui.home.HomeScreen
import org.yuezhikong.flyn.ui.home.LegalScreen
import org.yuezhikong.flyn.ui.home.UserScreen
import org.yuezhikong.flyn.ui.home.SettingScreen
import org.yuezhikong.flyn.ui.user.LoginScreen

@Composable
fun SetupNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        route = "navigation_graph",
        modifier = modifier
    ) {
        composable("home") { HomeScreen() }
        composable("legal") { LegalScreen() }
        composable("consultation") { ConsultationScreen() }
        composable("user") { UserScreen(navController) }
        composable("setting") { SettingScreen() }
        composable("login") { LoginScreen() }
    }
}
