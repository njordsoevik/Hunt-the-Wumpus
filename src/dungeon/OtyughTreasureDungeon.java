package dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OtyughTreasureDungeon extends TreasureDungeon implements OtyughDungeon {
  private final int STARTING_ARROWS = 3;

  public OtyughTreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped, int treasurePercent, int numberOtyugh) {
    this(rows, columns, interconnectivity, wrapped, treasurePercent, numberOtyugh, null);
  }

  public OtyughTreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped, int treasurePercent, int numberOtyugh, Long seed) {
    super(rows, columns, interconnectivity, wrapped, treasurePercent, seed);
    printGrid();
    placeOtyugh(rows, columns, numberOtyugh);

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
        if (getGrid()[x][y].getLocationType() == LocationType.CAVE) {
          getGrid()[x][y].addOtyugh();
          leftToPlace--;
        }
      }
    }
    if (leftToPlace > 0) {
      throw new IllegalArgumentException("Too many Otyughs, cannot fit in the caves.");
    }
  }
}
