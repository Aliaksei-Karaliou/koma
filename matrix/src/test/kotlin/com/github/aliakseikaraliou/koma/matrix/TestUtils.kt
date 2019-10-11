package com.github.aliakseikaraliou.koma.matrix

import org.junit.jupiter.api.Assertions
import java.lang.Exception

inline fun <reified T : Exception> assertThrows(crossinline function: () -> Unit) = Assertions.assertThrows(T::class.java) { function() }