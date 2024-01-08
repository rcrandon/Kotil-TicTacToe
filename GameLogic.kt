package com.example.tictactoe

class GameLogic {
    private var board: Array<Array<String>> = Array(3) { Array(3) { "" } }
    private var currentPlayer: String = "X"
    private val winningCombinations = arrayOf(
        // Rows
        arrayOf(0 to 0, 0 to 1, 0 to 2),
        arrayOf(1 to 0, 1 to 1, 1 to 2),
        arrayOf(2 to 0, 2 to 1, 2 to 2),
        // Columns
        arrayOf(0 to 0, 1 to 0, 2 to 0),
        arrayOf(0 to 1, 1 to 1, 2 to 1),
        arrayOf(0 to 2, 1 to 2, 2 to 2),
        // Diagonals
        arrayOf(0 to 0, 1 to 1, 2 to 2),
        arrayOf(0 to 2, 1 to 1, 2 to 0)
    )

    fun isCellOccupied(row: Int, col: Int): Boolean {
        return board[row][col].isNotEmpty()
    }

    fun makeMove(row: Int, col: Int) {
        if (row in 0..2 && col in 0..2) {
            board[row][col] = currentPlayer
        }
    }

    fun currentPlayerSymbol(): String {
        return currentPlayer
    }

    fun hasWon(): Boolean {
        for (combination in winningCombinations) {
            if (combination.all { (row, col) -> board[row][col] == currentPlayer }) {
                return true
            }
        }
        return false
    }

    fun isDraw(): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell.isEmpty()) {
                    return false
                }
            }
        }
        return true
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    fun reset() {
        for (row in 0 until board.size) {
            for (col in 0 until board[row].size) {
                board[row][col] = ""
            }
        }
        currentPlayer = "X"
    }
}
