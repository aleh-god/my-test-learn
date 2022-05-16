package by.godevelopment.mytestlearn.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object TestDataSource {

    private var storage: String = "TestDataSource"

    fun getDataAsFlow(): Flow<String>  = flow {
        emit(storage)
    }

    suspend fun getSuspendData(): String {
        delay(1000)
        return storage
    }

    fun getData(): String = storage

    fun addDataToStorage(data: String) {
        storage = data
    }
}