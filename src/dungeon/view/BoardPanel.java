package dungeon.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;

import dungeon.model.Direction;
import dungeon.model.RLocation;
import dungeon.model.RDungeon;
import dungeon.model.Treasure;

class BoardPanel extends JPanel {
    private RLocation[][] locations;
    private final String IMAGE_URL = "C:\\Users\\njord\\Downloads\\Project3-Dungeon\\dungeon-images-bw\\";
    private Dimension boardSize;
    private RDungeon readModel;
    private final int SCALE_X = 100;
    private final int SCALE_Y = 75;
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


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Dimension realBoardSize = convertBoardDimensions(this.boardSize);
        locations = readModel.getVisitedLocations();
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j]!=null) {
                    RLocation location = locations[i][j];
                    BufferedImage picture = getLocationImage(location);
                    g2d.drawImage(picture,(j)*realBoardSize.width/locations[i].length
                            ,(i)*realBoardSize.height/locations.length,  this);
                }
            }
        }
        System.out.println(readModel);
    }

    public void setModel(Dimension d, RDungeon model) {
        locations = new RLocation[d.height][d.width];
        readModel = model;
        this.boardSize = d;
        this.setPreferredSize(convertBoardDimensions(d));
    }

    private Dimension convertBoardDimensions(Dimension d) {
        return new Dimension(d.width*SCALE_X, d.height*SCALE_Y);
    }

    private BufferedImage getLocationImage(RLocation location) {
        Set<Direction> directions = location.getDirections();
        BufferedImage picture;
        try {
            // Get directions
            picture = ImageIO.read(new File(IMAGE_URL+imageMap.get(directions)+".png"));
            // Get Otyugh
            if (location.getCoordinate().equals(readModel.getCurrentCoordinate())) {
                System.out.println("here");
                picture = overlay(picture,IMAGE_URL+"player.png",0);
            }
            if (location.getOtyugh() != null) {
                picture = overlay(picture,IMAGE_URL+"otyugh.png",0);
            }
            if (location.getTreasure()!=null) {
                List<Treasure> treasures = location.getTreasure();
                System.out.println(treasures);
                if (treasures.contains(Treasure.RUBY)) {
                    picture = overlay(picture,IMAGE_URL+"ruby.png",0);
                }
                if (treasures.contains(Treasure.SAPPHIRE)) {
                    picture = overlay(picture,IMAGE_URL+"emerald.png",5);
                }
                if (treasures.contains(Treasure.DIAMOND)) {
                    picture = overlay(picture,IMAGE_URL+"diamond.png",10);
                }
            }
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

}