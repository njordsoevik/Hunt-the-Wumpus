package dungeon.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
    private HashMap<Set, String> imageMap;

    public BoardPanel(Dimension d, RDungeon model) {
        locations = new RLocation[d.height][d.width];
        readModel = model;
        this.boardSize = d;
        this.setBackground(Color.BLACK);

        imageMap = new HashMap<>();
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH
                , Direction.EAST, Direction.WEST)),"NSEW");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.WEST
                , Direction.EAST)),"NEW");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH
                , Direction.EAST)),"NES");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH
                , Direction.WEST)),"NSW");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST, Direction.SOUTH
                , Direction.EAST)),"ESW");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.EAST)),"NE");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH)),"NS");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH, Direction.EAST)),"ES");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST, Direction.EAST)),"EW");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.WEST)),"WN");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH, Direction.WEST)),"SW");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH)),"S");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH)),"N");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.EAST)),"E");
        imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST)),"W");
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
        Set<Direction> directions = location.getDirections();
        BufferedImage picture;
        try {
            // Get directions
            picture = ImageIO.read(new File(IMAGE_URL+imageMap.get(directions)+".png"));
            // Get Otyugh
//            if (location.getOtyugh() != null) {
//                overlay(picture,"otyugh.png",0);
//            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
        }
        return picture;
    }

    private BufferedImage overlay(BufferedImage starting, String fpath, int offset) throws IOException {
        BufferedImage overlay = ImageIO.read(new File(fpath));
        int w = Math.max(starting.getWidth(), overlay.getWidth());
        int h = Math.max(starting.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();
        g.drawImage(starting, 0, 0, null);
        g.drawImage(overlay, offset, offset, null);
        return combined;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        System.out.println(readModel.getDirections());
        Dimension realBoardSize = convertBoardDimensions(this.boardSize);
        locations = readModel.getVisitedLocations();
        for (int i = 0; i < readModel.getVisitedLocations().length; i++) {
            for (int j = 0; j < readModel.getVisitedLocations()[i].length; j++) {
                if (readModel.getVisitedLocations()[i][j]!=null) {
                    System.out.println(i + " " + j);
                }
            }
        }
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j]!=null) {
                    BufferedImage picture = getLocationImage(locations[i][j]);
                    g2d.drawImage(picture,j*realBoardSize.width/locations[i].length,i*realBoardSize.height/locations.length,  this);
                }
            }
        }
    }
}