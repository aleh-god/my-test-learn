package by.godevelopment.mytestlearn.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.godevelopment.mytestlearn.domain.ProcessDataUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val processDataUseCase: ProcessDataUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<String> = MutableStateFlow(INIT_VALUE)
    val uiState: StateFlow<String> = _uiState

    private var job: Job? = null

    init {
        initFlow()
    }

    private fun initFlow() {
        job?.cancel()
        job = viewModelScope.launch {
            processDataUseCase.getProcessFlow()
                .catch { e -> _uiState.value = e.message.toString() }
                .collect { _uiState.value = it }
        }
    }

    fun provideMessage(): String = processDataUseCase.getProcessData()

    fun saveMessage(message: String) {
        processDataUseCase.saveProcessData(message)
    }

    companion object {
        private const val INIT_VALUE = "null"
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory constructor(
    private val processDataUseCase: ProcessDataUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(processDataUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}