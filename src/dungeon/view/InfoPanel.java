package dungeon.view;

import java.awt.*;
import java.util.Set;

import javax.swing.*;

import dungeon.model.Direction;
import dungeon.model.RDungeon;

public class InfoPanel extends JPanel {
  private Dimension boardSize;
  private RDungeon readModel;

  public InfoPanel(Dimension boardSize, RDungeon readModel) {
    this.boardSize = boardSize;
    this.readModel = readModel;
    StringBuilder sb = new StringBuilder();
    for (Direction p: readModel.getDirections()) {
      sb.append(p).append(", ");
    }
    this.add(new JLabel("Directions: "));
    this.add(new JLabel(sb.toString()));
  }

}
