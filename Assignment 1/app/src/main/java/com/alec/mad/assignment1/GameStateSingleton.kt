package com.alec.mad.assignment1

import androidx.fragment.app.Fragment
import com.alec.mad.assignment1.state.GameState

/**
 * Simply holds a static reference to the current game state for simplicity.
 */
object GameStateSingleton {
    /**
     * The current game state.
     */
    var state = GameState()
        private set

    /**
     * Reset the game.
     */
    fun reset() {
        this.state = GameState()
    }
}

/**
 * Allows all fragments to access GameStateSingleton.state with a simple this.gameState.
 */
val Fragment.gameState get() = GameStateSingleton.state