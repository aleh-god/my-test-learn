package by.godevelopment.mytestlearn.domain

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ProcessDataUseCaseTest {

    private lateinit var dataMock: Repository
    private lateinit var useCaseMock: GetCurrentSecondsUseCase
    private lateinit var sut: ProcessDataUseCase

    @Before
    fun setUp() {
        dataMock = mock()
        useCaseMock = mock()
        sut = ProcessDataUseCase(dataMock, useCaseMock)
    }

    @Test
    fun `check process to upper scale`() {
        whenever(dataMock.getData()).thenReturn("mock")
        val result = sut.getProcessData()
        assertEquals("MOCK", result)
    }

    @Test
    fun `save process data to repository`() {
        sut.saveProcessData("MOCK")
        verify(dataMock).addDataToStorage(eq("mock"))
    }

    @Test
    fun `check process in flow to upper scale`() = runBlocking {
        whenever(dataMock.getSuspendData()).thenReturn("mock")
        val result = sut.getProcessSuspendData()
        assertEquals("MOCK", result)
    }
}
