package puzzle15.game;

public class GameConst {
    public static int BOARD_CURSOR_VALUE = 0;
    public static int DEFAULT_BOARD_XY_LENGTH = 4;
    public static String PUZZLE_SOLVED_STRING = "Congratulations with solving puzzle";
    public static String SYSTEM_UNKNOWN_ERROR = "Unknown Error";

    public enum GameState {
        IN_PROGRESS,
        WON
    }
}