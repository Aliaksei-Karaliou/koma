package com.github.aliakseikaraliou.koma.matrix

import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.koma.matrix.immutable.matrixOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MatrixBaseTest {
    @Test
    fun get() {
        val actual = matrixOf(3, 2) { i, j -> i + j }

        assertEquals(0, actual[0, 0])
        assertEquals(1, actual[0, 1])
        assertEquals(1, actual[1, 0])
        assertEquals(2, actual[1, 1])
        assertEquals(2, actual[2, 0])
        assertEquals(3, actual[2, 1])

        assertThrows<MatrixIndexOutOfBoundsException> { actual[3, 0] }
        assertThrows<MatrixIndexOutOfBoundsException> { actual[0, 2] }
    }

    @Test
    @DisplayName("hashCode()")
    fun testHashCode() {
        val actual = matrixOf(3, 2) { i, j -> i + j }.hashCode()
        val expected = 888461956

        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("toString()")
     fun testToString() {
        val actual = matrixOf(3, 2) { i, j -> i + j }.toString()
        val expected = "[[0 1]\n[1 2]\n[2 3]]"

        assertEquals(expected, actual)
    }
}