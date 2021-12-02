package dungeon.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dungeon.controller.Features;
import dungeon.model.ROtyughDungeon;

public class DungeonSwingView extends JFrame implements DungeonView {
  private JPanel container;
  private JMenuBar menuBar;
  private JPanel board;
  private JTextField rows;
  private JTextField columns;
  private JTextField interconnectivity;
  private JTextField percentTreasures;
  private JTextField percentArrows;
  private JTextField numberMonsters;
  private JButton enterButton;
  private JButton quitButton;
  private JComboBox<String> wrapped;

  public DungeonSwingView(ROtyughDungeon model) {
    super("Otyugh Dungeon Menu");
    this.setSize(1100,1000);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

    // ADD CONTAINER AND SCROLL PANE
    container = new JPanel();
    container.setLayout(new FlowLayout());
    JScrollPane scrollPane = new JScrollPane(container);
    this.add(scrollPane);

    // ADD BOARD TO SCROLL PANE
    board = new BoardPanel(model);
    board.setPreferredSize(new Dimension(1000,1000)); // Allows scrolling
    container.add(board);

    pack();
  }


  @Override
  public void refresh() {
    repaint();
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
        if (e.getKeyChar() == 's') {
          f.shootArrow();
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 's') {
          f.shootArrow();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 's') {
          f.shootArrow();
        }
      }
    });
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

}