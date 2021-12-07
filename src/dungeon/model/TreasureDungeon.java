package dungeon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * TreasureDungeon implements the Dungeon interface. Dungeons store treasure and can be naviagted
 * by the player.
 */
public class TreasureDungeon implements Dungeon {
  private final Location[][] grid;
  private final List<List> forest;
  private final Player player;
  private final List<Edge> edges;
  private final List<Edge> usedEdges;
  private final List<Edge> unusedEdges;
  private final Random rand;
  private Coordinate startC;
  private Coordinate endC;

  /**
   * Constructor for a dungeon, can specify size and attributes of the generated dungeon.
   *
   * @param rows              The number of rows in the dungeon.
   * @param columns           The number of columns in the dungeon.
   * @param interconnectivity The interconnectivity for kruskal's algorithm.
   * @param wrapped           If the dungeon should wrap around the edges.
   * @param treasurePercent   Percent of caves with treasure.
   */
  public TreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped,
                         int treasurePercent) {
    this(rows, columns, interconnectivity, wrapped, treasurePercent, null);
  }

  /**
   * Constructor for a dungeon, can specify size and attributes of the generated dungeon.
   *
   * @param rows              The number of rows in the dungeon.
   * @param columns           The number of columns in the dungeon.
   * @param interconnectivity The interconnectivity for kruskal's algorithm.
   * @param wrapped           If the dungeon should wrap around the edges.
   * @param treasurePercent   Percent of caves with treasure.
   * @param seed              Seed for testing randomness.
   */
  public TreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped,
                         int treasurePercent, Long seed) {
    if (rows > Integer.MAX_VALUE || columns > Integer.MAX_VALUE
            || interconnectivity > Integer.MAX_VALUE || treasurePercent > Integer.MAX_VALUE) {
      throw new ArithmeticException("Input above max values");
    }
    if (rows <= 0 || columns <= 0
            || interconnectivity < 0 || treasurePercent < 0) {
      throw new IllegalArgumentException("Arguments cannot be below zero.");
    }
    if ((rows + columns < 7)) {
      throw new IllegalArgumentException("Need a bigger dungeon for a start and end distance"
              + " greater than 5.");
    }
    this.rand = new Random();
    if (seed != null) {
      this.rand.setSeed(seed);
    }
    this.grid = new Location[rows][columns];
    this.forest = new ArrayList<>();
    this.edges = new ArrayList<>();
    this.usedEdges = new ArrayList<>();
    this.unusedEdges = new ArrayList<>();

    // Create base grid of locations
    createGrid(rows, columns);

    // Create all edges
    createAllEdges(rows, columns, wrapped);

    // Loop through edges to add them to used edges set
    createUsedEdges();

    // Loop through unused edges for interconnectivity
    createUnusedEdges(interconnectivity);

    // Loop through resulting edges and link the locations by added them to each-other's lists
    linkLocationsByEdge();

    // Set the start/end spot for the player
    setStartEnd();

    // Set treasure percent
    placeTreasure(rows, columns, treasurePercent);

    // Create player at starting coordinate
    this.player = new Player(startC);
    getCoordinateLocation(startC).setVisited();
  }


  @Override
  public boolean isGameOver() {
    return (isEndSquare(this.player.getCoordinate()) || getPlayer().getHealth() == Health.DEAD);
  }

  @Override
  public void movePlayer(Direction dir) {
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over!");
    }
    // Get player current location
    Location currentLocation = getCoordinateLocation(player.getCoordinate());
    // If current location has this direction available
    if (getDirections().contains(dir)) { // Set player coordinate
      Coordinate newSquare = currentLocation.getPaths().get(dir).getCoordinate();
      player.setCoordinate(newSquare);
      getCoordinateLocation(newSquare).setVisited();
    } else {
      throw new IllegalArgumentException("Cannot move there");
    }
  }

  @Override
  public List<Treasure> getCurrentLocationTreasure() {
    return getCoordinateLocation(player.getCoordinate()).getTreasure();
  }

  @Override
  public Set<Direction> getDirections() {
    Coordinate playerCoordinate = player.getCoordinate();
    Location currentLocation = getCoordinateLocation(playerCoordinate);
    Set<Direction> directions = currentLocation.getPaths().keySet();
    return directions;
  }

  @Override
  public List<Treasure> getPlayerTreasure() {
    return this.player.getTreasures();
  }

  @Override
  public void takeTreasure() {
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over!");
    }
    Location current = getCoordinateLocation(player.getCoordinate());
    if (!current.getTreasure().isEmpty()) {
      player.addTreasure(current.getTreasure());
      current.removeTreasure();
    }
  }

  protected Location[][] getGrid() {
    return this.grid;
  }

  protected Random getRandom() {
    return this.rand;
  }

  protected Player getPlayer() {
    return this.player;
  }

  protected Coordinate getStart() {
    return startC;
  }

  protected Coordinate getEnd() {
    return endC;
  }

  protected Location getCoordinateLocation(Coordinate c) {
    return grid[c.getI()][c.getJ()];
  }

  protected boolean isEndSquare(Coordinate c) {
    return endC.equals(c);
  }

  private void createGrid(int rows, int columns) {
    // Create all locations grid
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        // Create coordinate for location and forest
        Coordinate c = new Coordinate(i, j);
        // Create location
        Location l = new DungeonLocation(c);
        // Add location to grid
        grid[i][j] = l;
        // Create list (set) for forest
        List<Coordinate> s = new ArrayList<>();
        // Add coordinate to set
        s.add(c);
        // Add set to forest
        forest.add(s);
      }
    }
  }

  private void createAllEdges(int rows, int columns, boolean wrapped) {
    // Create all possible edges linking locations
    for (int i = 0; i < rows; i++) {
      Coordinate c1;
      Coordinate c2;
      for (int j = 0; j < columns; j++) {
        if (i < rows - 1 && j < columns - 1) {
          c1 = new Coordinate(i, j);
          c2 = new Coordinate(i + 1, j);
          edges.add(new Edge(1, c1, c2, Direction.SOUTH));
          c1 = new Coordinate(i, j);
          c2 = new Coordinate(i, j + 1);
          edges.add(new Edge(1, c1, c2, Direction.EAST));
        } else if (i == rows - 1 && j != columns - 1) {
          if (wrapped) {
            c1 = new Coordinate(i, j);
            c2 = new Coordinate(0, j);
            edges.add(new Edge(1, c1, c2, Direction.SOUTH));
          }
          c1 = new Coordinate(i, j);
          c2 = new Coordinate(i, j + 1);
          edges.add(new Edge(1, c1, c2, Direction.EAST));
        } else if (j == columns - 1 && i != rows - 1) {
          if (wrapped) {
            c1 = new Coordinate(i, j);
            c2 = new Coordinate(i, 0);
            edges.add(new Edge(1, c1, c2, Direction.EAST));
          }
          c1 = new Coordinate(i, j);
          c2 = new Coordinate(i + 1, j);
          edges.add(new Edge(1, c1, c2, Direction.SOUTH));
        } else if (j == columns - 1 && i == rows - 1) {
          if (wrapped) {
            c1 = new Coordinate(i, j);
            c2 = new Coordinate(i, 0);
            edges.add(new Edge(1, c1, c2, Direction.EAST));
            c1 = new Coordinate(i, j);
            c2 = new Coordinate(0, j);
            edges.add(new Edge(1, c1, c2, Direction.SOUTH));
          }
        }
      }
    }
  }

  private void createUsedEdges() {
    while (edges.size() > 0) {
      // get random edge
      int rand_i = rand.nextInt(edges.size());
      Edge e = edges.get(rand_i);

      //  check if both locations are in same set
      int i = checkSet(e.getSource());
      int j = checkSet(e.getDestination());
      if (i != j) { // Not in shared set, combine.
        combineSets(i, j);
        usedEdges.add(e);
      } else { // same set, put aside
        unusedEdges.add(e);
      }
      // remove edge from edges list
      edges.remove(e);
    }
  }

  private void createUnusedEdges(int interconnectivity) {
    for (int k = 0; k < interconnectivity; k++) {
      if (unusedEdges.size() > 0) {
        int rand_i = rand.nextInt(unusedEdges.size());
        Edge e = unusedEdges.get(rand_i);
        usedEdges.add(e);
        unusedEdges.remove(e);
      } else {
        break;
      }
    }
  }

  private void linkLocationsByEdge() {
    for (Edge e : usedEdges) {
      Coordinate c1 = e.getSource();
      Coordinate c2 = e.getDestination();
      Location l1 = getCoordinateLocation(c1);
      Location l2 = getCoordinateLocation(c2);
      l1.addPath(e.getDirection(), l2);
      l2.addPath(e.getDirection().getInverse(), l1);
    }
  }

  private void setStartEnd() {
    Coordinate randStart = new Coordinate(rand.nextInt(this.grid.length),
            rand.nextInt(this.grid[0].length));
    Coordinate randEnd = new Coordinate(rand.nextInt(this.grid.length),
            rand.nextInt(this.grid[0].length));
    int attempts = 0;
    while ((((Math.abs(randEnd.getI() - randStart.getI()) + Math.abs(randEnd.getJ()
            - randStart.getJ())) < 5)
            || getCoordinateLocation(randStart).getLocationType() == LocationType.TUNNEL
            || getCoordinateLocation(randEnd).getLocationType() == LocationType.TUNNEL)
            && attempts < 1000) {
      randStart = new Coordinate(rand.nextInt(this.grid.length),
              rand.nextInt(this.grid[0].length));
      randEnd = new Coordinate(rand.nextInt(this.grid.length),
              rand.nextInt(this.grid[0].length));
      attempts++;
    }
    if (attempts == 1000) {
      throw new IllegalStateException("This randomized dungeon could not generate a dungeon" +
              " with start and end distance greater than 5.");
    }
    this.startC = randStart;
    this.endC = randEnd;
  }

  private int checkSet(Coordinate c) {
    for (int i = 0; i < forest.size(); i++) {
      if (forest.get(i).contains(c)) {
        return i;
      }
    }
    return -1;
  }

  private void combineSets(int i, int j) {
    forest.get(i).addAll(forest.get(j));
    forest.remove(j);
  }


  private void placeTreasure(int rows, int columns, int percent) {
    int treasureIndex;
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
    Collections.shuffle(places, rand);
    for (int k = 0; k < num; k++) {
      // k modulo size to wrap around places list in case of over 100% treasure
      int x = places.get(k % places.size())[0];
      int y = places.get(k % places.size())[1];
      if (this.grid[x][y].getLocationType() == LocationType.CAVE) {
        treasureIndex = rand.nextInt(Treasure.values().length);
        this.grid[x][y].addTreasure(Treasure.values()[treasureIndex]);
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < this.grid.length; i++) {
      b.append("\n");
      for (int j = 0; j < this.grid[i].length; j++) {
        b.append(this.grid[i][j]);
        b.append(this.grid[i][j].eastStringHelper());
      }
      b.append("\n");
      for (int j = 0; j < this.grid[i].length; j++) {
        b.append(this.grid[i][j].southStringHelper());
        b.append(" ");
      }
    }
    b.append("\n");
    return b.toString();
  }


}
