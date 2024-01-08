package com.example.tictactoe

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UnitTest {

    private lateinit var gameLogic: GameLogic
    private lateinit var scoreTracker: ScoreTracker

    @Before
    fun setUp() {
        gameLogic = GameLogic()
        scoreTracker = ScoreTracker()
    }

    @Test
    fun testInitialBoardIsEmpty() {
        for (row in 0..2) {
            for (col in 0..2) {
                assertFalse(gameLogic.isCellOccupied(row, col))
            }
        }
    }

    @Test
    fun testMakeMove() {
        gameLogic.makeMove(0, 0)
        assertTrue(gameLogic.isCellOccupied(0, 0))
        assertEquals("X", gameLogic.currentPlayerSymbol())
    }

    @Test
    fun testPlayerSwitchAfterMove() {
        gameLogic.makeMove(0, 0)
        gameLogic.switchPlayer()
        assertEquals("O", gameLogic.currentPlayerSymbol())
    }

    @Test
    fun testWinningCondition() {
        gameLogic.makeMove(0, 0) // X
        gameLogic.makeMove(1, 0) // O
        gameLogic.makeMove(0, 1) // X
        gameLogic.makeMove(1, 1) // O
        gameLogic.makeMove(0, 2) // X wins
        assertTrue(gameLogic.hasWon())
    }

    @Test
    fun testDrawCondition() {
        gameLogic.makeMove(0, 0) // X
        gameLogic.makeMove(0, 1) // O
        gameLogic.makeMove(0, 2) // X
        gameLogic.makeMove(1, 1) // O
        gameLogic.makeMove(1, 0) // X
        gameLogic.makeMove(1, 2) // O
        gameLogic.makeMove(2, 1) // X
        gameLogic.makeMove(2, 0) // O
        gameLogic.makeMove(2, 2) // X
        assertTrue(gameLogic.isDraw())
    }

    @Test
    fun testScoreTracking() {
        scoreTracker.incrementPlayerXScore()
        scoreTracker.incrementPlayerOScore()
        scoreTracker.incrementDraws()
        scoreTracker.resetScores()
        assertEquals(0, scoreTracker.getPlayerXScore())
        assertEquals(0, scoreTracker.getPlayerOScore())
        assertEquals(0, scoreTracker.getDraws())
    }
}
