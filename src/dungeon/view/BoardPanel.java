package dungeon.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dungeon.model.Direction;
import dungeon.model.Health;
import dungeon.model.RDungeon;
import dungeon.model.RLocation;
import dungeon.model.Smell;
import dungeon.model.Treasure;

class BoardPanel extends JPanel {
  private final String IMAGE_URL = "C:\\Users\\njord\\Downloads\\Project3-Dungeon\\dungeon-images\\";
  private final int scaleX;
  private final int scaleY;
  private final HashMap<Set, String> imageMap;
  private RLocation[][] locations;
  private Dimension boardSize;
  private RDungeon readModel;

  public BoardPanel(Dimension d, RDungeon model, int scaleX, int scaleY) {
    locations = new RLocation[d.height][d.width];
    readModel = model;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.boardSize = d;
    this.setBackground(Color.BLACK);

    imageMap = new HashMap<>();
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH
            , Direction.EAST, Direction.WEST)), "NSEW");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.WEST
            , Direction.EAST)), "NEW");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH
            , Direction.EAST)), "NSE");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH
            , Direction.WEST)), "NSW");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST, Direction.SOUTH
            , Direction.EAST)), "SEW");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.EAST)), "NE");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH)), "NS");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH, Direction.EAST)), "SE");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST, Direction.EAST)), "EW");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.WEST)), "NW");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH, Direction.WEST)), "SW");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH)), "S");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH)), "N");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.EAST)), "E");
    imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST)), "W");
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Dimension realBoardSize = convertBoardDimensions(this.boardSize);
    locations = readModel.getVisitedLocations();
    for (int i = 0; i < locations.length; i++) {
      for (int j = 0; j < locations[i].length; j++) {
        if (locations[i][j] != null) {
          RLocation location = locations[i][j];
          BufferedImage picture = getLocationImage(location);
          if (location.getCoordinate().equals(readModel.getCurrentCoordinate())) {
            try {
              picture = overlay(picture, IMAGE_URL + "player.png", 0);
              if (readModel.getSmell() == Smell.MORE_PUNGENT) {
                picture = overlay(picture, IMAGE_URL + "stench02.png", 0);
              } else if (readModel.getSmell() == Smell.LESS_PUNGENT) {
                picture = overlay(picture, IMAGE_URL + "stench01.png", 0);
              }
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          }
          int xCoordinate = (j) * realBoardSize.width / boardSize.width;
          int yCoordinate = (i) * realBoardSize.height / boardSize.height;
          g2d.drawImage(picture, xCoordinate, yCoordinate, this);
        }
      }
    }
    if (readModel.isGameOver()) {
      BufferedImage picture;
      try {
        if (readModel.getPlayerHealth() == Health.HEALTHY) {
          picture = ImageIO.read(new File(IMAGE_URL + "win.png"));
        } else {
          picture = ImageIO.read(new File(IMAGE_URL + "lose.png"));
        }
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
      g2d.drawImage(picture, 0, 0, this);
    }
  }

  public void setModel(Dimension d, RDungeon model) {
    locations = new RLocation[d.height][d.width];
    readModel = model;
    this.boardSize = d;
    this.setPreferredSize(convertBoardDimensions(d));
  }

  private Dimension convertBoardDimensions(Dimension d) {
    return new Dimension(d.width * scaleX, d.height * scaleY);
  }

  private BufferedImage getLocationImage(RLocation location) {
    Set<Direction> directions = location.getDirections();
    BufferedImage picture;
    try {
      // Get directions
      picture = ImageIO.read(new File(IMAGE_URL + imageMap.get(directions) + ".png"));
      // Get Otyugh
      if (location.getOtyughHealth() == Health.HEALTHY
              || location.getOtyughHealth() == Health.INJURED) {
        picture = overlay(picture, IMAGE_URL + "otyugh.png", 0);
      }
      if (location.getTreasure() != null) {
        List<Treasure> treasures = location.getTreasure();
        if (treasures.contains(Treasure.RUBY)) {
          picture = overlay(picture, IMAGE_URL + "ruby.png", 0);
        }
        if (treasures.contains(Treasure.SAPPHIRE)) {
          picture = overlay(picture, IMAGE_URL + "emerald.png", 5);
        }
        if (treasures.contains(Treasure.DIAMOND)) {
          picture = overlay(picture, IMAGE_URL + "diamond.png", 10);
        }
      }
      if (!location.getArrows().isEmpty()) {
        picture = overlay(picture, IMAGE_URL + "arrow-black.png", 15);
      }
      if (location.getThief() != null) {
        picture = overlay(picture, IMAGE_URL + "thief.png", 20);
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