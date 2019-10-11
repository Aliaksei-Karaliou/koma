package com.github.aliakseikaraliou.koma.matrix.immutable

interface Matrix<T> {
    val height: Int
    val width: Int

    operator fun get(x: Int, y: Int):T
}
