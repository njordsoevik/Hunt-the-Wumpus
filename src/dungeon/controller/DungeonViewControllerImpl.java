package dungeon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
import dungeon.view.DungeonView;

public class DungeonViewControllerImpl implements DungeonViewController, Features {
    private OtyughDungeon model;
    private DungeonView view;

    @Override
    public void playGame(OtyughDungeon m) {
        this.view.addListener(this);
    }

    @Override
    public void handleCellClick(int row, int col) {
        System.out.println(row);
        System.out.println(col);
    }

    @Override
    public void setView(DungeonView view) {
        this.view = view;
        this.view.setFeatures(this);
    }


    @Override
    public void processInput(String rows, String columns, String connectivity, String wrapped, String treasure, String arrows, String monsters) {
        try {
            OtyughDungeon model = new OtyughTreasureDungeon(rows,columns,connectivity,wrapped, treasure,arrows,monsters);
        }
        System.out.println(rows);
        System.out.println(columns);
        System.out.println(connectivity);
        System.out.println(wrapped);
    }

    @Override
    public void exitProgram() {
        System.exit(0);
    }
}
