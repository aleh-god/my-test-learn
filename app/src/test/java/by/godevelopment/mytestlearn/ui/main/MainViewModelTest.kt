package by.godevelopment.mytestlearn.ui.main

import by.godevelopment.mytestlearn.domain.ProcessDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MainViewModelTest {

    private val INIT_VALUE = "null"
    private lateinit var processDataUseCaseMock: ProcessDataUseCase
    private lateinit var sut: MainViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        processDataUseCaseMock = mock()
        sut = MainViewModel(processDataUseCaseMock)
    }

    @Test
    fun getUiState(): Unit = runBlocking {
        val result = sut.uiState.first()
        assertEquals(result, INIT_VALUE)
    }

    @Test
    fun provideMessage() {
        sut.provideMessage()
        verify(processDataUseCaseMock).getProcessData()
    }

    @Test
    fun saveMessage() {
        sut.saveMessage(INIT_VALUE)
        verify(processDataUseCaseMock).saveProcessData(INIT_VALUE)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}
