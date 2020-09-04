package ckonta_P3;

import java.util.ArrayList;
import java.util.Random;

/**
 * the GameModel class is use to program game logic and rules
 *
 * @author cheick
 * @version 1.0
 */
public class GameModel {
    //declaring queue to hold player cards
    Queue<Integer> player1Queue;
    //declaring queue to hold player cards
    Queue<Integer> player2Queue;
    //declaring stack to hold dealer cards
    Stack<Integer> dealStack;
    //declaring stack to hold cards
    ArrayList<Integer> cards;
    //variables to hold player name
    String player1;
    //variables to hold player name
    String player2;
    //declaring stack to hold discard cards
    Stack<Integer> discardCard;
    //declaring queue to hold players
    Queue<String> players;
    String currentplayer;

    /**
     * Game model constructor is use to assign variables
     */
    public GameModel() {
        //assigning card to arraylist
        cards = new ArrayList<Integer>();
        //assigning player one queue
        player1Queue = new Queue<Integer>();
        //assigning player two queue
        player2Queue = new Queue<Integer>();
        //assigning dealer stack
        dealStack = new Stack<Integer>();
        //assigning discard stack
        discardCard = new Stack<Integer>();
        //calling set card function
        setCard();


    }

    /**
     * function to set player names
     *
     * @param player1 name
     * @param player2 name
     */
    public void playerNameSet(String player1, String player2) {
        //assigning player one
        this.player1 = player1;
        //assigning player 2
        this.player2 = player2;
        //setting current player
        currentplayer = player1;


    }

    /**
     * the function will switch between player during the game
     *
     * @param current take current player as parameter
     */
    public void switchPlayer(String current) {
        //if current player equal player one
        if (current.equals(this.player1)) {
            //set current player to player two
            currentplayer = player2;
        } else {
            //else set current player to player one
            currentplayer = player1;
        }
    }

    /**
     * the function inout 52 card into the card deck between 1 to 13
     */
    public void setCard() {
        //loop to run the inner loop four times
        for (int j = 1; j <= 4; j++) {
            //loop to add number into the deck
            for (int i = 1; i <= 13; i++) {
                //add number into card deck
                cards.add(i);
            }
        }
        //pass card deck to shuffle funciton
        shuffleDeck(cards);
        //call deal card function
        dealCard();

    }

    /**
     * function use to shuffle deck before distribute to players
     *
     * @param cards stack
     */
    private void shuffleDeck(ArrayList<Integer> cards) {
        //random number generator
        Random rand = new Random();
        //loop through elements
        for (int i = cards.size(); i > 1; i--) {
            //assigning rand num to variable j
            int j = rand.nextInt(i);
            //temporary hold for values
            int temp = cards.get(i - 1);
            //set card element
            cards.set(i - 1, cards.get(j));
            //pass j and temp value to card
            cards.set(j, temp);
        }

    }

    /**
     * function to distribute cards among players
     */
    public void dealCard() {
        //player start with seven card
        final int PLAYERHANDSIZE = 7;
        //loop to distribute shuffle card into dealStacke
        for (int i = 0; i < cards.size(); i++) {
            //add cards into deal stack
            dealStack.push(cards.get(i));
        }
        //loop to distribute card between players
        for (int i = 0; i < PLAYERHANDSIZE; i++) {
            //add seven cards into player one queue
            player1Queue.enqueue(dealStack.pop());
            //add seven cards into player two queue
            player2Queue.enqueue(dealStack.pop());
        }
        //add card to discard card from deal stack
        discardCard.push(dealStack.pop());
    }

    /**
     * function to check if game is over
     *
     * @return true or false
     */
    public boolean GameOver() {
        //return true if players queue is empty
        return (player1Queue.empty() || player2Queue.empty());
    }

    /**
     * check if deal stack is empty
     */
    public void dealStackEmpty() {
        //store the top number in the discardcard
        int temp = discardCard.pop();
        //check if deal stack is empty
        if (!dealStack.isEmpty()) {
            //loop to fill deal stack from discard card stack
            while (!discardCard.isEmpty()) {
                dealStack.push(discardCard.pop());
            }
            //push remain the top number inchange
            discardCard.push(temp);
        }
    }

    /**
     * function to set game rules/logic
     */
    public String gameLogic(Queue<Integer> crrentPlayer) {
        //store varibale to penalize players
        final int LOWPENALTY = 2;
        //declaring varibale to track results
        String results;
        //store the number dequeue from player queue
        int temp = crrentPlayer.dequeue();
        //store the discard card value
        int discardValue = discardCard.peek();
        //check if player number is less than discardvalue
        if (temp < discardValue) {
            //assign result to lower
            results = "LOWER";
            //loop to add two card to player queue from deal stack
            for (int i = 0; i < LOWPENALTY; i++) {
                //check if deal stack is empty
                dealStackEmpty();
                //enqueue from current player
                crrentPlayer.enqueue(dealStack.pop());
            }
        //check if player card is equal to discard value
        } else if (temp == discardValue) {
            //assign result to equal
            results = "EQUAL";
            //check if deal stack is empty
            dealStackEmpty();
            //enqueue from current player
            crrentPlayer.enqueue(dealStack.pop());
        //else if player card id higher than discard value
        } else {
            //assign result to higer
            results = "HIGHER";

        }
        //add player card to discard stack
        discardCard.push(temp);
        //switch player
        switchPlayer(currentplayer);
        //return result
        return results;
    }


}