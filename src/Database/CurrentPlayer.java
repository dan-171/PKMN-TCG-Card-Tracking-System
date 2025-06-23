package Database;

public class AppSession {
	private static Integer currentPlayerId = null;

    public static void setCurrentPlayerId(int playerId) {
        currentPlayerId = playerId;
    }

    public static Integer getCurrentPlayerId() {
        return currentPlayerId;
    }
    
    public static void clearSession() {
        currentPlayerId = null;
    }
}
