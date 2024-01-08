package com.example.tictactoe

/**
 * This class is responsible for tracking the score of the Tic Tac Toe game.
 */
class ScoreTracker {

    private var playerXScore: Int = 0
    private var playerOScore: Int = 0
    private var draws: Int = 0

    /**
     * Increments the score for Player X.
     */
    fun incrementPlayerXScore() {
        playerXScore++
    }

    /**
     * Increments the score for Player O.
     */
    fun incrementPlayerOScore() {
        playerOScore++
    }

    /**
     * Increments the number of draws.
     */
    fun incrementDraws() {
        draws++
    }

    /**
     * Resets all scores to 0.
     */
    fun resetScores() {
        playerXScore = 0
        playerOScore = 0
        draws = 0
    }

    /**
     * Retrieves the current score for Player X.
     * @return The score for Player X.
     */
    fun getPlayerXScore(): Int {
        return playerXScore
    }

    /**
     * Retrieves the current score for Player O.
     * @return The score for Player O.
     */
    fun getPlayerOScore(): Int {
        return playerOScore
    }

    /**
     * Retrieves the number of draws.
     * @return The number of draws.
     */
    fun getDraws(): Int {
        return draws
    }
}
