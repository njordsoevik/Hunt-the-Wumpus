package dungeon.view;

import java.awt.event.ActionListener;

import javax.swing.*;

import dungeon.controller.DungeonViewController;

public class MenuPanel extends JPanel {
  private JPanel rowsPanel;
  private JPanel columnsPanel;
  private JPanel interconnectivityPanel;
  private JPanel percentTreasuresPanel;
  private JPanel percentArrowsPanel;
  private JPanel numberMonstersPanel;
  private JPanel connectPanel;
  private JPanel wrappedPanel;
  private JButton enterButton;
  private JComboBox<String> wrapped;

  public MenuPanel() {

  }

  public void addActionListener(ActionListener listener) {
    enterButton.addActionListener(listener);
  }
}
