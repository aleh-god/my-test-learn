package by.godevelopment.mytestlearn.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RepositoryImplTest {

    private lateinit var sut: RepositoryImpl
    private lateinit var dataSourceMock: TestDataSource

    @Before
    fun setUp() {
        dataSourceMock = mock()
        sut = RepositoryImpl(dataSourceMock)
    }

    @Test
    fun getDataAsFlow(): Unit = runBlocking {
        sut.getDataAsFlow()
        verify(dataSourceMock).getSourceAsFlow()
    }

    @Test
    fun getData() {
        sut.getData()
        verify(dataSourceMock).getSource()
    }

    @Test
    fun addDataToStorage() {
        sut.addDataToStorage("data")
        verify(dataSourceMock).changeSource("data")
    }

    @Test
    fun getSuspendData(): Unit = runBlocking {
        sut.getSuspendData()
        verify(dataSourceMock).getSuspendSource()
    }
}