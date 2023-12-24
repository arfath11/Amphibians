package com.example.amphibians.ui.theme.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmpApplication
import com.example.amphibians.data.AmpDataRepository
import com.example.amphibians.model.AmpData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface AmpUiState {
    data class Success(val photos: List<AmpData>) : AmpUiState
    object Error : AmpUiState
    object Loading : AmpUiState
}

class AmpViewModel(private val  ampDataRepository: AmpDataRepository): ViewModel() {

    var ampUiState: AmpUiState by  mutableStateOf(AmpUiState.Loading)
    private set
    init {
        getAmpPhotos()
    }

     fun getAmpPhotos() {
         viewModelScope.launch {
             ampUiState = AmpUiState.Loading
             ampUiState = try {
                 val result = ampDataRepository.getAmpData()
                 AmpUiState.Success(
                     result
                 )
             } catch (e: IOException) {
                 AmpUiState.Error
             } catch (e: HttpException) {
                 AmpUiState.Error
             }
         }
    }





    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmpApplication)
                val ampDataRepository = application.container.ampDataRepository
                AmpViewModel(ampDataRepository = ampDataRepository)
            }
        }
    }

}