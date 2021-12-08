/**
 *
 * Jose Lopez
 * 12/7/2021
 *
 * Simple NxN tic tac toe game. The program will make the 2 players choose their spots by random
 * and will decide a winner based on matching row, column, or diagonal.
 *
 **/

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")
    val gameController = GameController(args[0].toInt())
    gameController.startGame()
}