package com.github.aliakseikaraliou.koma.matrix.exceptions

class MatrixInappropriateSizeException(height: Int, width: Int, size: Int) :
        MatrixException("Matrix has inappropriate size. Height:$height, width:$width, size:$size")