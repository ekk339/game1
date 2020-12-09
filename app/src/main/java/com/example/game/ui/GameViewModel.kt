package com.example.game.ui

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.game.R
import com.example.game.model.Board
import com.example.game.model.Cell

class GameViewModel: ViewModel() {
    private lateinit var board:  Board
    private val _win = MutableLiveData<String>()
    val win: LiveData<String>
        get() = _win

    fun setBoard(size: Int){
        board = Board(size)
    }

    fun setCell(cell: Cell, player: String){
        board.placeMove(cell,player)
        _win.postValue(board.getWin())
    }

    fun getBoard() = board.board

    fun resetValue() {
        board.resetValue()
        _win.postValue("")
    }


}