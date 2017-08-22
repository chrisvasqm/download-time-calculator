package com.christianv07.dev.speedy

import com.christianv07.dev.speedy.extension.isZeroOrNegative
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class DoubleExtensionTests {

    @Test
    fun isZeroOrNegative_isZero_ReturnsTrue() = assertTrue(0.0.isZeroOrNegative())

    @Test
    fun isZeroOrNegative_isGreaterThanZero_ReturnsFalse() = assertFalse(5.0.isZeroOrNegative())

}