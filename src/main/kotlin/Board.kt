import util.Constants.EMPTYSPACE
import java.util.stream.IntStream.range

class Board(size: Int) {

    // A flag stating whether current board is a draw
    var isDraw = false

    // Board and board size
    private var board: Array<Array<String>>
    private var nSize: Int = 0

    init {
        board = Array(size) { Array(size) {"#"} }
        nSize = size
    }

    /**
     *
     * getAllowedMoves will search the board for all open spaces. It will return a list
     * of those open spaces.
     *
     * Parameters: None
     * Return Value: Mutable<List<Int>> - List of rows and columns where open space is located
     *
     **/
    fun getAllowedMoves(): MutableList<List<Int>> {
        val allowedMovement = MutableList(0) { listOf<Int>()}
        for(row in range(0, nSize)) {
            for(col in range(0, nSize)) {
                if(getItemInSpace(row,col) == EMPTYSPACE) {
                    allowedMovement+= listOf(row,col)
                }
            }
        }
        return allowedMovement
    }


    /**
     *
     * isGameEnded will check if there is any winner present after each players move. If no
     * winner is found, it will check whether it is a draw. If not, then it will return false
     * meaning the game is not over
     *
     * Parameters: Int - Row of the player move
     *             Int - Column of the player move
     *             String - Symbol of the current player
     *
     * Return Value: Boolean - True - game is over due to draw or win
     *                         False - game is not over
     *
     **/
    private fun isGameEnded(row: Int, col: Int, playerSymbol: String): Boolean {
        if(checkWinnerHorizontal(row, playerSymbol) ||
                checkWinnerVertical(col, playerSymbol) ||
                checkWinnerUpDiagonal(playerSymbol) ||
                checkWinnerDownDiagonal(playerSymbol)
        ){
            return true
        }
        else if (getAllowedMoves().isEmpty()) {
            isDraw = true
            return true
        }
        return false
    }

    /**
     *
     * commitMove will actually make the change to the board and then call isGameEnded, which
     * will tell us if the game is finished or not
     *
     * Parameters: Int - Row of the player move
     *             Int - Column of the player move
     *             String - Symbol of the current player
     *
     * Return Value: Boolean - True - game is over due to draw or win
     *                         False - game is not over
     *
     **/
    fun commitMove(row: Int, col: Int, playerSymbol: String): Boolean {
        board[row][col] = playerSymbol
        return isGameEnded(row, col, playerSymbol)
    }

    private fun checkWinnerHorizontal(row: Int, playerSymbol: String): Boolean {
        for(i in range(0, nSize)) {
            if(board[row][i] != playerSymbol) {
                return false
            }
        }
        return true
    }

    private fun checkWinnerVertical(col: Int, playerSymbol: String): Boolean {
        for(i in range(0, nSize)) {
            if(board[i][col] != playerSymbol) {
                return false
            }
        }
        return true
    }

    private fun checkWinnerDownDiagonal(playerSymbol: String): Boolean {
        for(i in range(0, nSize)) {
            if(board[i][i] != playerSymbol) {
                return false
            }
        }
        return true
    }

    private fun checkWinnerUpDiagonal(playerSymbol: String): Boolean {
        for(i in range(0, nSize)) {
            if(board[(nSize - 1 - i)][i] != playerSymbol){
                return false
            }
        }
        return true
    }

    private fun getItemInSpace(row: Int, col: Int): String {
        return board[row][col]
    }

    override fun toString(): String {
        var stringBoard = ""
        board.forEach { it ->
            it.forEach {
                stringBoard+= it
            }
            stringBoard += "\n"
        }
        return stringBoard
    }
}