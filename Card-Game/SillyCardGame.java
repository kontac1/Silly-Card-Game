package ckonta_P3;
import java.util.Scanner;

/**
 * the SillyGame function is use to collect player name and output game
 * progress stats to players
 *
 * @author cheick
 * @version 1.0
 */
public class SillyCardGame {
    //call GameModel class
    static GameModel game = new GameModel();

    /**
     * the main function call other function and display output on user screen
     *
     * @param args contain command line argument
     */
    public static void main(String[] args) {

        //declare variable to repeat program
        String line;
        //to store character of user input
        char choice;

        //create a scanner object
        Scanner input = new Scanner(System.in);

        //new line
        System.out.println("\n****** Welcome to Silly little card game! ******");
        //loop to repeat the program
        do {

            //new line
            System.out.println();

            //call get player name function
            getPlayer();
            //new line
            System.out.println();
            //call play game function
            playGame();
            //new line
            System.out.println();

            //ask user if they will like to repeat the program
            System.out.print("\nPlay again? (Y/N): ");
            line = input.next();
            choice = line.charAt(0);


        } while (choice == 'Y' || choice == 'y');

        //thank user for playing
        System.out.println("\nThank you for playing");
        //close scanner
        input.close();

    }

    /**
     * function call player names
     */
    public static void getPlayer() {


        //create a scanner object
        Scanner input = new Scanner(System.in);

        //collect player names
        System.out.print("Please enter player one name: ");
        //store player one name
        String player1 = input.next();
        //collect player two name
        System.out.print("Please enter player two name: ");
        //store player two name
        String player2 = input.next();
        //call playerName set function from GameModel class to pass player names
        game.playerNameSet(player1, player2);

    }

    /**
     * function play the game and output results
     */
    public static void playGame() {
        //declaring variable to store current player
        String currentPlayer = game.currentplayer;
        //while game is not over
        while (!game.GameOver()) {

            //promt user whose turn it is
            System.out.println(currentPlayer + ", It is your turn");
            //check if current player is player
            if (game.currentplayer.equals(game.player1)) {
                //display player one card queue
                System.out.println(game.player1Queue);
                //display the discard pile value
                System.out.println("Discard pile card: " + game.discardCard.peek());
                //display player one card value
                System.out.println("Your current card: " + game.player1Queue.peek());
                //display result
                displayResult(game.gameLogic(game.player1Queue));
                //check if player one win the game
                if (game.player1Queue.empty()) {
                    //if so print the following
                    System.out.println("You have won the game");
                }
                //print new line
                System.out.println();
                //else
            } else {
                //display player two card on hand
                System.out.println(game.player2Queue);
                //display the discard pile value
                System.out.println("Discard pile card: " + game.discardCard.peek());
                //display player two hand value
                System.out.println("Your current card: " + game.player2Queue.peek());
                //display result
                displayResult(game.gameLogic(game.player2Queue));
                //check if player two win the game
                if (game.player2Queue.empty()) {
                    //if so print the following
                    System.out.println("You have won the game");
                }
                //print new line
                System.out.println();
            }
            //switch players
            currentPlayer = game.currentplayer;
        }
        //new line
        System.out.println();
        //inform player that game has ended
        System.out.println("The game has finished.");

    }

    /**
     * display result use a switch stach to determine results
     *
     * @param res tke result as parameter
     */
    public static void displayResult(String res) {
        //switch results
        switch (res) {
            //case result is equal to LOWER
            case "LOWER" -> {
                //print result to player
                System.out.println("Your card is LOWER, pick up 2 cards.");

            }
            //case result is equal to EQUAL
            case "EQUAL" -> {
                //print result to player
                System.out.println("Your card is EQUAL, pick up 1 cards.");

            }
            //case result is equal to HIGHER
            case "HIGHER" -> {
                //print result to player
                System.out.println("Your card is HIGHER, turn is over.");

            }
        }

    }
}
