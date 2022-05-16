package by.godevelopment.mytestlearn.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ProcessDataUseCaseTest {

//    private val dataMock = mock<Repository>()
//    private val sut = ProcessDataUseCase(dataMock)

    @Test
    fun `check process to upper scale`() {
        val dataMock = mock<Repository>()
        val sut = ProcessDataUseCase(dataMock)

        whenever(dataMock.getData()).thenReturn("mock")
        val result = sut.getProcessData()
        assertEquals("MOCK", result)
    }

    @Test
    fun `save process data to repository`() {
        val dataMock = mock<Repository>()
        val sut = ProcessDataUseCase(dataMock)

        sut.saveProcessData("MOCK")
        verify(dataMock).addDataToStorage(eq("mock"))
    }

    @Test
    fun `check process in flow to upper scale`() = runBlocking<Unit> {
        val dataMock = mock<Repository>()
        val sut = ProcessDataUseCase(dataMock)

        whenever(dataMock.getSuspendData()).thenReturn("mock")
        val result = sut.getProcessSuspendData()
        assertEquals("MOCK", result)
    }
}