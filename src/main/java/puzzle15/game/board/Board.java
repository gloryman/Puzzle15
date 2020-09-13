package puzzle15.game.board;

public interface Board {
    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();

    int getWidth();
    int getHeight();

    int[][] getCells();
}
