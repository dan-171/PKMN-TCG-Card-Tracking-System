package Database;

public class CurrentPlayer extends Player {
    private static CurrentPlayer instance = null;

    public CurrentPlayer(int playerID) {
        super(playerID);
    }

    // Set the current logged-in player
    public static void setCurrentPlayer(Player player) {
        if (player != null) {
            CurrentPlayer cp = new CurrentPlayer(player.getPlayerID());
            cp.playerName = player.getPlayerName();
            cp.cardQuantity = player.getCardQuantity();
            cp.registerDate = player.getRegistrationDate();
            cp.password = player.getPassword();
            instance = cp;
        }
    }

    public static CurrentPlayer getCurrentPlayer() {
        return instance;
    }

    public static void clearSession() {
        instance = null;
    }

    public static boolean isLoggedIn() {
        return instance != null;
    }
}
