package snake_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SnakeViewControl extends JFrame implements KeyListener{

    int row = 40;
    int col = 70;
    int delay;
    boolean playing = false;

    JPanel[][] squares;
    SnakeModel model;
    Thread myThread;
    Coordinates treatPos = new Coordinates(0, 0);
    ArrayList<Coordinates> endScreen;
    String filename = "gameover.txt";
    Random r = new Random();

    SnakeViewControl(SnakeModel m, int delay) throws FileNotFoundException {
        this.delay = delay;
        model = m;
        
        new JFrame();
        setSize(1050, 588); //400-12
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
            
        /* Initialize end screen */
        endScreen = new ArrayList<>();
        File file = new File("src/snake_v2/"+filename);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            endScreen.add(new Coordinates(row, col));
        }
        
        System.out.println("\n\n---------------------");
        System.out.println("Press space to start\n");
        System.out.println("---------------------\n\n");
    }
    public void startGame() {
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                squares[i][j].setBackground(Color.darkGray);
            }
        myThread = new Thread(() -> {
            while (playing) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {}
                repaintSnake();
                model.updateHead();
                model.updateButt();
            }
        });

        placeTreat();
    }
    public void repaintSnake() {
        if (model.headPos.equals(treatPos)) {
            model.length += 5;
            model.treats += 1;
            placeTreat();
        }
        if (model.eternalMode)
            if (!isTailBitten()) {
                squares[model.headPos.row][model.headPos.col].setBackground(Color.white);
                model.body.add(model.headPos); }
            else
                gameOver();
        if (!model.eternalMode)
            if (isInside() && !isTailBitten()) {
                squares[model.headPos.row][model.headPos.col].setBackground(Color.white);
                model.body.add(model.headPos); }
            else
                gameOver();
        squares[model.buttPos.row][model.buttPos.col].setBackground(Color.darkGray);
    }
    public void placeTreat() {
        while(true) {
            treatPos.row = r.nextInt(row);
            treatPos.col = r.nextInt(col);
            if (!model.body.contains(treatPos)) {
                squares[treatPos.row][treatPos.col].setBackground(Color.orange);
                break;
            }
        }
    }
    public boolean isTailBitten() {
        return (model.body.contains(model.headPos));
    }

    public boolean isInside() {
        return (model.headPos.row < row)
            && (model.headPos.row >= 0)
            && (model.headPos.col < col)
            && (model.headPos.col >= 0);
    }
    
    public void gameOver() {
        playing = false;
        System.out.println("\n\n---------------------");
        System.out.println("GAME OVER\nTREATS: "+model.treats);
        System.out.println("---------------------\n\n");

        /* Reset canvas */
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                squares[i][j].setBackground(Color.darkGray);
            }

        /* Paint end screen */
        for (Coordinates c: endScreen) {
            squares[c.row][c.col].setBackground(Color.white);
        }

        model.initSnake();
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
            if ((model.rowDir != 0) && (model.colDir != -1))
                model.setDir(0);
        }
        else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
            if ((model.rowDir != 0) && (model.colDir != 1))
                model.setDir(1);
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
            if ((model.rowDir != -1) && (model.colDir != 0))
                model.setDir(2);
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP) {
            if ((model.rowDir != 1) && (model.colDir != 0))
                model.setDir(3);
        }
        else if(e.getKeyCode()== KeyEvent.VK_M) {
            model.eternalMode = !model.eternalMode;
            String mode = (model.eternalMode) ? "enabled" : "disabled";
            System.out.println("\n\n---------------------");
            System.out.println("Eternal mode: "+mode+"\n");
            System.out.println("---------------------\n\n");
        }
        else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            if (!playing) {
                startGame();
                myThread.start();
                playing = true;
                String mode = (model.eternalMode) ? "enabled" : "disabled";
                System.out.println("\n\n---------------------");
                System.out.println("GO snake!\nEternal mode: "+mode+"\nTo change mode press m");
                System.out.println("---------------------\n\n");
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
