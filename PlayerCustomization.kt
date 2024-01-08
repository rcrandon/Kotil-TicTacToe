package com.example.tictactoe

import android.content.Context
import android.content.SharedPreferences

class PlayerCustomization(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("PlayerCustomization", Context.MODE_PRIVATE)

    fun getPlayerSymbol(player: String): String {
        return prefs.getString(player, getDefaultSymbol(player)) ?: getDefaultSymbol(player)
    }

    fun setPlayerSymbol(player: String, symbol: String) {
        prefs.edit().putString(player, symbol).apply()
    }

    private fun getDefaultSymbol(player: String): String {
        return if (player == "PlayerX") "X" else "O"
    }

    fun getPlayerName(player: String): String {
        return prefs.getString("${player}Name", getDefaultName(player)) ?: getDefaultName(player)
    }

    fun setPlayerName(player: String, name: String) {
        prefs.edit().putString("${player}Name", name).apply()
    }

    private fun getDefaultName(player: String): String {
        return if (player == "PlayerX") "Player X" else "Player O"
    }
}
