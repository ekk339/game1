package com.example.game.model


class Board(
    private val size: Int
) {
    companion object {
        const val PLAYER1 = "O"
        const val PLAYER2 = "X"
        const val DRAW = "DRAW"
    }

    var board = Array(size) { arrayOfNulls<String>(size) }
    private var stop = false
    private val row = size
    private val col = size
    private var count = 0


    fun placeMove(cell: Cell, player: String) {
        if(!stop){
            board[cell.i][cell.j] = player
        }
        upCount()
    }

    fun resetValue(){
        board = Array(size) { arrayOfNulls(size) }
        count = 0
    }

    fun getWin(): String? {
        var Cb1 = 0
        var Cb2 = 0
        var countRC: Int = row * col
            for (i in 0 until row) {
                var C_C = 0
                var C_R = 0
                if (board[0][0] === board[i][i] && board[0][0] !== null) {
                    Cb1 += 1;
                    if (Cb1 == row) {
                        stop = true
                        return board[0][0].toString()
                    }
                }
                if (board[0][row - 1] === board[i][row - 1 - i] && board[0][row - 1] !== null) {
                    Cb2 += 1;
                    if (Cb2 == row) {
                        stop = true
                        return  board[0][row - 1].toString()
                    }
                }
                for (j in col - 1 downTo 1) {
                    if (board[i][0] === board[i][j] && board[i][0] !== null) {
                        C_C += 1;
                        if (C_C == row - 1) {
                            stop = true
                            return board[i][0].toString()
                        }
                    }
                    if (board[0][i] === board[j][i] && board[0][i] !== null) {
                        C_R += 1
                        if (C_R == row - 1) {
                            stop = true
                            return  board[0][i].toString()

                        }
                    }
                }
            }

        if (count === countRC && !stop) {
            stop = true
            return DRAW
        }
        return null
    }

    private fun upCount() {
        count += 1
    }

}