package snake_v2;

import java.io.FileNotFoundException;

public class ShowSnake {
    public static void main(String[] args) throws FileNotFoundException {
        new SnakeViewControl(new SnakeModel(), 30); // HARD: 30, EASY: 70
    }
}
