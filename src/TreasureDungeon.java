import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class TreasureDungeon implements Dungeon {
  private Location[][] grid;
  private List<Edge> edges;
  private List<Set> forest;
  private Player player;
  private Location[] parents;


  public TreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped) {
    this.edges = new ArrayList<>();
    grid = new Location[rows][columns];
    forest = new ArrayList<>();

    for (int i=0; i<rows; i++) {
      for (int j = 0; j < columns; j++) {
        Location L = new DungeonLocation();
        grid[i][j] = L;
      }
    }
    // Create edges(potential paths)

    for (int i=0; i<rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (i<rows-1 && j < columns-1) {
          List<Location> ls1 = new ArrayList<>();
          ls1.add(grid[i][j]);
          ls1.add(grid[i+1][j]);
          edges.add(new Edge(1, ls1));
          List<Location> ls2 = new ArrayList<>();
          ls2.add(grid[i][j]);
          ls2.add(grid[i][j+1]);
          edges.add(new Edge(1, ls2));
        }
        else if (i==rows-1 && j!=columns-1 ){
          List<Location> ls2 = new ArrayList<>();
          ls2.add(grid[i][j]);
          ls2.add(grid[i][j+1]);
          edges.add(new Edge(1, ls2));
        }
        else if (j==columns-1 && i!=rows-1 ){
          List<Location> ls2 = new ArrayList<>();
          ls2.add(grid[i][j]);
          ls2.add(grid[i+1][j]);
          edges.add(new Edge(1, ls2));
        }
      }
    }
    Random rand = new Random();
    for (Edge e: edges) {
      List<Edge> used = new ArrayList<>();
      List<Edge> leftover = new ArrayList<>();

      //int i = rand.nextInt();

      //edges.get(i);
    }
  }

  int find (Location L) {
    for (int i=0;i<forest.size();i++) {
      if (forest.get(i).contains(L)) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    TreasureDungeon p = new TreasureDungeon(2,2,5,true);
    System.out.println(p.edges.size());
    for (Edge e : p.edges) {
      System.out.println(e.getLocations());
    }


  }

  @Override
  public void movePlayer(Direction dir) {

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
