package org.yuezhikong.flyn.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.yuezhikong.flyn.ui.home.ConsultationScreen
import org.yuezhikong.flyn.ui.home.HomeScreen
import org.yuezhikong.flyn.ui.home.LegalScreen
import org.yuezhikong.flyn.ui.home.UserScreen
import org.yuezhikong.flyn.ui.home.SettingScreen
import org.yuezhikong.flyn.ui.user.LoginScreen
import org.yuezhikong.flyn.ui.user.ProfileScreen
import org.yuezhikong.flyn.ui.user.SignupScreen

private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultEnterTransition() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        tween(300)) + fadeIn(tween(300)
        )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultExitTransition() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        tween(300)) + fadeOut(tween(300)
        )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopEnterTransition() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        tween(300)) + fadeIn(tween(300)
        )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopExitTransition() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        tween(300)) + fadeOut(tween(300)
        )

@Composable
fun SetupNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        route = "navigation_graph",
        modifier = modifier
    ) {
        composable(
            "home",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
            ) { HomeScreen() }
        composable("legal") { LegalScreen() }
        composable("consultation") { ConsultationScreen() }
        composable(
            "user" ,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) { UserScreen(navController) }
        composable(
            "setting" ,
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() },
            popEnterTransition = { defaultPopEnterTransition() },
            popExitTransition = { defaultPopExitTransition() },
        ) { SettingScreen() }
        composable("login",
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() },
            popEnterTransition = { defaultPopEnterTransition() },
            popExitTransition = { defaultPopExitTransition() },
        ) { LoginScreen(navController) }
        composable("signin",
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() },
            popEnterTransition = { defaultPopEnterTransition() },
            popExitTransition = { defaultPopExitTransition() },
            ) { SignupScreen(navController) }
        composable("profile",
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() },
            popEnterTransition = { defaultPopEnterTransition() },
            popExitTransition = { defaultPopExitTransition() },
        ) { ProfileScreen() }
    }
}
