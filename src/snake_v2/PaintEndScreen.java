package snake_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintEndScreen extends JFrame implements ActionListener {
    JPanel panel;
    JButton[][] squares;
    int row = 40;
    int col = 70;

    PaintEndScreen() {
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
                        }
                    }
                }
            }

    public static void main(String[] args) {
        new PaintEndScreen();
    }
}
