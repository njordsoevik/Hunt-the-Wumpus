import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreasureDungeon implements Dungeon {
  private Location[][] grid;
  private List<List> forest;
  private Player player;
  private Location[] parents;
  private List<Edge> edges;
  private List<Edge> usedEdges;
  private List<Edge> unusedEdges;
  private Random rand;


  public TreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped) {
    this.grid = new Location[rows][columns];
    this.rand = new Random();
    this.forest = new ArrayList<>();
    this.edges = new ArrayList<>();
    this.usedEdges = new ArrayList<>();
    this.unusedEdges = new ArrayList<>();

    // Create all locations grid
    for (int i=0; i<rows; i++) {
      for (int j = 0; j < columns; j++) {
        // Create coordinate for location and forest
        Coordinate c = new Coordinate(i,j);
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

    // Create all possible edges linking locations
    for (int i=0; i<rows; i++) {
      Coordinate c1;
      Coordinate c2;
      for (int j = 0; j < columns; j++) {
        if (i<rows-1 && j < columns-1) {
          List<Location> ls1 = new ArrayList<>();
          ls1.add(grid[i][j]);
          ls1.add(grid[i+1][j]);
          c1 = new Coordinate(i,j);
          c2 = new Coordinate(i+1,j);
          edges.add(new Edge(1, c1,c2));
          List<Location> ls2 = new ArrayList<>();
          ls2.add(grid[i][j]);
          ls2.add(grid[i][j+1]);
          c1 = new Coordinate(i,j);
          c2 = new Coordinate(i,j+1);
          edges.add(new Edge(1, c1,c2));
        }
        else if (i==rows-1 && j!=columns-1 ){
          if (wrapped==true) {
            List<Location> ls1 = new ArrayList<>();
            ls1.add(grid[i][j]);
            ls1.add(grid[0][j]);
            c1 = new Coordinate(i,j);
            c2 = new Coordinate(0,j);
            edges.add(new Edge(1, c1,c2));
          }
          List<Location> ls2 = new ArrayList<>();
          ls2.add(grid[i][j]);
          ls2.add(grid[i][j+1]);
          c1 = new Coordinate(i,j);
          c2 = new Coordinate(i,j+1);
          edges.add(new Edge(1, c1,c2));
        }
        else if (j==columns-1 && i!=rows-1 ){
          if (wrapped==true) {
            List<Location> ls1 = new ArrayList<>();
            ls1.add(grid[i][j]);
            ls1.add(grid[i][0]);
            c1 = new Coordinate(i,j);
            c2 = new Coordinate(i,0);
            edges.add(new Edge(1, c1,c2));
          }
          List<Location> ls2 = new ArrayList<>();
          ls2.add(grid[i][j]);
          ls2.add(grid[i+1][j]);
          c1 = new Coordinate(i,j);
          c2 = new Coordinate(i+1,j);
          edges.add(new Edge(1, c1,c2));
        }
      }
    }

    // Loop through edges
    while (edges.size()>0) {
      // get random edge
      int rand_i = rand.nextInt(edges.size());
      Edge e = edges.get(rand_i);

      //  check if both locations are in same set
      int i = checkSet(e.getSource());
      int j = checkSet(e.getDestination());
      if (i!=j) { // Not in shared set, combine.
        combineSets(i,j);
        usedEdges.add(e);
      } else { // same set, put aside
        unusedEdges.add(e);
      }
      // remove edge from edges list
      edges.remove(e);
    }

    // loop for interconnectivity
    for (int k = 0; k < interconnectivity; k++) {
      if (unusedEdges.size()>0) {
        int rand_i = rand.nextInt(unusedEdges.size());
        Edge e = unusedEdges.get(rand_i);
        usedEdges.add(e);
        unusedEdges.remove(e);
      }
      else {
        break;
      }
    }

    // Loop through edges and link the locations by added them to each-other's lists
    for (Edge e:usedEdges) {
      Coordinate c1 = e.getSource();
      Coordinate c2 = e.getDestination();
      Location l1 = getCoordinateLocation(c1);
      Location l2 = getCoordinateLocation(c2);
      l1.addPath(l2);
      l2.addPath(l1);
    }
  }

  private int checkSet (Coordinate c) {
    for (int i=0;i<forest.size();i++) {
      if (forest.get(i).contains(c)) {
        return i;
      }
    }
    return -1;
  }

  private void combineSets (int i, int j) {
    forest.get(i).addAll(forest.get(j));
    forest.remove(j);
  }



  private Location getCoordinateLocation (Coordinate c) {
    return grid[c.getX()][c.getY()];
  }

  private Coordinate getLocation (Location l) {
    return l.getCoordinate();
  }

  public static void main(String[] args) {
    TreasureDungeon p = new TreasureDungeon(4,3,1,true);
    Location l = p.grid[0][0];
    for (Location l1:l.getPaths()) {
      System.out.println(l1.getCoordinate());
    }

  }

  @Override
  public void movePlayer(Direction dir) {

  }

  @Override
  public Treasure getCurrentLocationTreasure() {
    return null;
  }

  @Override
  public List<Direction> getDirections() {
    return null;
  }

  @Override
  public List<Treasure> getPlayerTreasure() {
    return null;
  }

  @Override
  public void takeTreasure() {

  }
}
