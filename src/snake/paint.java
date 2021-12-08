package snake;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class paint extends JPanel {

    public paint() {
        setBackground(Color.darkGray);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(0, 255, 93));
        //g.drawRect(340, 190, 10, 10);
        g.setColor(Color.white);
        g.fillRect(311, 191, 9, 9);
        g.fillRect(321, 191, 9, 9);
        g.fillRect(331, 191, 9, 9);
        g.fillRect(341, 191, 9, 9);
        g.fillRect(351, 191, 9, 9);
        g.fillRect(361, 191, 9, 9);
        g.fillRect(371, 191, 9, 9);
        g.fillRect(381, 191, 9, 9);
        g.fillRect(391, 191, 9, 9);
        g.fillRect(401, 191, 9, 9);
        g.fillRect(411, 191, 9, 9);



        g.setColor(Color.orange);
        g.fillRect(600, 350, 9, 9);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(700, 400);
        jFrame.add(new paint());
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}