package dungeon.view;

import javax.swing.*;

public class JPanelBox extends JPanel {
  private JLabel display;
  private JTextField input;

  public JPanelBox(String title) {
//    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
//    this.setLayout(layout);
    display = new JLabel(title);
    input = new JTextField(5);
    this.add(display);
    this.add(input);
  }

  public JTextField getInput() {
    return input;
  }

}
