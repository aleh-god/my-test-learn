package by.godevelopment.mytestlearn.domain

class ProcessDataUseCase(
    private val repository: Repository
) {

    fun getProcessData(): String = repository.getData().uppercase()

    fun saveProcessData(data: String) {
        repository.addDataToStorage(data.lowercase())
    }

    suspend fun getProcessSuspendData(): String = repository.getSuspendData().uppercase()
}
