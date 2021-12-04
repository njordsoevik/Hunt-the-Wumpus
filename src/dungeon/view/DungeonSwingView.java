package dungeon.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import dungeon.controller.Features;
import dungeon.model.Arrow;
import dungeon.model.Direction;
import dungeon.model.RDungeon;
import dungeon.model.RLocation;
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
  private JLabel healthLabel;
  private JLabel playerArrowLabel;
  private JLabel playerTreasureLabel;
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
  private final int SCALE_X = 100;
  private final int SCALE_Y = 100;

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

    // ADD TOP BAR
    JPanel topBar = new JPanel();
    topBar.setLayout(new BorderLayout());
    topBar.setBackground(Color.BLACK);

    // ADD INSTRUCTIONS
    JPanel instructions = new JPanel();
    instructions.setPreferredSize(new Dimension(300,150));
    instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));
    instructions.setBackground(Color.BLACK);
    TitledBorder title = new TitledBorder("Instructions");
    title.setTitleColor(Color.WHITE);
    instructions.setBorder(title);
    JLabel moveInstructions = new JLabel("Move: Arrow keys OR clicking adjacent" +
            " cells");
    moveInstructions.setForeground(Color.white);
    JLabel shootInstructions = new JLabel("Shoot: Press S + ARROW KEY + DISTANCE");
    shootInstructions.setForeground(Color.white);
    instructions.add(moveInstructions);
    instructions.add(shootInstructions);

    // ADD PLAYER INFO
    JPanel playerInfo = new JPanel();
    playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
    playerInfo.setBackground(Color.BLACK);
    playerInfo.setPreferredSize(new Dimension(200,150));
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
    infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBackground(Color.black);
    infoPanel.setPreferredSize(new Dimension(300,150));
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
    setBoard(5,5,model); // TODO this cause issues , maybe now fixed

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
    updateInfoPanels();
    repaint();
  }

  private void updateInfoPanels() {
    // ADD DIRECTIONS
    StringBuilder sb = new StringBuilder();
    for (Direction p: model.getDirections()) {
      sb.append(p).append(", ");
    }
    directionsLabel.setText(sb.toString());

    // ADD SMELL
    smellLabel.setText(model.getSmell().toString());

    // ADD PLAYER TREASURES
    List<Treasure> treasuresPlayer = model.getPlayerTreasure();
    if (treasuresPlayer.isEmpty()){
      treasureLabel.setText("NONE");
    } else {
      treasureLabel.setText(treasuresPlayer.toString());
    }

    // ADD TREASURES
    List<Treasure> treasures = model.getCurrentLocationTreasure();
    if (treasures.isEmpty()){
      treasureLabel.setText("NONE");
    } else {
      treasureLabel.setText(labelTreasures(treasures));
    }

    // ADD ARROWS
    List<Arrow> arrows = model.getCurrentLocationArrows();
    if (arrows.isEmpty()){
      arrowLabel.setText("0");
    } else {
      arrowLabel.setText(String.valueOf(arrows.size()));
    }

    // ADD PLAYER HEALTH
    healthLabel.setText(model.getPlayerHealth().toString());

    // ADD PLAYER TREASURE
    List<Treasure> playerTreasure = model.getPlayerTreasure();
    if (playerTreasure.isEmpty()){
      playerTreasureLabel.setText("NONE");
    } else {
      playerTreasureLabel.setText(labelTreasures(playerTreasure));
    }

    // ADD PLAYER ARROWS
    List<Arrow> playerArrows = model.getPlayerArrows();
    if (playerArrows.isEmpty()){
      playerArrowLabel.setText("0");
    } else {
      playerArrowLabel.setText(String.valueOf(playerArrows.size()));
    }
  }

  private String labelTreasures(List<Treasure> treasures) {
    StringBuilder sb = new StringBuilder();
    int s = 0;
    int r = 0;
    int d = 0;
    for (Treasure t:treasures) {
      if (t==Treasure.RUBY) {
        r++;
      } else if (t==Treasure.DIAMOND) {
        d++;
      } else if (t==Treasure.SAPPHIRE) {
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

    enterButton.addActionListener(l -> f.processInput(rows.getText(), columns.getText()
            , interconnectivity.getText(), (String) wrapped.getSelectedItem()
            , percentArrows.getText(), percentTreasures.getText(), numberMonsters.getText()));

    quitButton.addActionListener(l -> f.exitProgram());

    board.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        RLocation[][] locations = model.getVisitedLocations();
        int currentX = model.getCurrentCoordinate().getJ() * SCALE_X / (locations[0].length);
        int currentY = model.getCurrentCoordinate().getI() * SCALE_Y / (locations.length);
        int scaleClickX = e.getX()/ locations[0].length;
        int scaleClickY = e.getY()/ locations.length;
        System.out.println(currentX + " " + currentY + "Current");
        System.out.println(scaleClickX + " " + scaleClickY);
        if (scaleClickX>(currentX+8) && scaleClickX<(currentX+23)) {
          //f.move(Direction.EAST);
        }
        //f.handleCellClick(currentX, currentY);
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