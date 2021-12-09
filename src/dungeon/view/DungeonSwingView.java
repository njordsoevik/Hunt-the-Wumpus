package dungeon.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import dungeon.controller.Features;
import dungeon.model.Arrow;
import dungeon.model.Direction;
import dungeon.model.RDungeon;
import dungeon.model.RLocation;
import dungeon.model.Treasure;

/**
 * The view for the dungeon graphical user interface (GUI). Players will interact with
 * this GUI to generate and play games.
 */
public class DungeonSwingView extends JFrame implements DungeonView {
  private static final int SCALE_X = 100;
  private static final int SCALE_Y = 100;
  private final JPanel container;
  private final JLabel directionsLabel;
  private final JLabel smellLabel;
  private final JLabel treasureLabel;
  private final JLabel arrowLabel;
  private final JLabel healthLabel;
  private final JLabel playerArrowLabel;
  private final JLabel playerTreasureLabel;
  private final JTextField rows;
  private final JTextField columns;
  private final JTextField interconnectivity;
  private final JTextField percentTreasures;
  private final JTextField percentArrows;
  private final JTextField numberMonsters;
  private final JButton enterButton;
  private final JButton restartButton;
  private final JButton quitButton;
  private final JComboBox<String> wrapped;
  private RDungeon model;
  private BoardPanel board;
  private boolean controlModifierPressed;

  /**
   * The constructor for the dungeon view that displays the menu, game board and information for
   * playing the game.
   */
  public DungeonSwingView(RDungeon m) {
    super("Otyugh Dungeon");
    if (!(m instanceof RDungeon)){
      throw new IllegalArgumentException("Invalid model");
    }
    this.model = m;
    this.controlModifierPressed = false;
    this.setSize(900, 800);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // ADD MENU
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(new JSeparator());
    menuBar.add(new JLabel("Rows"));
    rows = new JTextField("5");
    menuBar.add(rows);
    menuBar.add(new JLabel("Columns"));
    columns = new JTextField("5");
    menuBar.add(columns);
    menuBar.add(new JLabel("Interconnectivity"));
    interconnectivity = new JTextField("0");
    menuBar.add(interconnectivity);
    menuBar.add(new JLabel("Treasures (%)"));
    percentTreasures = new JTextField("150");
    menuBar.add(percentTreasures);
    menuBar.add(new JLabel("Arrows (%)"));
    percentArrows = new JTextField("50");
    menuBar.add(percentArrows);
    menuBar.add(new JLabel("Monsters"));
    numberMonsters = new JTextField("1");
    menuBar.add(numberMonsters);
    menuBar.add(new JLabel("Wrapped"));
    wrapped = new JComboBox(new String[]{"False", "True"});
    menuBar.add(wrapped);
    enterButton = new JButton("Generate");
    enterButton.setActionCommand("Enter Button");
    menuBar.add(enterButton);
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");
    menuBar.add(restartButton);
    quitButton = new JButton("Quit");
    menuBar.add(quitButton);
    this.setJMenuBar(menuBar);

    // ADD TOP BAR
    JPanel topBar = new JPanel();
    topBar.setLayout(new BorderLayout());
    topBar.setBackground(Color.BLACK);

    // ADD INSTRUCTIONS
    JPanel instructions = new JPanel();
    instructions.setPreferredSize(new Dimension(300, 150));
    instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));
    instructions.setBackground(Color.BLACK);
    TitledBorder title = new TitledBorder("Instructions");
    title.setTitleColor(Color.WHITE);
    instructions.setBorder(title);
    JLabel moveInstructions = new JLabel("Move: ARROW KEY or CLICK cells");
    moveInstructions.setForeground(Color.white);
    JLabel shootInstructions = new JLabel("Shoot: Hold CONTROL + ARROW KEY");
    shootInstructions.setForeground(Color.white);
    JLabel pickInstructions = new JLabel("Pick Up: P");
    pickInstructions.setForeground(Color.white);
    instructions.add(moveInstructions);
    instructions.add(shootInstructions);
    instructions.add(pickInstructions);

    // ADD PLAYER INFO
    JPanel playerInfo = new JPanel();
    playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
    playerInfo.setBackground(Color.BLACK);
    playerInfo.setPreferredSize(new Dimension(200, 150));
    TitledBorder playerTitle = new TitledBorder("Player");
    playerTitle.setTitleColor(Color.WHITE);
    playerInfo.setBorder(playerTitle);

    JPanel playerHealthPanel = new JPanel();
    playerHealthPanel.setBackground(Color.BLACK);
    JLabel healthText = new JLabel("Health: ");
    healthText.setForeground(Color.ORANGE);
    healthLabel = new JLabel("");
    healthLabel.setForeground(Color.white);
    playerHealthPanel.add(healthText);
    playerHealthPanel.add(healthLabel);

    JPanel playerTreasurePanel = new JPanel();
    playerTreasurePanel.setBackground(Color.BLACK);
    JLabel treasureTextPlayer = new JLabel("Treasure: ");
    treasureTextPlayer.setForeground(Color.ORANGE);
    playerTreasureLabel = new JLabel("");
    playerTreasureLabel.setForeground(Color.white);
    playerTreasurePanel.add(treasureTextPlayer);
    playerTreasurePanel.add(playerTreasureLabel);

    JPanel playerArrowPanel = new JPanel();
    playerArrowPanel.setBackground(Color.BLACK);
    JLabel arrowTextPlayer = new JLabel("Arrows: ");
    arrowTextPlayer.setForeground(Color.ORANGE);
    playerArrowLabel = new JLabel("");
    playerArrowLabel.setForeground(Color.white);
    playerArrowPanel.add(arrowTextPlayer);
    playerArrowPanel.add(playerArrowLabel);

    playerInfo.add(playerHealthPanel);
    playerInfo.add(playerTreasurePanel);
    playerInfo.add(playerArrowPanel);

    // ADD INFO BAR
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBackground(Color.black);
    infoPanel.setPreferredSize(new Dimension(300, 150));
    TitledBorder titleInfo = new TitledBorder("Game Information");
    titleInfo.setTitleColor(Color.WHITE);
    infoPanel.setBorder(titleInfo);

    JPanel directionsPanel = new JPanel();
    directionsPanel.setBackground(Color.BLACK);
    JLabel directionsText = new JLabel("Directions: ");
    directionsText.setForeground(Color.ORANGE);
    directionsLabel = new JLabel("");
    directionsLabel.setForeground(Color.WHITE);
    directionsPanel.add(directionsText);
    directionsPanel.add(directionsLabel);

    JPanel smellPanel = new JPanel();
    smellPanel.setBackground(Color.BLACK);
    JLabel smellText = new JLabel("Smell: ");
    smellText.setForeground(Color.GREEN);
    smellLabel = new JLabel("");
    smellLabel.setForeground(Color.WHITE);
    smellPanel.add(smellText);
    smellPanel.add(smellLabel);

    JPanel treasurePanel = new JPanel();
    treasurePanel.setBackground(Color.BLACK);
    JLabel treasureText = new JLabel("Treasure: ");
    treasureText.setForeground(Color.YELLOW);
    treasureLabel = new JLabel("");
    treasureLabel.setForeground(Color.WHITE);
    treasurePanel.add(treasureText);
    treasurePanel.add(treasureLabel);

    JPanel arrowPanel = new JPanel();
    arrowPanel.setBackground(Color.BLACK);
    JLabel arrowText = new JLabel("Arrows: ");
    arrowText.setForeground(Color.CYAN);
    arrowLabel = new JLabel("");
    arrowLabel.setForeground(Color.WHITE);
    arrowPanel.add(arrowText);
    arrowPanel.add(arrowLabel);

    infoPanel.add(directionsPanel);
    infoPanel.add(smellPanel);
    infoPanel.add(treasurePanel);
    infoPanel.add(arrowPanel);

    updateInfoPanels();

    JPanel topRightInfo = new JPanel();
    topRightInfo.add(instructions);
    topRightInfo.add(playerInfo);
    topRightInfo.add(infoPanel);
    topRightInfo.setBackground(Color.BLACK);
    topBar.add(topRightInfo, BorderLayout.NORTH);
    this.add(topBar, BorderLayout.NORTH);
    //this.add(infoPanel, BorderLayout.NORTH);

    // ADD CONTAINER AND SCROLL PANE
    container = new JPanel();
    container.setBackground(Color.BLACK);
    container.setLayout(new FlowLayout());
    JScrollPane scrollPane = new JScrollPane(container);
    // ADD BOARD TO SCROLL PANE
    setBoard(5, 5, model);

    this.add(scrollPane);
  }

  @Override
  public void updateModel(int x, int y, RDungeon model) {
    this.model = model;
    setBoard(x, y, model);
    updateInfoPanels();
  }


  private void setBoard(int x, int y, RDungeon model) {
    if (board != null) {
      container.remove(board);
      this.board.setModel(new Dimension(x, y), model);
    } else {
      board = new BoardPanel(new Dimension(x, y), model, SCALE_X, SCALE_Y);
    }
    this.board.setPreferredSize(new Dimension(y * SCALE_Y, x * SCALE_X));
    container.add(board);
  }


  private void updateInfoPanels() {
    // ADD DIRECTIONS
    StringBuilder sb = new StringBuilder();
    for (Direction p : model.getDirections()) {
      sb.append(p).append(", ");
    }
    directionsLabel.setText(sb.toString());

    // ADD SMELL
    smellLabel.setText(model.getSmell().toString());

    // ADD PLAYER TREASURES
    List<Treasure> treasuresPlayer = model.getPlayerTreasure();
    if (treasuresPlayer.isEmpty()) {
      treasureLabel.setText("NONE");
    } else {
      treasureLabel.setText(treasuresPlayer.toString());
    }

    // ADD TREASURES
    List<Treasure> treasures = model.getCurrentLocationTreasure();
    if (treasures.isEmpty()) {
      treasureLabel.setText("NONE");
    } else {
      treasureLabel.setText(labelTreasures(treasures));
    }

    // ADD ARROWS
    List<Arrow> arrows = model.getCurrentLocationArrows();
    if (arrows.isEmpty()) {
      arrowLabel.setText("0");
    } else {
      arrowLabel.setText(String.valueOf(arrows.size()));
    }

    // ADD PLAYER HEALTH
    healthLabel.setText(model.getPlayerHealth().toString());

    // ADD PLAYER TREASURE
    List<Treasure> playerTreasure = model.getPlayerTreasure();
    if (playerTreasure.isEmpty()) {
      playerTreasureLabel.setText("NONE");
    } else {
      playerTreasureLabel.setText(labelTreasures(playerTreasure));
    }

    // ADD PLAYER ARROWS
    List<Arrow> playerArrows = model.getPlayerArrows();
    if (playerArrows.isEmpty()) {
      playerArrowLabel.setText("0");
    } else {
      playerArrowLabel.setText(String.valueOf(playerArrows.size()));
    }
  }

  @Override
  public void refresh() {
    validate();
    updateInfoPanels();
    repaint();
  }


  private String labelTreasures(List<Treasure> treasures) {
    StringBuilder sb = new StringBuilder();
    int s = 0;
    int r = 0;
    int d = 0;
    for (Treasure t : treasures) {
      if (t == Treasure.RUBY) {
        r++;
      } else if (t == Treasure.DIAMOND) {
        d++;
      } else if (t == Treasure.SAPPHIRE) {
        s++;
      }
    }
    sb.append("R: ").append(r).append(", S: ").append(s)
            .append(", D: ").append(d);
    return sb.toString();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void setFeatures(Features f) {

    enterButton.addActionListener(l -> f.processInput(rows.getText(), columns.getText(),
            interconnectivity.getText(), (String) wrapped.getSelectedItem(),
            percentTreasures.getText(), percentArrows.getText(), numberMonsters.getText()));

    restartButton.addActionListener(l -> f.restartProgram());
    quitButton.addActionListener(l -> f.exitProgram());

    board.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        RLocation[][] locations = model.getVisitedLocations();
        int currentX = model.getCurrentCoordinate().getJ() * SCALE_X / (locations[0].length);
        int currentY = model.getCurrentCoordinate().getI() * SCALE_Y / (locations.length);
        int lengthCellX = SCALE_X / (locations[0].length);
        int lengthCellY = SCALE_Y / (locations.length);
        int scaleClickX = e.getX() / locations[0].length;
        int scaleClickY = e.getY() / locations.length;
        if (scaleClickX > (currentX + lengthCellX)
                && scaleClickX < (currentX + (3 * lengthCellX))) {
          f.move(Direction.EAST);
        } else if (scaleClickY > (currentY + lengthCellY)
                && scaleClickY < (currentY + (3 * lengthCellY))) {
          f.move(Direction.SOUTH);
        } else if (scaleClickX < (currentX) && scaleClickX > (currentX - (3 * lengthCellX))) {
          f.move(Direction.WEST);
        } else if (scaleClickY < (currentY) && scaleClickY > (currentY - (3 * lengthCellY))) {
          f.move(Direction.NORTH);
        }
      }
    });

    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // keyPressed handles all user key input
      }

      @Override
      public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        Direction direction = null;
        String result = null;
        if (code == 17) {
          controlModifierPressed = true;
        } else if (code == 80) {
          f.pickUp();
        } else if (code == 37) {
          direction = Direction.WEST;
          if (controlModifierPressed) {
            result = JOptionPane.showInputDialog("Enter distance: ", "");
            controlModifierPressed = false;
          }
        } else if (code == 38) {
          direction = Direction.NORTH;
          if (controlModifierPressed) {
            result = JOptionPane.showInputDialog("Enter distance: ", "");
            controlModifierPressed = false;
          }
        } else if (code == 39) {
          direction = Direction.EAST;
          if (controlModifierPressed) {
            result = JOptionPane.showInputDialog("Enter distance: ", "");
            controlModifierPressed = false;
          }
        } else if (code == 40) {
          direction = Direction.SOUTH;
          if (controlModifierPressed) {
            result = JOptionPane.showInputDialog("Enter distance: ", "");
            controlModifierPressed = false;
          }
        }
        if (direction != null) {
          if (result != null) {
            try {
              int shootingDistance = Integer.parseInt(result);
              f.shootArrow(direction, shootingDistance);
            } catch (NumberFormatException nfe) {
              System.out.println("Cannot shoot " + result + " distance.");
            }
          } else {
            f.move(direction);
          }
        }

      }

      @Override
      public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 17) {
          controlModifierPressed = false;
        }
      }
    });
  }

  @Override
  public void showErrorMessage(String error) {
    System.out.println("Error: " + error);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}