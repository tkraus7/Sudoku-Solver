package com.gui

import com.solver.SudokuSolver
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.util.Duration

class SudokuSolverController {
    private val GRID_BOX_SIZE = 600.0
    private val MAX_FONT_SIZE = 30.0

    private val alert = Alert(Alert.AlertType.ERROR)

    @FXML
    private lateinit var bp: BorderPane

    @FXML
    private lateinit var gridPane: GridPane

    private var labels = Array(9) { Array(9) { Label() } }

    private var currentLabel: Label? = null
    private var currentBox: VBox? = null
    private var currentStyle: String? = null

    private fun setKeyListener() {
        val stage = bp.scene.window
        stage.addEventFilter(KeyEvent.KEY_PRESSED) { e ->
            if (currentLabel == null) return@addEventFilter
            when (e.code) {
                KeyCode.NUMPAD1 -> currentLabel!!.text = "1"
                KeyCode.NUMPAD2 -> currentLabel!!.text = "2"
                KeyCode.NUMPAD3 -> currentLabel!!.text = "3"
                KeyCode.NUMPAD4 -> currentLabel!!.text = "4"
                KeyCode.NUMPAD5 -> currentLabel!!.text = "5"
                KeyCode.NUMPAD6 -> currentLabel!!.text = "6"
                KeyCode.NUMPAD7 -> currentLabel!!.text = "7"
                KeyCode.NUMPAD8 -> currentLabel!!.text = "8"
                KeyCode.NUMPAD9 -> currentLabel!!.text = "9"
                KeyCode.BACK_SPACE -> currentLabel!!.text = ""
                else -> return@addEventFilter
            }
        }
    }

    private fun setCurrent(cl: Label, cb: VBox) {
        currentLabel?.textFill = Color.BLACK
        currentBox?.style = currentStyle

        currentLabel = cl
        currentBox = cb
        currentStyle = currentBox!!.style

        currentLabel!!.textFill = Color.RED
        currentBox!!.style = "-fx-border-color: red; -fx-border-width: 3px; -fx-background-color:" +
                currentStyle!!.substring(currentStyle!!.lastIndexOf(" " + 1))
    }

    fun updateCurrent(value: Int, x: Int, y: Int) {

        val cl = labels[x][y]
        cl.textFill = Color.BLUE
        cl.text = if (value != 0) "$value" else ""
        val cb = cl.parent as VBox
        setCurrent(cl, cb)
    }

    private fun Int.isOdd(): Boolean {
        return this % 2 == 0
    }

    private fun Int.isEven(): Boolean {
        return !this.isOdd()
    }

    @FXML
    private fun createGrid() {
        gridPane.isVisible = true
        gridPane.minHeight = GRID_BOX_SIZE
        gridPane.minWidth = GRID_BOX_SIZE


        for (i in 0..8) {
            for (j in 0..8) {
                labels[i][j].text = ""
                labels[i][j].font = Font(MAX_FONT_SIZE)

                val box = VBox()
                box.alignment = Pos.CENTER

                val isOnBorder: (Int) -> Int = { x -> if (x == 2 || x == 5) 3 else 1 }

                box.style = "-fx-border-color: black;-fx-border-width: 1px ${isOnBorder(j)}px ${isOnBorder(i)}px 1px;"

                box.style += if ((i.isOdd() and j.isEven()) or (i.isEven() and j.isOdd())) "-fx-background-color: grey"
                else "-fx-background-color: lightgrey"




                box.addEventHandler(MouseEvent.MOUSE_CLICKED) {
                    setCurrent(labels[i][j], box)
                }

                box.children.add(labels[i][j])
                gridPane.add(box, j, i)
            }
        }
        setKeyListener()
    }


    @FXML
    private fun solveBoard() {
        val board = Array(9) { Array(9) { 0 } }
        for (i in 0..8) {
            for (j in 0..8) {
                val value = if (labels[i][j].text == "") 0 else labels[i][j].text.toInt()
                board[i][j] = value
            }
        }
        val solver = SudokuSolver(board)
        var listOfChanges = mutableListOf<Triple<Int, Int, Int>>()

        fun solve(x: Int = 0, y: Int = 0): Boolean {
            if (x > 8) return true
            else if (y > 8) return solve(x + 1, 0)

            if (board[x][y] == 0) {
                for (i in 1..9) {
                    if (!solver.isValid(i, x, y, board)) {
                        continue
                    } else {
                        board[x][y] = i
                        listOfChanges.addLast(Triple(i, x, y))

                        if (solve(x, y + 1)) return true
                        else {
                            board[x][y] = 0
                            listOfChanges.addLast(Triple(0, x, y))
                        }
                    }
                }
                return false
            } else {
                return solve(x, y + 1)
            }
        }

        val timeline = Timeline(KeyFrame(Duration.millis(20.0), {
            val (value, x, y) = listOfChanges.removeFirst()
            updateCurrent(value, x, y)
        }))

        if (solver.isBoardValid(board)) {
            solve()
            timeline.cycleCount = listOfChanges.size
            timeline.play()
        } else {
            alert.contentText = "Invalid starting board"
            alert.show()
        }

    }

}