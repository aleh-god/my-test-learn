package by.godevelopment.mytestlearn.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.godevelopment.mytestlearn.domain.ProcessDataUseCase

class MainViewModel constructor(
    private val processDataUseCase: ProcessDataUseCase
) : ViewModel() {

    fun provideMessage(): String = processDataUseCase.getProcessData()
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