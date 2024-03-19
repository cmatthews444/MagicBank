public class MagicGame {
    private MagicPlayerManager playerManager;

    /**
     * "manager" function that sees over the game and sets it up for "playing"
     */
    public MagicGame(int numPlayers) {
        this.playerManager = new MagicPlayerManager(numPlayers);
    } //sets up game with requested number of players

    public void drawCard(int playerNumber) {
        playerManager.drawCard(playerNumber);
    } //allowed said player to draw card

    public void playCard(int playerNumber) {
        playerManager.playCard(playerNumber);
    } //takes card from deck to hand

    public int getDeckSize(int playerNumber) {
        return playerManager.getDeckSize(playerNumber);
    }
//Checks the players deck or hand size
    public int getHandSize(int playerNumber) {
        return playerManager.getHandSize(playerNumber);
    }
    public void mulligan(int playerNumber) {
        playerManager.mulligan(playerNumber); //Trades hand for new hand
    }
}
