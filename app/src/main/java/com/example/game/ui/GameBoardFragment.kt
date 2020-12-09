package com.example.game.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.game.R
import com.example.game.model.Board
import com.example.game.model.Cell
import kotlinx.android.synthetic.main.fragment_board.*


class GameBoardFragment : Fragment() {
    private lateinit var gameViewModel: GameViewModel
    private var n = 3
    private var boardCells = Array(n) { arrayOfNulls<TextView>(n) }
    var isPlayer = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        if(savedInstanceState != null){
            n = savedInstanceState.getInt(SIZE)
        }else{
            arguments?.let {
              n  = it.getInt(SIZE)
            }
        }
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        boardCells = Array(n) { arrayOfNulls<TextView>(n) }
        gameViewModel.setBoard(n)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadBoard()
        winObserve()

        button_restart.setOnClickListener {
            gameViewModel.resetValue()
            boardToUi()
        }
    }

    private fun winObserve() {
        gameViewModel.win.observe(this, Observer {
            when(it){
                Board.PLAYER1 -> {
                    text_view_result.text = getString(R.string.player1)
                }
                Board.PLAYER2 -> {
                    text_view_result.text = getString(R.string.player2)
                }
                Board.DRAW -> {
                    text_view_result.text = "เสมอ"
                }
                else -> text_view_result.text = ""
            }
        })
    }

    private fun loadBoard() {
        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = TextView(requireContext()).apply {
                    gravity = Gravity.CENTER
                    textSize = 20f
                    setTextColor(Color.BLACK)
                    freezesText = true
                }
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 150
                    height = 150
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
                boardCells[i][j]?.setOnClickListener{
                    cellClickListener(i,j)

                }
                layout_board.addView(boardCells[i][j])

            }
        }
    }

    private fun boardToUi() {
        val board = gameViewModel.getBoard()
        for (i in board.indices) {
            for (j in board.indices) {
                when (board[i][j]) {
                    Board.PLAYER1 -> {
                        boardCells[i][j]?.text = "O"
                        boardCells[i][j]?.isEnabled = false
                    }
                    Board.PLAYER2 -> {
                        boardCells[i][j]?.text = "X"
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.text = ""
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    private fun cellClickListener(i: Int, j: Int) {
        val cell = Cell(i, j)
        isPlayer = if(isPlayer){
                gameViewModel.setCell(cell, Board.PLAYER1)
                false
            }else{
                gameViewModel.setCell(cell, Board.PLAYER2)
                true
            }
           boardToUi()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SIZE,n)

    }

    companion object {
        const val SIZE = "size"
        const val TAG_GAME_FRAGMENT = "tagGameFragment"
        fun newInstant(size: Int) = GameBoardFragment().apply {
            arguments = Bundle().apply {
                putInt(SIZE, size)

            }

        }
    }
}