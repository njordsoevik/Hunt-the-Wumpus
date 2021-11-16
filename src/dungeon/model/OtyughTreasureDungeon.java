package dungeon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * OtyughTreasureDungeon implements the OtyughDungeon interface. OtyughTreasureDungeons store
 * treasure,arrows, and Otyughs. The caves  can be navigated by the player.
 */
public class OtyughTreasureDungeon extends TreasureDungeon implements OtyughDungeon {
  private final int STARTING_ARROWS = 3;

  /**
   * Constructor for a dungeon, can specify size and attributes of the generated dungeon.
   *
   * @param rows              The number of rows in the dungeon.
   * @param columns           The number of columns in the dungeon.
   * @param interconnectivity The interconnectivity for kruskal's algorithm.
   * @param wrapped           If the dungeon should wrap around the edges.
   * @param treasurePercent   Percent of caves with treasure.
   * @param arrowPercent   Percent of caves with arrows.
   * @param numberOtyugh   The number of Otyughs to inhabit the caves. If zero is inputted, one
   *                       Otyugh will be placed at the final cave.
   */
  public OtyughTreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped,
                               int treasurePercent, int arrowPercent, int numberOtyugh) {
    this(rows, columns, interconnectivity, wrapped, treasurePercent,
            arrowPercent, numberOtyugh, null);
  }

  /**
   * Constructor for a dungeon, can specify size and attributes of the generated dungeon.
   *
   * @param rows              The number of rows in the dungeon.
   * @param columns           The number of columns in the dungeon.
   * @param interconnectivity The interconnectivity for kruskal's algorithm.
   * @param wrapped           If the dungeon should wrap around the edges.
   * @param treasurePercent   Percent of caves with treasure.
   * @param arrowPercent   Percent of caves with arrows.
   * @param numberOtyugh   The number of Otyughs to inhabit the caves. If zero is inputted, one
   *                       Otyugh will be placed at the final cave.
   * @param seed              Seed for testing randomness.
   */
  public OtyughTreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped,
                               int treasurePercent, int arrowPercent, int numberOtyugh, Long seed) {
    super(rows, columns, interconnectivity, wrapped, treasurePercent, seed);
    placeOtyugh(rows, columns, numberOtyugh);
    placeArrows(rows, columns, arrowPercent);
    addStartingArrows(STARTING_ARROWS);
  }


  private void placeOtyugh(int rows, int columns, int number) {
    int leftToPlace = number;
    // Place at end
    this.getCoordinateLocation(this.getEnd()).addOtyugh();
    leftToPlace--;
    // Place the rest
    if (leftToPlace > 0) {
      List<Integer[]> places = new ArrayList<>();
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          if (i != getEnd().getI() || j != getEnd().getJ()) {
            Integer[] z = new Integer[2];
            z[0] = i;
            z[1] = j;
            places.add(z);
          }
        }
      }
      Collections.shuffle(places, this.getRandom());
      for (int k = 0; k < places.size(); k++) {
        int x = places.get(k)[0];
        int y = places.get(k)[1];
        if (!(getGrid()[x][y].getCoordinate().equals(getStart()))
                && !(getGrid()[x][y].getCoordinate().equals(getEnd()))
                && (getGrid()[x][y].getLocationType() == LocationType.CAVE)
                && leftToPlace > 0) {
          getGrid()[x][y].addOtyugh();
          leftToPlace--;
        }
      }
    }
    if (leftToPlace > 0) {
      throw new IllegalArgumentException("Too many Otyughs, cannot fit in the caves.");
    }
  }

  private void placeArrows(int rows, int columns, int percent) {
    int arrowIndex;
    int num = (int) Math.ceil((double) ((percent) * (rows * columns)) / 100);

    List<Integer[]> places = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Integer[] z = new Integer[2];
        z[0] = i;
        z[1] = j;
        places.add(z);
      }
    }
    Collections.shuffle(places, this.getRandom());
    for (int k = 0; k < num; k++) {
      // k modulo size to wrap around places list
      int x = places.get(k % places.size())[0];
      int y = places.get(k % places.size())[1];
      arrowIndex = this.getRandom().nextInt(Arrow.values().length);
      this.getGrid()[x][y].addArrows(Arrow.values()[arrowIndex]);
    }
  }

  private void addStartingArrows(int arrows) {
    int arrowIndex;
    List<Arrow> a = new ArrayList<>();
    for (int k = 0; k < arrows; k++) {
      arrowIndex = this.getRandom().nextInt(Arrow.values().length);
      a.add(Arrow.values()[arrowIndex]);
    }
    getPlayer().addArrow(a);
  }

  @Override
  public void takeArrows() {
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over!");
    }
    Location current = getCoordinateLocation(getPlayer().getCoordinate());
    if (!current.getArrows().isEmpty()) {
      getPlayer().addArrow(current.getArrows());
      current.removeArrows();
    }
  }

  @Override
  public void shootArrow(Direction dir, int distance) {
    if (dir == null || distance < 0) {
      throw new IllegalArgumentException("Not valid direction or distance.");
    }
    if (getPlayer().getArrows().size() == 0) {
      throw new IllegalArgumentException("Player does not have any arrows to shoot.");
    }
    Location current = getCoordinateLocation(getPlayer().getCoordinate());
    shootArrowHelper(current, dir, distance, false);
    getPlayer().removeArrow();
  }

  private void shootArrowHelper(Location l, Direction dir, int distance, boolean traveling) {
    // if distance is zero, kill otyugh if there
    if (distance == 0) {
      if (l.getOtyugh() != null) {
        l.getOtyugh().reduceHealth();
      }
      return;
    }
    Map<Direction, Location> directions = l.getPaths();
    // if direction exists, move one into direction
    if (directions.containsKey(dir)) {
      shootArrowHelper(directions.get(dir), dir, distance - 1, true);
    } else { // if not, check if tunnel
      if (l.getLocationType() == LocationType.CAVE) {  // Break arrow in cave
        return;
      } else { // If in tunnel
        if (!traveling) { // If not traveling arrow, break
          return;
        } else { // If traveling arrow, shoot out other side
          directions.remove(dir.getInverse());
          for (Direction moveDirection : directions.keySet()) {
            shootArrowHelper(directions.get(moveDirection), moveDirection, distance - 1, true);
          }
        }
      }
    }
  }

  @Override
  public List<Arrow> getPlayerArrows() {
    return getPlayer().getArrows();
  }

  @Override
  public List<Arrow> getCurrentLocationArrows() {
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over!");
    }
    return getCoordinateLocation(getPlayer().getCoordinate()).getArrows();
  }

  @Override
  public Health getPlayerHealth() {
    return getPlayer().getHealth();
  }

  @Override
  public void movePlayer(Direction dir) {
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over!");
    }
    // Get player current location
    Location currentLocation = getCoordinateLocation(getPlayer().getCoordinate());
    // If current location has this direction available
    if (getDirections().contains(dir)) { // Set player coordinate
      Coordinate newSquare = currentLocation.getPaths().get(dir).getCoordinate();
      getPlayer().setCoordinate(newSquare);
      checkOtyugh(getCoordinateLocation(newSquare));
    } else {
      throw new IllegalArgumentException("Cannot move there");
    }
  }

  private void checkOtyugh(Location l) {
    Otyugh o = l.getOtyugh();
    if (o != null) {
      if (o.getHealth() == Health.HEALTHY) {
        getPlayer().reduceHealth();
      } else if (o.getHealth() == Health.INJURED) {
        int hit = getRandom().nextInt(2);
        if (hit == 1) {
          getPlayer().reduceHealth();
        }
      }
    }
  }

  @Override
  public Smell getSmell() {
    int countOtyugh = 0;
    List<Location> secondaryLocations = new ArrayList<>();
    Location currentLocation = getCoordinateLocation(getPlayer().getCoordinate());
    // Check all first directions
    for (Map.Entry<Direction, Location> entry : currentLocation.getPaths().entrySet()) {
      Location l = entry.getValue();
      Direction d = entry.getKey();
      Otyugh o = l.getOtyugh();
      if (o != null && (l.getOtyugh().getHealth() == Health.HEALTHY
              || l.getOtyugh().getHealth() == Health.INJURED)) {
        return Smell.MORE_PUNGENT;
      } else {
        Map<Direction, Location> temp = l.getPaths(); // Get all secondary locations
        temp.remove(d.getInverse()); // Remove our source node
        secondaryLocations.addAll(temp.values());
      }
    }
    for (Location l2 : secondaryLocations) {
      Otyugh o2 = l2.getOtyugh();
      if (o2 != null && (o2.getHealth() == Health.HEALTHY
              || o2.getHealth() == Health.INJURED)) {
        countOtyugh++;
      }
    }
    if (countOtyugh >= 2) {
      return Smell.MORE_PUNGENT;
    } else if (countOtyugh == 1) {
      return Smell.LESS_PUNGENT;
    } else {
      return Smell.NONE;
    }
  }

}
