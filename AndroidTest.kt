package com.example.tictactoe

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TicTacToeInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tictactoe", appContext.packageName)
    }

    @Test
    fun gameBoardButtonsExist() {
        val activity = activityRule.activity
        val resources = activity.resources
        for (row in 0..2) {
            for (col in 0..2) {
                val buttonId = resources.getIdentifier("button$row$col", "id", activity.packageName)
                val button = activity.findViewById<Button>(buttonId)
                assertNotNull("Button at ($row, $col) should not be null", button)
            }
        }
    }

    @Test
    fun resetButtonResetsGame() {
        val activity = activityRule.activity
        val buttonReset = activity.findViewById<Button>(R.id.buttonReset)
        val gameLogic = GameLogic()

        // Simulate a few moves
        gameLogic.makeMove(0, 0)
        gameLogic.makeMove(1, 1)
        gameLogic.makeMove(2, 2)

        // Click the reset button
        buttonReset.performClick()

        // Check if the board is reset
        for (row in 0..2) {
            for (col in 0..2) {
                assertFalse("Cell at ($row, $col) should be empty after reset", gameLogic.isCellOccupied(row, col))
            }
        }
    }

    @Test
    fun textViewStatusUpdatesCorrectly() {
        val activity = activityRule.activity
        val textViewStatus = activity.findViewById<TextView>(R.id.textViewStatus)
        val gameLogic = GameLogic()

        // Simulate a player X win
        gameLogic.makeMove(0, 0)
        gameLogic.makeMove(0, 1)
        gameLogic.makeMove(0, 2)

        // Check if the status TextView reflects the win
        val expectedWinText = activity.getString(R.string.winner, gameLogic.currentPlayerSymbol())
        assertEquals("TextView should display winner message", expectedWinText, textViewStatus.text)
    }
}
