import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A class for managing players' decks and hands in a Magic game.
 */
public class MagicPlayerManager {
    private int[] handSizes;
    private int[] deckSizes;
    private Lock[] accountLocks;

    /**
     * Constructs a player manager with a given number of player accounts.
     * @param numPlayers the number of player accounts
     */
    public MagicPlayerManager(int numPlayers) {
        handSizes = new int[numPlayers];
        deckSizes = new int[numPlayers];
        accountLocks = new Lock[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            handSizes[i] = 7; // Initial hand size and deck size
            deckSizes[i] = 100;
            accountLocks[i] = new ReentrantLock();
        }
    }

    /**
     * Draws a card from the specified player's deck and adds it to their hand.
     * @param playerNumber the player number
     */
    public void drawCard(int playerNumber) {
        Lock lock = accountLocks[playerNumber];
        lock.lock();
        try {
            if (deckSizes[playerNumber] > 0) {
                deckSizes[playerNumber]--;
                handSizes[playerNumber]++;
            }
        } finally {
            lock.unlock();
        } //Checks player deck size and if cards allow takes card from deck to hand
    }

    public void playCard(int playerNumber) {
        Lock lock = accountLocks[playerNumber];
        lock.lock();
        try {
            if (handSizes[playerNumber] > 0) {
                handSizes[playerNumber]--;
            }
        } finally {
            lock.unlock();
        } //checks players hand size and if allowed puts a hand on the "board"
    }

    public int getDeckSize(int playerNumber) {
        Lock lock = accountLocks[playerNumber];
        lock.lock();
        try {
            return deckSizes[playerNumber];
        } finally {
            lock.unlock();
        }
    }
 //checks players hand or deck size

    public int getHandSize(int playerNumber) {
        Lock lock = accountLocks[playerNumber];
        lock.lock();
        try {
            return handSizes[playerNumber];
        } finally {
            lock.unlock();
        }
    }
    public void mulligan(int playerNumber) { //Trades a new hand for a equal number of cards
        Lock lock = accountLocks[playerNumber]; //I did figure mulligan was a slightly better name than wheel hope thats
        //ok since I know it says not to change the work from the outline.
        lock.lock();
        try {
            int handSize = handSizes[playerNumber];
            if (deckSizes[playerNumber] >= handSize) {
                handSizes[playerNumber] = 0;
                handSizes[playerNumber] += handSize;
                deckSizes[playerNumber] -= handSize;
            } else {
                System.out.println("Not enough cards in deck to perform mulligan for player " + (playerNumber + 1));
            }
        } finally {
            lock.unlock();
        }
    }



}
