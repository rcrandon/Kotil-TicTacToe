package com.example.tictactoe

import kotlin.random.Random

/**
 * This class is responsible for the single-player AI logic in the Tic Tac Toe game.
 */
class SinglePlayerAI {

    /**
     * Determines the AI's move on the board.
     * @param board The current state of the game board.
     * @return Pair of row and column where the AI wants to make a move.
     */
    fun determineMove(board: Array<Array<String>>): Pair<Int, Int> {
        // Check if AI can win in the next move
        for (row in 0..2) {
            for (col in 0..2) {
                if (board[row][col].isEmpty()) {
                    board[row][col] = "O" // AI is always "O"
                    if (isWinningMove(board, "O")) {
                        return Pair(row, col)
                    } else {
                        board[row][col] = "" // Undo the move
                    }
                }
            }
        }

        // Block opponent's winning move
        for (row in 0..2) {
            for (col in 0..2) {
                if (board[row][col].isEmpty()) {
                    board[row][col] = "X" // Opponent is "X"
                    if (isWinningMove(board, "X")) {
                        return Pair(row, col)
                    } else {
                        board[row][col] = "" // Undo the move
                    }
                }
            }
        }

        // If no immediate win or block, choose a random empty cell
        val emptyCells = mutableListOf<Pair<Int, Int>>()
        for (row in 0..2) {
            for (col in 0..2) {
                if (board[row][col].isEmpty()) {
                    emptyCells.add(Pair(row, col))
                }
            }
        }

        return if (emptyCells.isNotEmpty()) {
            emptyCells[Random.nextInt(emptyCells.size)]
        } else {
            Pair(-1, -1) // No available moves
        }
    }

    /**
     * Checks if the current move is a winning move.
     * @param board The current state of the game board.
     * @param player The player symbol to check for a winning move.
     * @return True if the move leads to a win, otherwise false.
     */
    private fun isWinningMove(board: Array<Array<String>>, player: String): Boolean {
        val winningCombinations = arrayOf(
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

        for (combination in winningCombinations) {
            if (combination.all { (row, col) -> board[row][col] == player }) {
                return true
            }
        }
        return false
    }
}
