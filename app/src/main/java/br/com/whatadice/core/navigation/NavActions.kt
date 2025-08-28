package br.com.whatadice.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

class NavActions(private val navController: NavHostController) {

    fun goBack(){
        navController.navigateUp()
    }

    fun goToHome(restore : Boolean = true){
        /* - launchsingletop > impede que a tela seja recriada várias vezes
         * - restoreState true > mantém o estado da tela usada antes
         * - popUpTo + {inclusive false} > volta pro início da nav stack */
        navController.navigate(Screen.Home, navOptions{
            launchSingleTop = true
            if (restore) restoreState = true
            popUpTo(navController.graph.startDestinationId) {inclusive = false}
        })
    }

    fun goToLogin(){
        navController.navigate(Screen.Login, navOptions {
            launchSingleTop = true
            popUpTo(navController.graph.startDestinationId) {inclusive = false}
        })
    }

    fun goToSignup(){
        navController.navigate(Screen.Signup)
    }

    fun backToLoginWithSuccess(){
        /* - Usada quando concluir o cadastro, para voltar ao login */
        navController.navigate(Screen.Login)
    }
}