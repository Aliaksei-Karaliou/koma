package com.github.aliakseikaraliou.koma.matrix

import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixException
import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixInappropriateSizeException
import com.github.aliakseikaraliou.koma.matrix.immutable.ListMatrix
import com.github.aliakseikaraliou.koma.matrix.immutable.matrixFill
import com.github.aliakseikaraliou.koma.matrix.immutable.matrixOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MatrixCreateTest {

    @Test
    fun function() {
        val actual = matrixOf(3, 3) { i, j -> i * j * 2 }
        val expected = ListMatrix.create(3, 3, listOf(
                0, 0, 0,
                0, 2, 4,
                0, 4, 8
        ))

        assertEquals(expected, actual)
    }

    @Test
    fun arrays() {
        val actual = matrixOf(arrayOf(1, 2, 3, 4), arrayOf(100, 200, 30, 10))
        val expected = ListMatrix.create(2, 4, listOf(1, 2, 3, 4, 100, 200, 30, 10))

        assertEquals(expected, actual)

        assertThrows<MatrixException> { matrixOf(arrayOf(1, 2, 3, 4), arrayOf(100, 200, 30, 1, 0)) }
    }

    @Test
    fun lists() {
        val actual = matrixOf(listOf(listOf(1, 12, 3, 4), listOf(100, 200, 30, 10)))
        val expected = ListMatrix.create(2, 4, listOf(1, 12, 3, 4, 100, 200, 30, 10))

        assertEquals(expected, actual)

        assertThrows<MatrixException> { matrixOf(listOf(listOf(1, 2, 3, 4), listOf(30, 1, 0))) }
        assertThrows<MatrixEmptyException> { matrixOf(listOf<List<Int>>()) }
    }

    @Test
    fun base() {
        assertThrows<MatrixEmptyException> { ListMatrix.create(0, 1, listOf<Int>()) }
        assertThrows<MatrixInappropriateSizeException> { ListMatrix.create(2, 2, listOf(1, 2, 3, 4, 5)) }
    }

    @Test
    @DisplayName("fill()")
    fun fillTest() {
        val actual = matrixFill(2, 2, 10)
        val expected = ListMatrix.create(2, 2, listOf(10, 10, 10, 10))

        assertEquals(expected, actual)
    }
}
