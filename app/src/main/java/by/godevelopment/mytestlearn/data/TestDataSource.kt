package by.godevelopment.mytestlearn.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object TestDataSource {

    private const val refreshIntervalMs = 1000L
    private var source: String = "TestDataSource"

    fun getSourceAsFlow(): Flow<String>  = flow {
        while(true) {
            emit(source)
            delay(refreshIntervalMs)
        }
    }

    suspend fun getSuspendSource(): String {
        delay(refreshIntervalMs)
        return source
    }

    fun getSource(): String = source

    fun changeSource(data: String) {
        source = data
    }
}
