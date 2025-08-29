package br.com.whatadice.ui.splash

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.whatadice.R
import br.com.whatadice.core.navigation.appNavGraph

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    logged: () -> Unit,
    viewModel: SplashViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.start() }

    // design da interface
    Box(Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.size(96.dp) // ajustar
            )
            Spacer(Modifier.height(12.dp))
            CircularProgressIndicator()
        }

        Crossfade(
            targetState = state,
            modifier = Modifier.align(Alignment.Center)
        ) { onState ->
            when (onState) {
                is SplashState.Loading -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(Modifier.height(12.dp))
                        Text("Carregando...")
                    }
                }

                is SplashState.goHome -> {
                    LaunchedEffect("goHome") { logged() }
                }

                is SplashState.goLogin -> {
                    LaunchedEffect("goLogin") { onNavigateToLogin() }
                }
            }
        }
    }
}