package br.com.whatadice.core.navigation

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.whatadice.core.navigation.Screen.Splash
import br.com.whatadice.core.navigation.Screen.Home
import br.com.whatadice.core.navigation.Screen.Login
import br.com.whatadice.core.navigation.Screen.Signup
import br.com.whatadice.ui.splash.SplashScreen
import br.com.whatadice.ui.home.HomeScreen
import br.com.whatadice.ui.login.LoginScreen
import br.com.whatadice.ui.signup.SignupScreen

@Composable
fun appNavGraph(modifier: Modifier = Modifier, startDestination: String = Splash.route){
    val navController = rememberNavController()
    val navActions = remember(navController) { NavActions(navController) }

    NavHost(navController = navController, startDestination = startDestination, modifier = modifier){
        composable(Splash.route){
            SplashScreen(
                onNavigateToLogin = { navActions.toLoginFromSplash()},
                logged = { navActions.toHomeFromSplash() }
            )
        }
        composable(Home.route){
            HomeScreen(
                onLogout = { navActions.clearAndGoToLogin()}
            )
        }
        composable(Login.route){
            LoginScreen(
                onNavigateToSignup = { navActions.goToSignup() },
                onNavigateToHome = { navActions.goToHome() }
            )
        }
        composable(Signup.route){
            SignupScreen(
                onNavigateToLogin = { navActions.goToLogin() }
            )
        }
    }
}