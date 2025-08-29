package br.com.whatadice.data.datastore
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

data class LocalUser(
    val username: String,
    val email: String
)

class AuthRepository(private val context: Context){

    private object Keys {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USERNAME = stringPreferencesKey("username")
        val EMAIL = stringPreferencesKey("email")
    }
    private val dataStore = context.appDataStore

    //  verificar essa lógica
    val isLoggedInFlow: Flow<Boolean> =
        dataStore.data.map { it[Keys.IS_LOGGED_IN] ?: false}

    val currentUserFlow: Flow<LocalUser?> =
        dataStore.data.map { prefs ->
            val username = prefs[Keys.USERNAME]
            val email = prefs[Keys.EMAIL]

            /* se não preencher email e username retorna null else retorna o localuser */
            if (username.isNullOrBlank() || email.isNullOrBlank()) null else LocalUser(username, email)
        }
    // suspend fun é usada no contexto de corrotina, permite pausar a execução e emprestar a thread pra outra função mais longa
    suspend fun isLoggedInOnce(): Boolean {
        return dataStore.data.map { it[Keys.IS_LOGGED_IN] ?: false }
            .first()
    }

    suspend fun logout() {
        dataStore.edit { prefs ->
            // desloga e mantem os dados do ultimo usuario
            prefs[Keys.IS_LOGGED_IN] = false
        }
    }
}