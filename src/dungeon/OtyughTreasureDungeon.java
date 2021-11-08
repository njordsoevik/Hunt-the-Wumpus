package dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OtyughTreasureDungeon extends TreasureDungeon{
  private final int STARTING_ARROWS = 3;

  public OtyughTreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped, int treasurePercent, int numberOtyugh) {
    this(rows, columns, interconnectivity, wrapped, treasurePercent, numberOtyugh, null);
  }

  public OtyughTreasureDungeon(int rows, int columns, int interconnectivity, boolean wrapped, int treasurePercent, int numberOtyugh, Long seed) {
    super(rows, columns, interconnectivity, wrapped, treasurePercent, seed);
    placeOtyugh(rows,columns,numberOtyugh);
  }


  private void placeOtyugh(int rows, int columns, int number) {
    // Place at end
    this.getCoordinateLocation(this.getEnd()).addOtyugh();

    // Place the rest
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
    for (int k = 0; k < number; k++) {
      // k modulo size to wrap around places list in case of over 100% treasure
      int x = places.get(k % places.size())[0];
      int y = places.get(k % places.size())[1];
      if (getGrid()[x][y].getLocationType() == LocationType.CAVE) {
        try {
          getGrid()[x][y].addOtyugh();
        }
        catch (IllegalStateException ex) {
          throw new IllegalArgumentException("Too many Otyughs, cannot fit in the caves.");
        }
      }
    }
  }
}
