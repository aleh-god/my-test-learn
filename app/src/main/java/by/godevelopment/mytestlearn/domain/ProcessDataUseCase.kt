package by.godevelopment.mytestlearn.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProcessDataUseCase(
    private val repository: Repository,
    private val getCurrentSecondsUseCase: GetCurrentSecondsUseCase
) {

    fun getProcessData(): String = repository.getData().uppercase()

    fun saveProcessData(data: String) {
        repository.addDataToStorage(data.lowercase())
    }

    suspend fun getProcessSuspendData(): String = repository.getSuspendData().uppercase()

    fun getProcessFlow(): Flow<String> = repository.getDataAsFlow().map {
        StringBuilder(it).append(" ").append(getCurrentSecondsUseCase()).toString()
    }
}
