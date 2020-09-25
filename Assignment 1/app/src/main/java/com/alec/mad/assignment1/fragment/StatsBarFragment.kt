package com.alec.mad.assignment1.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alec.mad.assignment1.GameStateSingleton
import com.alec.mad.assignment1.R
import com.alec.mad.assignment1.gameState
import com.alec.mad.assignment1.state.GameState.PlayerCondition
import com.alec.mad.assignment1.state.GameStateObserver

@SuppressLint("SetTextI18n")
class StatsBarFragment : Fragment(), GameStateObserver {

    private lateinit var statusReadout: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats_bar, container, false)

        this.statusReadout = view.findViewById(R.id.statsBarPointsReadout) as TextView

        this.gameState.observers.add(this)
        this.onUpdatePlayerPoints(this.gameState.playerPoints)
        this.onUpdatePlayerCondition(this.gameState.playerCondition)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        this.gameState.observers.remove(this)
    }

    override fun onUpdatePlayerPoints(playerPoints: Int) {
        if (this.gameState.playerCondition == PlayerCondition.PLAYING) {
            val remaining = this.gameState.targetPoints - playerPoints
            val playerCondition = this.gameState.playerCondition

            this.statusReadout.text = "You have $playerPoints points, $remaining more to win!"
        }
    }

    override fun onUpdatePlayerCondition(playerCondition: PlayerCondition) {
        //TODO DEBUG
        println("Changed player cond to $playerCondition")
        this.statusReadout.text = when (playerCondition) {
            PlayerCondition.LOST -> "You are dead. No big surprise"
            PlayerCondition.PLAYING -> this.statusReadout.text // Handled by onUpdatePlayerPoints
            PlayerCondition.WON -> "You won!"
        }
    }
}