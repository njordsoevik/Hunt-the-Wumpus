package dungeon.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;

import dungeon.model.Direction;
import dungeon.model.Location;
import dungeon.model.ROtyughDungeon;

class BoardPanel extends JPanel {
    private Location[][] locations;
    private final String IMAGE_URL = "C:\\Users\\njord\\Downloads\\Project3-Dungeon\\dungeon-images-bw\\";
    private Dimension boardSize;
    private ROtyughDungeon readModel;
    private final int preferredScaleX = 200;
    private final int preferredScaleY = 200;

    public BoardPanel(Dimension d, ROtyughDungeon model) {
        locations = new Location[d.width][d.height];
        readModel = model;
        this.boardSize = d;
        this.setBackground(Color.BLACK);
    }

    public void setModel(Dimension d, ROtyughDungeon model) {
        locations = new Location[d.width][d.height];
        readModel = model;
        this.boardSize = d;
        this.setPreferredSize(new Dimension(d.width*preferredScaleX,d.height*preferredScaleY));
    }

    private Dimension convertBoardDimensions(Dimension d) {
        return new Dimension(d.width*200, d.height*200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension realBoardSize = convertBoardDimensions(this.boardSize);
        System.out.println(this.boardSize.width);
        Graphics2D g2d = (Graphics2D) g;
        // draw grid lines
        try {
            BufferedImage myPicture = ImageIO.read(new File(IMAGE_URL+"NSE.png"));
            g2d.drawImage(myPicture,realBoardSize.width/2, realBoardSize.height/2, this);
        } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
        }


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

    public void setDirections(Set<Direction> directions) {

    }
}