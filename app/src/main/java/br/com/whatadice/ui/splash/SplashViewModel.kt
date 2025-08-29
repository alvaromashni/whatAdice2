package br.com.whatadice.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.whatadice.data.datastore.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface SplashState{
    data object Loading: SplashState
    data object goHome: SplashState
    data object goLogin: SplashState
}

class SplashViewModel(private val authRepository: AuthRepository) : ViewModel(){
    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state = _state.asStateFlow()

    fun start(){
        /* se diferente de loading, não faz nada  */
        if (_state.value != SplashState.Loading) return

        viewModelScope.launch {
            delay(600) // tempo de tela de splash

            val isLoggedIn = authRepository.isLoggedInOnce()
            _state.value = if (isLoggedIn) SplashState.goHome else SplashState.goLogin
        }
    }
}