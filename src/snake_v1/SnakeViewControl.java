package snake_v1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeViewControl extends JFrame implements KeyListener {

    JPanel boardpanel;
    JPanel[][] squares;
    SnakeModel model;
    Thread myThread;
    int row = 40;
    int col = 70;
    int treatRow;
    int treatCol;

    Coordinates treatPosition = new Coordinates(0, 0);

    SnakeViewControl(SnakeModel m, int delay) {
        model = m;
        new JFrame();
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        addKeyListener(this);
        setLocationRelativeTo(null);

        boardpanel = new JPanel();
        boardpanel.setBackground(Color.lightGray);
        boardpanel.setLayout(new GridLayout(row, col));
        squares = new JPanel[row][col];

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                JPanel sq = new JPanel();
                squares[i][j] = sq;
                sq.setBackground(Color.darkGray);
                boardpanel.add(sq);
                sq.setBorder(BorderFactory.createLineBorder(Color.darkGray));
            }

        add(boardpanel);
        placeTreat();
        setVisible(true);

        myThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {}
                repaintSnake();
                model.updatePosition();
                model.updateLength();
            }
        });
        myThread.start();
    }
    public void repaintSnake() {
        if (model.headPosition.equals(treatPosition)) { // (model.headRow == treatRow && model.headCol == treatCol)
            model.length = model.length + 5;
            placeTreat();
        }
        //squares[model.headPosition.row][model.headPosition.col].setBackground(Color.white);

        if (isInside() && !(isTailBitten())) {
            //squares[model.headRow][model.headCol].setBackground(Color.white);
            squares[model.headPosition.row][model.headPosition.col].setBackground(Color.white);
            model.snakeArray.add(model.headPosition);
        }
        else {
            gameOver();
        }
        //squares[model.rowToGo][model.colToGo].setBackground(Color.darkGray);
        squares[model.buttPosition.row][model.buttPosition.col].setBackground(Color.darkGray);

        //System.out.println("\n");
        //System.out.println(model.buttPosition);
        //System.out.println(model.snakeArray);
    }
    public void placeTreat() {
        Random r = new Random();
        treatPosition.row = r.nextInt(row);
        treatPosition.col = r.nextInt(col);
        squares[treatPosition.row][treatPosition.col].setBackground(Color.green);
        //treatRow = r.nextInt(row);
        //treatCol = r.nextInt(col);
        //squares[treatRow][treatCol].setBackground(Color.green);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
            if ((model.rowDirection != 0) && (model.colDirection != -1))
                model.changeDirection(0);
        }
        else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
            if ((model.rowDirection != 0) && (model.colDirection != 1))
                model.changeDirection(1);
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
            if ((model.rowDirection != -1) && (model.colDirection != 0))
                model.changeDirection(2);
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP) {
            if ((model.rowDirection != 1) && (model.colDirection != 0))
                model.changeDirection(3);
        }
    }
    public void gameOver() {
        int treats = (model.length-10)/5;
        System.out.println("GAME OVER\nTREATS: "+treats);
        System.exit(0);
    }

    public boolean isTailBitten() {
        //System.out.println("\n");
        //System.out.println(model.headRowPrev);
        //System.out.println(model.headRow);
        //System.out.println(model.headColPrev);
        //System.out.println(model.headCol);
        //return false;

        //return ((model.headRowPrev.contains(model.headRow)) && (model.headColPrev.contains(model.headCol))
        //    && (model.headRowPrev.indexOf(model.headRow)) == (model.headColPrev.indexOf(model.headCol)));

        //
        //
        //
        return (model.snakeArray.contains(model.headPosition));
    }
    public boolean isInside() {
        //return (model.headRow < row
        //        && model.headRow >= 0
        //        && model.headCol < col
        //        && model.headCol >= 0);

        return (model.headPosition.row < row)
                && (model.headPosition.row >= 0)
                && (model.headPosition.col < col)
                && (model.headPosition.col >= 0);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }
}
