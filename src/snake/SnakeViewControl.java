package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SnakeViewControl extends JFrame implements KeyListener, Runnable {

    JPanel[][] squares;
    SnakeModel model;
    Random r = new Random();
    int row = 40;
    int col = 70;
    boolean playing = false;
    int delay;
    Coordinates treatPosition = new Coordinates(0, 0);
    ArrayList<Coordinates> paintArray;
    Thread myThread;

    SnakeViewControl(SnakeModel m, int delay) {
        this.delay = delay;
        model = m;
        new JFrame();
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(row, col));
        squares = new JPanel[row][col];

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                JPanel sq = new JPanel();
                squares[i][j] = sq;
                sq.setBackground(Color.darkGray);
                add(sq);
                sq.setBorder(BorderFactory.createLineBorder(Color.darkGray));
            }
        setVisible(true);
        System.out.println("Press space to start");
    }
    public void startGame() {
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                squares[i][j].setBackground(Color.darkGray);
            }
        myThread = new Thread();
        myThread.start();
        placeTreat();
        //myThread = new Thread(() -> {
        //    while (playing) {
        //        try {
        //            Thread.sleep(delay);
        //        } catch (InterruptedException ignored) {}
        //        repaintSnake();
        //        model.updatePosition();
        //        model.updateLength();
        //    }
        //});
    }

    public void run() {
        while (playing) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {}
            repaintSnake();
            model.updatePosition();
            model.updateLength();
        }
    }
    public void repaintSnake() {
        if (model.headPosition.equals(treatPosition)) {
            model.length += 5;
            model.treats += 1;
            placeTreat();
        }
        if (!model.eternalMode)
            if (isInside() && !isTailBitten()) {
                squares[model.headPosition.row][model.headPosition.col].setBackground(Color.white);
                model.snakeArray.add(model.headPosition); }
            else
                gameOver();
        if (model.eternalMode)
            if (!isTailBitten()) {
                squares[model.headPosition.row][model.headPosition.col].setBackground(Color.white);
                model.snakeArray.add(model.headPosition); }
            else
                gameOver();

        squares[model.buttPosition.row][model.buttPosition.col].setBackground(Color.darkGray);
    }
    public void placeTreat() {
        while(true) {
            treatPosition.row = r.nextInt(row);
            treatPosition.col = r.nextInt(col);
            if (!model.snakeArray.contains(treatPosition)) {
                squares[treatPosition.row][treatPosition.col].setBackground(Color.orange);
                break;
            }
        }
    }
    public boolean isTailBitten() {
        return (model.snakeArray.contains(model.headPosition));
    }

    public boolean isInside() {
        return (model.headPosition.row < row)
            && (model.headPosition.row >= 0)
            && (model.headPosition.col < col)
            && (model.headPosition.col >= 0);
    }

    public void gameOver() {
        playing = false;
        System.out.println("GAME OVER\nTREATS: "+model.treats);
        System.out.println("Press space to start");
        System.out.println(model.snakeArray);
        System.out.println(model.headPosition);

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                squares[i][j].setBackground(Color.darkGray);
            }
        model.resetSnake();
    }

    public void keyPressed(KeyEvent e) {
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
        else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            if (!playing) {
                startGame();
                playing = true;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
