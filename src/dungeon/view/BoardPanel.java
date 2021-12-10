package dungeon.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

/**
 * The view for the dungeon graphical user interface (GUI) dungeon board. Players will use the
 * board to understand where they are in the dungeon, and what features surround them.
 */
class BoardPanel extends JPanel {
  private final int scaleX;
  private final int scaleY;
  private final HashMap<Set<Direction>, File> imageMap;
  private RLocation[][] locations;
  private Dimension boardSize;
  private RDungeon readModel;
  private final BufferedImage player;
  private final BufferedImage win;
  private final BufferedImage lose;
  private final BufferedImage stenchOne;
  private final BufferedImage stenchTwo;
  private final BufferedImage thief;
  private final BufferedImage otyugh;
  private final BufferedImage ruby;
  private final BufferedImage sapphire;
  private final BufferedImage diamond;
  private final BufferedImage arrow;

  BoardPanel(Dimension d, RDungeon model, int scaleX, int scaleY) {
    locations = new RLocation[d.height][d.width];
    readModel = model;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.boardSize = d;
    this.setBackground(Color.BLACK);

    try {
      player = ImageIO.read(loadImage("player.png"));
      win = ImageIO.read(loadImage("win.png"));
      lose = ImageIO.read(loadImage("lose.png"));
      stenchOne = ImageIO.read(loadImage("stench01.png"));
      stenchTwo = ImageIO.read(loadImage("stench02.png"));
      thief = ImageIO.read(loadImage("thief.png"));
      otyugh = ImageIO.read(loadImage("otyugh.png"));
      ruby = ImageIO.read(loadImage("ruby.png"));
      sapphire = ImageIO.read(loadImage("emerald.png"));
      diamond = ImageIO.read(loadImage("diamond.png"));
      arrow = ImageIO.read(loadImage("arrow-black.png"));
    } catch (IOException ex) {
      throw new IllegalStateException("Could not find images");
    }

    imageMap = new HashMap<>();
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST,
            Direction.WEST)), loadImage("NSEW.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.WEST, Direction.EAST)),
            loadImage("NEW.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST)),
            loadImage("NSE.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.WEST)),
            loadImage("NSW.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST, Direction.SOUTH, Direction.EAST)),
            loadImage("SEW.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.EAST)),
            loadImage("NE.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.SOUTH)),
            loadImage("NS.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH, Direction.EAST)),
            loadImage("SE.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.WEST, Direction.EAST)),
            loadImage("EW.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.NORTH, Direction.WEST)),
            loadImage("NW.png"));
    imageMap.put(new HashSet<>(Arrays.asList(Direction.SOUTH, Direction.WEST)),
            loadImage("SW.png"));
    imageMap.put(new HashSet<>(List.of(Direction.SOUTH)), loadImage("S.png"));
    imageMap.put(new HashSet<>(List.of(Direction.NORTH)), loadImage("N.png"));
    imageMap.put(new HashSet<>(List.of(Direction.EAST)), loadImage("E.png"));
    imageMap.put(new HashSet<>(List.of(Direction.WEST)), loadImage("W.png"));
  }

  private File loadImage(String name) {
   // File f3 = new File();
    File f = new File("");
    String path = f.getAbsolutePath();
    File fImg = new File(path + "/res/img/" + name);
    return fImg;
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

              picture = overlay(picture, player, 0);
              if (readModel.getSmell() == Smell.MORE_PUNGENT) {
                picture = overlay(picture, stenchTwo, 0);
              } else if (readModel.getSmell() == Smell.LESS_PUNGENT) {
                picture = overlay(picture, stenchOne, 0);
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
      if (readModel.getPlayerHealth() == Health.HEALTHY) {
        picture = win;
      } else {
        picture = lose;
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
      picture = ImageIO.read(imageMap.get(directions));
      // Get Otyugh
      if (location.getOtyughHealth() == Health.HEALTHY
              || location.getOtyughHealth() == Health.INJURED) {
        picture = overlay(picture, otyugh, 0);
      }
      if (location.getTreasure() != null) {
        List<Treasure> treasures = location.getTreasure();
        if (treasures.contains(Treasure.RUBY)) {
          picture = overlay(picture, ruby, 0);
        }
        if (treasures.contains(Treasure.SAPPHIRE)) {
          picture = overlay(picture, sapphire, 5);
        }
        if (treasures.contains(Treasure.DIAMOND)) {
          picture = overlay(picture, diamond, 10);
        }
      }
      if (!location.getArrows().isEmpty()) {
        picture = overlay(picture, arrow, 15);
      }
      if (location.getThief() != null) {
        picture = overlay(picture, thief, 20);
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }

    return picture;
  }

  private BufferedImage overlay(BufferedImage starting,
                                BufferedImage overlay, int offset) throws IOException {
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

}