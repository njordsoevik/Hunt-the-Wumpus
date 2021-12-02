package dungeon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
import dungeon.view.DungeonView;

public class DungeonViewControllerImpl implements DungeonViewController, Features {
    private OtyughDungeon model;
    private DungeonView view;

    public DungeonViewControllerImpl(OtyughDungeon m, DungeonView v) {
        this.model = m;
        this.view = v;
    }

    @Override
    public void go() {
        this.view.setFeatures(this);
        this.view.makeVisible();
    }

    @Override
    public void handleCellClick(int row, int col) {
        System.out.println(row);
        System.out.println(col);
    }

    @Override
    public void processInput(String rows, String columns, String connectivity, String wrapped
            , String treasure, String arrows, String monsters) {
        OtyughDungeon model;
        try {
            model = new OtyughTreasureDungeon(Integer.parseInt(rows)
                    ,Integer.parseInt(columns),Integer.parseInt(connectivity)
                    ,Boolean.parseBoolean(wrapped), Integer.parseInt(treasure)
                    ,Integer.parseInt(arrows),Integer.parseInt(monsters));
            System.out.println(model);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: "+ ex);
        }

    }

    @Override
    public void shootArrow() {
        System.out.println("shoot");
    }

    @Override
    public void exitProgram() {
        System.exit(0);
    }
}
