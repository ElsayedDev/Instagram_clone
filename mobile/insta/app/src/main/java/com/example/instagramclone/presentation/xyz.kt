package com.example.instagramclone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SnackBarScreenViewModel : ViewModel() {

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

     var message: String = ""

    fun setMessageShown(message: String) {
        this.message = message
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }


    }
}