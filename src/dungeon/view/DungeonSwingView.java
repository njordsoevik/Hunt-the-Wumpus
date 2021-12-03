package dungeon.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dungeon.controller.Features;
import dungeon.model.Direction;
import dungeon.model.RDungeon;
import dungeon.model.Treasure;

import java.util.List;

public class DungeonSwingView extends JFrame implements DungeonView {
  private RDungeon model;
  private JPanel container;
  private JPanel infoPanel;
  private JLabel directionsLabel;
  private JLabel smellLabel;
  private JLabel treasureLabel;
  private JLabel arrowLabel;
  private JMenuBar menuBar;
  private BoardPanel board;
  private JTextField rows;
  private JTextField columns;
  private JTextField interconnectivity;
  private JTextField percentTreasures;
  private JTextField percentArrows;
  private JTextField numberMonsters;
  private JButton enterButton;
  private JButton quitButton;
  private JComboBox<String> wrapped;
  private final int SCALE_X = 200;
  private final int SCALE_Y = 200;

  public DungeonSwingView(RDungeon m) {
    super("Otyugh Dungeon Menu");
    this.model = m;
    this.setSize(900,800);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // ADD MENU
    menuBar = new JMenuBar();
    menuBar.add(new JSeparator());
    menuBar.add(new JLabel("Rows"));
    rows = new JTextField("");
    menuBar.add(rows);
    menuBar.add(new JLabel("Columns"));
    columns = new JTextField("");
    menuBar.add(columns);
    menuBar.add(new JLabel("Interconnectivity"));
    interconnectivity = new JTextField("");
    menuBar.add(interconnectivity);
    menuBar.add(new JLabel("Treasures (%)"));
    percentTreasures = new JTextField("");
    menuBar.add(percentTreasures);
    menuBar.add(new JLabel("Arrows (%)"));
    percentArrows = new JTextField("");
    menuBar.add(percentArrows);
    menuBar.add(new JLabel("Monsters"));
    numberMonsters = new JTextField("");
    menuBar.add(numberMonsters);
    menuBar.add(new JLabel("Wrapped"));
    wrapped = new JComboBox(new String[] {"True", "False"});
    menuBar.add(wrapped);
    enterButton = new JButton("Enter");
    enterButton.setActionCommand("Enter Button");
    menuBar.add(enterButton);
    //quit button
    quitButton = new JButton("Quit");
    menuBar.add(quitButton);
    this.setJMenuBar(menuBar);

//    // ADD INFO BAR
    infoPanel = new JPanel();
    infoPanel.setBackground(Color.BLUE);
    directionsLabel = new JLabel("");
    directionsLabel.setForeground(Color.WHITE);
    smellLabel = new JLabel("");
    smellLabel.setForeground(Color.WHITE);
    treasureLabel = new JLabel("");
    treasureLabel.setForeground(Color.WHITE);
    arrowLabel = new JLabel("");
    arrowLabel.setForeground(Color.WHITE);
    updateInfoPanel();
    this.add(infoPanel, BorderLayout.NORTH);

    // ADD CONTAINER AND SCROLL PANE
    container = new JPanel();
    container.setLayout(new FlowLayout());
    JScrollPane scrollPane = new JScrollPane(container);
    // ADD BOARD TO SCROLL PANE
    setBoard(3,4,model);

    this.add(scrollPane);
  }

  @Override
  public void setBoard(int x, int y, RDungeon model) {
    if (board != null) {
      container.remove(board);
      this.board.setModel(new Dimension(x,y),model);
    } else {
      board = new BoardPanel(new Dimension(x,y),model);
    }
    this.board.setPreferredSize(new Dimension(x* SCALE_X,y* SCALE_Y));
    container.add(board);
  }

  @Override
  public void refresh() {
    validate();
    updateInfoPanel();
    repaint();
  }

  private void updateInfoPanel() {
    // ADD DIRECTIONS
    StringBuilder sb = new StringBuilder();
    for (Direction p: model.getDirections()) {
      sb.append(p).append(", ");
    }
    directionsLabel.setText(sb.toString());

    // ADD SMELL
    smellLabel.setText(model.getSmell().toString());

    // ADD TREASURES
    treasureLabel.setText(model.getCurrentLocationTreasure().toString());

    // ADD ARROWS
    arrowLabel.setText(model.getCurrentLocationArrows().toString());

    infoPanel.add(directionsLabel);
    infoPanel.add(smellLabel);
    infoPanel.add(treasureLabel);
    infoPanel.add(arrowLabel);
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void setFeatures(Features f) {

    enterButton.addActionListener(l -> f.processInput(rows.getText(), columns.getText()
            , interconnectivity.getText(), (String) wrapped.getSelectedItem()
            , percentArrows.getText(), percentTreasures.getText(), numberMonsters.getText()));

    quitButton.addActionListener(l -> f.exitProgram());

    board.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        f.handleCellClick(e.getX(), e.getY());
      }
    });

    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 83) {
          f.shootArrow();
        } else if (code == 80) {
          f.pickUp();
        } else if (code == 37) {
          f.move(Direction.WEST);
        } else if (code == 38) {
          f.move(Direction.NORTH);
        } else if (code == 39) {
          f.move(Direction.EAST);
        } else if (code == 40) {
          f.move(Direction.SOUTH);
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
  }

  @Override
  public void showErrorMessage(String error) {
    System.out.println("Error: " + error);
    JOptionPane.showMessageDialog(this,error,"Error",JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}