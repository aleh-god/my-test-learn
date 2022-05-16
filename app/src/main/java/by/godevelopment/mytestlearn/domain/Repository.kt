package by.godevelopment.mytestlearn.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getDataAsFlow(): Flow<String>

    fun getData(): String

    fun addDataToStorage(data: String)

    suspend fun getSuspendData(): String
}