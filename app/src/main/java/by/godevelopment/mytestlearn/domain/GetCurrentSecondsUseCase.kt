package by.godevelopment.mytestlearn.domain

import java.util.Calendar

class GetCurrentSecondsUseCase {
    operator fun invoke(): Int {
        return Calendar.getInstance().get(Calendar.SECOND)
    }
}
