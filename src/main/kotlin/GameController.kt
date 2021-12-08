import kotlin.random.Random

class GameController(size: Int) {

    // Represents the symbols for each player
    private val player1Symbol = "X"
    private val player2Symbol = "O"

    // Represents the current game board
    private val game: Board = Board(size)


    /**
     *
     * startGame will start the main logic of the game. It will call the necessary
     * functions needed to make players move happen as well as check if the game is
     * finished or not, whether it be a winner or a draw.
     *
     * Parameters: None
     * Return Value: None
     *
     **/
    fun startGame() {
        var currentPlayer = 0
        while(true) {
            currentPlayer++
            printCurrentTurn(currentPlayer)
            if(commitPlayerMove(currentPlayer)) {
                printBoard()
                printWinner(currentPlayer)
                break
            } else{
                printBoard()
            }
        }
    }

    /**
     *
     * commitPlayerMove will obtain a random column and row from open spaces
     * and will call our board function that will actually commit the move into
     * the game  board
     *
     * Parameters: Int: Will contain the current player making the move
     * Return Value: True - Game has ended
     *               False - Game still in progress
     *
     **/

    private fun commitPlayerMove(currentPlayer: Int): Boolean {
        val isGameEnded: Boolean
        val randomIndex = Random.nextInt(game.getAllowedMoves().size)
        val randomRowCol = game.getAllowedMoves()[randomIndex]
        isGameEnded = if(currentPlayer % 2 == 0){
            game.commitMove(randomRowCol[0], randomRowCol[1], player2Symbol)
        } else {
            game.commitMove(randomRowCol[0], randomRowCol[1], player1Symbol)
        }
        return isGameEnded
    }

    private fun printBoard() {
        print(game)
        print("----------------\n")
    }

    private fun getCurrentPlayer(currentPlayer: Int): Int {
        if(currentPlayer % 2 == 0) {
            return 2
        }
        return 1
    }

    private fun printCurrentTurn(currentPlayer: Int) {
        println("Player ${getCurrentPlayer(currentPlayer)} Turn:")
    }

    private fun printWinner(currentPlayer: Int) {
        if(game.isDraw) {
            println("Game is a draw")
        }
        else{
            println("Player ${getCurrentPlayer(currentPlayer)} Wins The Game" )
        }
    }
}