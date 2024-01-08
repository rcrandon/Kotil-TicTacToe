package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameBoard: Array<Array<Button>>
    private lateinit var textViewStatus: TextView
    private lateinit var gameLogic: GameLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewStatus = findViewById(R.id.textViewStatus)
        gameLogic = GameLogic()

        // Initialize the game board buttons
        gameBoard = Array(3) { row ->
            Array(3) { col ->
                val buttonId = resources.getIdentifier("button$row$col", "id", packageName)
                findViewById<Button>(buttonId).apply {
                    setOnClickListener {
                        onCellClicked(this, row, col)
                    }
                }
            }
        }

        val buttonReset: Button = findViewById(R.id.buttonReset)
        buttonReset.setOnClickListener {
            resetGame()
        }
    }

    private fun onCellClicked(button: Button, row: Int, col: Int) {
        if (!gameLogic.isCellOccupied(row, col)) {
            gameLogic.makeMove(row, col)
            button.text = gameLogic.currentPlayerSymbol()
            button.isEnabled = false

            when {
                gameLogic.hasWon() -> textViewStatus.text = getString(R.string.winner, gameLogic.currentPlayerSymbol())
                gameLogic.isDraw() -> textViewStatus.text = getString(R.string.draw)
                else -> {
                    gameLogic.switchPlayer()
                    textViewStatus.text = getString(R.string.turn, gameLogic.currentPlayerSymbol())
                }
            }
        }
    }

    private fun resetGame() {
        for (row in gameBoard) {
            for (button in row) {
                button.text = ""
                button.isEnabled = true
            }
        }
        gameLogic.reset()
        textViewStatus.text = getString(R.string.turn, gameLogic.currentPlayerSymbol())
    }
}
