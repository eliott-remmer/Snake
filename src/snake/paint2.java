package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class paint2 extends JFrame implements ActionListener, Serializable {
    JPanel panel;
    JButton[][] squares;
    int row = 40;
    int col = 70;
    public static ArrayList<Coordinates> paintC = new ArrayList<>();

    paint2() {
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(row, col));
        squares = new JButton[row][col];

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                JButton sq = new JButton();
                squares[i][j] = sq;
                sq.setBackground(Color.darkGray);
                sq.setOpaque(true);
                sq.setBorderPainted(false);
                panel.add(sq);
                sq.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                sq.addActionListener(this);
            }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (e.getSource() == squares[i][j]) {
                    if (squares[i][j].getBackground() == Color.darkGray)
                        squares[i][j].setBackground(Color.lightGray);
                    else {
                        squares[i][j].setBackground(Color.white);
                        System.out.println(i+" "+j);
                        paintC.add(new Coordinates(i, j));
                    }
                }
            }
    }

    public static void main(String[] args) throws IOException {
        new paint2();
    }
}
