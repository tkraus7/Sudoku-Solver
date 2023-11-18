package com.example.kotlinjavafx

import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font

class SudokuSolverController {
    private val GRID_BOX_SIZE = 600.0
    private val MAX_FONT_SIZE = 30.0

    @FXML private lateinit var bp: BorderPane

    @FXML private lateinit var gridPane: GridPane

    private var labels = List(9) { List(9) { Label() } }

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
        currentBox!!.style = "-fx-border-color: red; -fx-border-width: 3px"
    }

    @FXML private fun createGrid() {
        gridPane.isVisible = true
        gridPane.minHeight = GRID_BOX_SIZE
        gridPane.minWidth = GRID_BOX_SIZE


        for (i in 0..8) {
            for (j in 0..8) {
                labels[i][j].text = "${i * 9 + j}"
                labels[i][j].font = Font(MAX_FONT_SIZE)

                val box = VBox()
                box.alignment = Pos.CENTER

                val isOnBorder: (Int) -> Int = {x -> if (x == 2 || x == 5) 3 else 1}

                box.style = "-fx-border-color: black;-fx-border-width: 1px ${isOnBorder(j)}px ${isOnBorder(i)}px 1px"


                box.addEventHandler(MouseEvent.MOUSE_CLICKED) {
                    setCurrent(labels[i][j], box)
                }

                box.children.add(labels[i][j])
                gridPane.add(box, j, i)
            }
        }



        setKeyListener()

    }

}