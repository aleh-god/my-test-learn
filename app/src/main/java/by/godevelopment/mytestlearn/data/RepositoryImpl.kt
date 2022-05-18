package by.godevelopment.mytestlearn.data

import by.godevelopment.mytestlearn.domain.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl : Repository {
    override fun getDataAsFlow(): Flow<String> =
        TestDataSource.getSourceAsFlow()

    override fun getData(): String = TestDataSource.getSource()

    override fun addDataToStorage(data: String) = TestDataSource.changeSource(data)

    override suspend fun getSuspendData(): String = TestDataSource.getSuspendSource()
}