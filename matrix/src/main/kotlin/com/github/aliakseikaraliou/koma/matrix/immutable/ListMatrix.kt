package com.github.aliakseikaraliou.koma.matrix.immutable

import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixException
import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixInappropriateSizeException
import com.github.aliakseikaraliou.koma.matrix.exceptions.MatrixIndexOutOfBoundsException

class ListMatrix<T> private constructor(override val height: Int,
                                        override val width: Int,
                                        private val source: List<T>) : Matrix<T> {

    override fun get(x: Int, y: Int): T {
        if (x >= height) {
            throw MatrixIndexOutOfBoundsException("Out of bounds. Index: $x, matrix height: $height")
        }

        if (y >= width) {
            throw MatrixIndexOutOfBoundsException("Out of bounds. Index: $y, matrix width: $width")
        }

        return source[x * width + y]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListMatrix<*>

        return height == other.height && width == other.width && source == other.source
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + source.hashCode()
        return result + 31
    }

    override fun toString(): String {
        val builder = StringBuilder()

        for (i in 0 until height) {
            val line = StringBuilder()

            for (j in 0 until width) {
                line.append("${this[i, j]} ")
            }

            builder.append("[${line.trim()}]")

            if (i + 1 != height) {
                builder.append("\n")
            }
        }

        return "[$builder]"
    }


    companion object {
        fun <T> create(height: Int, width: Int, source: List<T>): ListMatrix<T> {
            if (height * width != source.size) {
                throw MatrixInappropriateSizeException(height, width, source.size)
            }

            if (source.isEmpty()) {
                throw MatrixEmptyException()
            }

            return ListMatrix(height, width, source)
        }
    }
}

fun <T> matrixOf(height: Int, width: Int, creator: (Int, Int) -> T): Matrix<T> {
    val list = ArrayList<T>(height * width)

    for (i in 0 until height) {
        for (j in 0 until width) {
            list.add(creator(i, j))
        }
    }

    return ListMatrix.create(height, width, list)
}

fun <T> matrixOf(array1: Array<T>, vararg arrays: Array<T>): Matrix<T> {
    val height = arrays.size + 1
    val width = array1.size

    if (arrays.any { it.size != width }) {
        throw MatrixException("Cannot create matrix with current arrays size. One of the array size is not equals to others")
    }

    val total = arrays.map { it.toList() }.toMutableList()
    total.add(0, array1.toList())

    return ListMatrix.create(height, width, total.flatten())
}

fun <T> matrixOf(lists: List<List<T>>): Matrix<T> {
    val height = lists.size

    if (lists.isEmpty()) {
        throw MatrixEmptyException()
    }

    val width = lists[0].size

    if (lists.any { it.size != width }) {
        throw MatrixException("Cannot create matrix with current lists size. One of the lists size is not equals to others")
    }

    return ListMatrix.create(height, width, lists.flatten())
}

fun <T> matrixFill(height: Int, width: Int, item: T) = matrixOf(height, width) { _, _ -> item }

