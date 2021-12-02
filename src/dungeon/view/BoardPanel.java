package dungeon.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;

import dungeon.model.Direction;
import dungeon.model.RLocation;
import dungeon.model.RDungeon;

class BoardPanel extends JPanel {
    private RLocation[][] locations;
    private final String IMAGE_URL = "C:\\Users\\njord\\Downloads\\Project3-Dungeon\\dungeon-images-bw\\";
    private Dimension boardSize;
    private RDungeon readModel;
    private final int preferredScaleX = 200;
    private final int preferredScaleY = 200;

    public BoardPanel(Dimension d, RDungeon model) {
        locations = new RLocation[d.height][d.width];
        readModel = model;
        this.boardSize = d;
        this.setBackground(Color.BLACK);
    }

    public void setModel(Dimension d, RDungeon model) {
        locations = new RLocation[d.height][d.width];
        readModel = model;
        this.boardSize = d;
        this.setPreferredSize(new Dimension(d.width*preferredScaleX,d.height*preferredScaleY));
    }

    private Dimension convertBoardDimensions(Dimension d) {
        return new Dimension(d.width*200, d.height*200);
    }

    private BufferedImage getLocationImage(RLocation location) {
        //Map<Direction, Loca> directions = location.getPaths();

        try {
            BufferedImage picture = ImageIO.read(new File(IMAGE_URL+"NSE.png"));
            return picture;
        } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Location[][] l = model.getLocations
        // l.removeTreasure
        // l.addMonster
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Dimension realBoardSize = convertBoardDimensions(this.boardSize);
        locations = readModel.getVisitedLocations();
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j]!=null) {
                    BufferedImage picture = getLocationImage(locations[i][j]);
                    g2d.drawImage(picture,i*realBoardSize.width/locations.length, j*realBoardSize.height/locations[i].length, this);
                    g2d.drawImage(picture,i*realBoardSize.width/locations.length, j*realBoardSize.height/locations[i].length, this);
                }
            }
        }
        // draw grid lines


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