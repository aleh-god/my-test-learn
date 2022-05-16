package by.godevelopment.mytestlearn.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DataSource {

    private var storage: String = ""

    fun getDataAsFlow(): Flow<String>  = flow {
        emit(storage)
    }

    fun getData(): String = storage

    fun addDataToStorage(data: String) {
        storage = data
    }
}