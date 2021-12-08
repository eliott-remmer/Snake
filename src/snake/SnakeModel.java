package snake;

import java.util.ArrayList;

public class SnakeModel {

    int row = 40;
    int col = 70;
    int length = 10;
    int treats = 0;
    int rowDirection = 0;
    int colDirection = 1;
    int nextRow = 20;
    int nextCol = 0;
    boolean eternalMode = true;

    ArrayList<Coordinates> snakeArray = new ArrayList<>();
    Coordinates headPosition = new Coordinates(nextRow, nextCol);
    Coordinates buttPosition = new Coordinates(0, 0);

    public void changeDirection(int d) {
        switch (d) {
            case 0: //right
                rowDirection = 0;
                colDirection = 1;
                break;
            case 1: //left
                rowDirection = 0;
                colDirection = -1;
                break;
            case 2: //down
                rowDirection = 1;
                colDirection = 0;
                break;
            case 3: //up
                rowDirection = -1;
                colDirection = 0;
                break;
        }
    }
    public void updatePosition() {
        nextRow += rowDirection;
        nextCol += colDirection;
        if (eternalMode) {
            if (nextRow >= row)
                nextRow = 0;
            if (nextRow < 0)
                nextRow = row - 1;
            if (nextCol >= col)
                nextCol = 0;
            if (nextCol < 0)
                nextCol = col - 1;
        }
        headPosition = new Coordinates(nextRow, nextCol);
    }
    public void updateLength() {
        if (snakeArray.size() > length) {
            buttPosition = snakeArray.remove(0);
        }
    }
    public void resetSnake() {
        length = 10;
        treats = 0;
        rowDirection = 0;
        colDirection = 1;
        nextRow = 20;
        nextCol = 0;

        snakeArray = new ArrayList<>();
        headPosition = new Coordinates(nextRow, nextCol);
        buttPosition = new Coordinates(0, 0);
    }
}
