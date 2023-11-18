package com.solver


class SudokuSolver(val board: Array<Array<Int>>) {

    fun isValid(value: Int, x: Int, y: Int): Boolean {
        for (i in 0..board.size) {
            if (value == board[i][y] || value == board[x][i]) return false
        }

        val segmentRowIndex = x / 3
        val segmentColumnIndex = y / 3

        for (i in 0..2) {
            for (j in 0..2) {
                if (value == board[segmentRowIndex*3 + i][segmentColumnIndex*3+j]) return false
            }
        }
        return true
    }

    fun solve(x: Int, y: Int) {
//        if (x > 8 || y > 8) return

        for (i in 1..9) {

        }
    }


    fun printBoard() {
        for (row in board) {
            for (elem in row) {
                print("$elem ")
            }
            println()
        }
        println()
    }
}


fun main() {
    val board: Array<Array<Int>> = arrayOf(
        arrayOf(7, 8, 0, 4, 0, 0, 1, 2, 0),
        arrayOf(6, 0, 0, 0, 7, 5, 0, 0, 9),
        arrayOf(0, 0, 0, 6, 0, 1, 0, 7, 8),
        arrayOf(0, 0, 7, 0, 4, 0, 2, 6, 0),
        arrayOf(0, 0, 1, 0, 5, 0, 9, 3, 0),
        arrayOf(9, 0, 4, 0, 6, 0, 0, 0, 5),
        arrayOf(0, 7, 0, 3, 0, 0, 0, 1, 2),
        arrayOf(1, 2, 0, 0, 0, 7, 4, 0, 0),
        arrayOf(0, 4, 9, 2, 0, 6, 0, 0, 7),
    )

    val board2 = arrayOf(
        arrayOf(7,1,3),
        arrayOf(9,0,2),
        arrayOf(4,6,8)
    )

    val ss = SudokuSolver(board)
    ss.printBoard()
    println(ss.isValid(5, 1,1))

}