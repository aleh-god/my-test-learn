package by.godevelopment.mytestlearn.data

import by.godevelopment.mytestlearn.domain.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val testDataSource: TestDataSource
) : Repository {
    override fun getDataAsFlow(): Flow<String> =
        testDataSource.getSourceAsFlow()

    override fun getData(): String = testDataSource.getSource()

    override fun addDataToStorage(data: String) = testDataSource.changeSource(data)

    override suspend fun getSuspendData(): String = testDataSource.getSuspendSource()
}