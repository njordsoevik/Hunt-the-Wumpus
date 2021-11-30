package dungeon.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dungeon.controller.DungeonController;
import dungeon.controller.DungeonViewController;
import dungeon.model.OtyughDungeon;

public class DungeonSwingView extends JFrame implements DungeonView {

    private BoardPanel boardPanel;

    public DungeonSwingView() {
        super("Otyugh Dungeon");
        this.setSize(600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boardPanel = new BoardPanel();
        add(boardPanel);
    }

    @Override
    public void addClickListener(DungeonViewController listener) {
        // create the MouseAdapter
        MouseAdapter clickAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listener.handleCellClick(e.getX(), e.getY());
            }
        };
        boardPanel.addMouseListener(clickAdapter);
    }

    @Override
    public void refresh() {
        repaint();
    }

    @Override
    public void makeVisible() {
        setVisible(true);
    }
}
