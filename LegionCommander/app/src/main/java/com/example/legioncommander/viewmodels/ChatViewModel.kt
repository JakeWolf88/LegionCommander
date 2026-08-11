package com.example.legioncommander.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.legioncommander.model.UserPreferencesRepository
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserPreferencesRepository(application)
    
    private val _messages = mutableStateListOf<ChatMessage>()
    val messages: List<ChatMessage> = _messages

    fun sendMessage(userText: String) {
        if (userText.isBlank()) return

        _messages.add(ChatMessage(userText, true))
        
        viewModelScope.launch {
            try {
                val rawKey = repository.apiKey.first()
                val apiKey = rawKey?.trim() ?: ""
                
                // 1. Key Format Validation
                if (apiKey.isBlank()) {
                    _messages.add(ChatMessage("Error: No API Key found. Go to Settings.", false))
                    return@launch
                }
                
                if (!apiKey.startsWith("AIza")) {
                    _messages.add(ChatMessage("Error: This key is invalid. A real Gemini key MUST start with 'AIza'. You might be copying the Project ID or a Token by mistake.", false))
                    return@launch
                }

                // 2. Initialize Model with Persona
                val generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash", 
                    apiKey = apiKey,
                    systemInstruction = content {
                        text("You are a Star Wars Legion judge and tactical expert. " +
                             "You have perfect knowledge of the Core Rulebook and Errata. " +
                             "Answer questions concisely and helpfully during a match.")
                    }
                )

                val response = generativeModel.generateContent(userText)
                response.text?.let { 
                    _messages.add(ChatMessage(it, false))
                }
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Gemini API Error: ${e.message}", e)
                val rawError = e.localizedMessage ?: "Unknown Error"
                
                val userFriendlyError = when {
                    rawError.contains("404") -> "Error 404: Model Not Found. Verify 'Generative Language API' is enabled in Google Cloud Console for the project linked to this key."
                    rawError.contains("403") -> "Error 403: Permission Denied. Your key might be restricted or the API isn't fully enabled yet."
                    else -> "Error: $rawError"
                }
                
                _messages.add(ChatMessage(userFriendlyError, false))
            }
        }
    }

    fun saveApiKey(key: String) {
        viewModelScope.launch {
            repository.saveApiKey(key.trim())
        }
    }
    
    val userApiKey = repository.apiKey
}
