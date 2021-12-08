package snake_v1;

import java.util.ArrayList;

public class SnakeModel {

    ArrayList<Integer> headRowPrev = new ArrayList<>();
    ArrayList<Integer> headColPrev = new ArrayList<>();
    int length = 20;
    int headRow = 20;
    int headCol = 0;
    int rowToGo = 0;
    int colToGo = 0;
    int rowDirection = 0;
    int colDirection = 1;

    ArrayList<Coordinates> snakeArray = new ArrayList<>();
    int nextRow = 20;
    int nextCol = 0;
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
        //headRowPrev.add(headRow);
        //headColPrev.add(headCol);
        //headCol = headCol + colDirection;
        //headRow = headRow + rowDirection;

        nextRow += rowDirection;
        nextCol += colDirection;

        headPosition = new Coordinates(nextRow, nextCol);
        //snakeArray.add(headPosition);


        //headPosition.row = headPosition.row + rowDirection;
        //headPosition.col = headPosition.col + colDirection;


    }
    public void updateLength() {
        if (snakeArray.size() > length) {
            //rowToGo = headRowPrev.remove(0);
            //colToGo = headColPrev.remove(0);

            buttPosition = snakeArray.remove(0);
        }
    }
}
