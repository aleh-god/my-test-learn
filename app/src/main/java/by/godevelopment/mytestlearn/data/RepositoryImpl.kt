package by.godevelopment.mytestlearn.data

import by.godevelopment.mytestlearn.domain.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl : Repository {
    override fun getDataAsFlow(): Flow<String> = TestDataSource.getDataAsFlow()

    override fun getData(): String = TestDataSource.getData()

    override fun addDataToStorage(data: String) = TestDataSource.addDataToStorage(data)

    override suspend fun getSuspendData(): String = TestDataSource.getSuspendData()
}