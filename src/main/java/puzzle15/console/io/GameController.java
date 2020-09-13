package puzzle15.console.io;

public interface GameController {
    GameEvent getNextEvent();
    void collectUserInput();
}
