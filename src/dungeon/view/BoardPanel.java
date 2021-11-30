package dungeon.view;

import java.awt.*;

import javax.swing.*;

import dungeon.model.OtyughDungeon;

class BoardPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // draw grid lines
        g2d.drawLine(0,200,600,200);
        g2d.drawLine(0,400,600,400);
        g2d.drawLine(200,0,200,600);
        g2d.drawLine(400,0,400,600);

//            model.grid[][] board = model.getBoard();
//            //g2d.setFont();
//            // iterate over board, draw X and O accordingly
//
//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board[0].length; j++) {
//                    int spaceX = (200 * (j) + 100);
//                    int spaceY = (200 * (i) + 100);
//                    String p = "";
//                    if (board[i][j] != null) {
//                        p = board[i][j].toString();
//                    }
//                    g2d.drawString(p, spaceX, spaceY);
//                }
//            }
        System.out.println();
    }
}
