package moe.nova.playground.stream;

public sealed interface Game permits CompletedGame, InvalidGame {

    Game INVALID_GAME = new InvalidGame();

    static Game completedGame(Player player1, Player player2, GameResult result) {
        return new CompletedGame(player1, player2, result);
    }

    static boolean isCompleted(Game game) {
        return game instanceof CompletedGame;
    }
}


record CompletedGame(Player player1, Player player2, GameResult result) implements Game {
}

record InvalidGame() implements Game {

    @Override
    public String toString() {
        return "INVALID GAME";
    }
}
