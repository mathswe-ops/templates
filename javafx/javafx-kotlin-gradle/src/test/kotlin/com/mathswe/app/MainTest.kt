package com.mathswe.app

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class MainTest {
    @Test
    fun sumsTwoNumbers() {
        assertEquals(4, sum(2, 2))
    }
}
