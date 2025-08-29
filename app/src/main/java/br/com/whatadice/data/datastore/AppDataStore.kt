package br.com.whatadice.data.datastore
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.appDataStore by preferencesDataStore("app_prefs")