package br.com.whatadice.core.navigation

/* Eu decidi usar uma 'sealed class' para garantir a
*  a escalabilidade do app depois, poderia usar enum, mas
*  esse é um projeto de interesse, então acho legal pensar
*  no futuro. */

sealed class Screen(val route: String) {
    data object Splash : Screen("Splash")
    data object Login : Screen("Login")
    data object Signup : Screen("Signup")
    data object Home : Screen("Home")
};