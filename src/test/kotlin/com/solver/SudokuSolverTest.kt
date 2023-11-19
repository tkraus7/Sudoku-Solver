package com.solver

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class SudokuSolverTest {

    private lateinit var solver: SudokuSolver
    private val board = arrayOf(
        arrayOf(7, 1, 3),
        arrayOf(9, 0, 2),
        arrayOf(4, 6, 8)
    )

    private val board2: Array<Array<Int>> = arrayOf(
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

    val board3: Array<Array<Int>> = arrayOf(
        arrayOf(7, 8, 1, 4, 0, 0, 1, 2, 0),
        arrayOf(6, 0, 0, 0, 7, 5, 0, 0, 9),
        arrayOf(0, 0, 0, 6, 0, 1, 0, 7, 8),
        arrayOf(0, 0, 7, 0, 4, 0, 2, 6, 0),
        arrayOf(0, 0, 1, 0, 5, 0, 9, 3, 0),
        arrayOf(9, 0, 4, 0, 6, 0, 0, 0, 5),
        arrayOf(0, 7, 0, 3, 0, 0, 0, 1, 2),
        arrayOf(1, 2, 0, 0, 0, 7, 4, 0, 0),
        arrayOf(0, 4, 9, 2, 0, 6, 0, 0, 7),
    )

    @BeforeEach
    fun setUp() {
        solver = SudokuSolver(Array(1) { Array(1) { 0 } })
    }

    @Test
    fun isValid() {
        assertTrue(solver.isValid(5, 1, 1, board))
        assertFalse(solver.isValid(1, 1, 1, board))
    }

    @Test
    fun isBoardValid() {
        assertTrue(solver.isBoardValid(board2))
        assertFalse(solver.isBoardValid(board3))
    }
}