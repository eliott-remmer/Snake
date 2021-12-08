package snake_v2;

import java.util.ArrayList;

public class SnakeModel {

    int row = 40;
    int col = 70;
    boolean eternalMode = true;
    int length;
    int treats;
    int rowDir;
    int colDir;
    int nextRow;
    int nextCol;
    ArrayList<Coordinates> body;
    Coordinates headPos;
    Coordinates buttPos;

    SnakeModel() {
        initSnake();
    }

    public void initSnake() {
        length = 10;
        treats = 0;
        rowDir = 0;
        colDir = 1;
        nextRow = 20;
        nextCol = 0;
        body = new ArrayList<>();
        headPos = new Coordinates(nextRow, nextCol);
        buttPos = new Coordinates(0, 0);
    }

    public void updateHead() {
        nextRow += rowDir;
        nextCol += colDir;
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
        headPos = new Coordinates(nextRow, nextCol);
    }
    public void updateButt() { 
        if (body.size() > length) {
            buttPos = body.remove(0);
        }
    }
    public void setDir(int d) {
        switch (d) {
            case 0: //right
                rowDir = 0;
                colDir = 1;
                break;
            case 1: //left
                rowDir = 0;
                colDir = -1;
                break;
            case 2: //down
                rowDir = 1;
                colDir = 0;
                break;
            case 3: //up
                rowDir = -1;
                colDir = 0;
                break;
        }
    }
}
