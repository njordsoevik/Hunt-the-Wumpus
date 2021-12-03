package dungeon.controller;

import dungeon.model.Direction;
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
        view.setFeatures(this);
        view.makeVisible();
        view.resetFocus();
    }

    @Override
    public void handleCellClick(int row, int col) {
        System.out.println(row);
        System.out.println(col);
        view.resetFocus();
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
            view.setBoard(Integer.parseInt(rows),Integer.parseInt(columns)
                    ,model);
            updateView();
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: "+ ex);
        }
    }

    @Override
    public void shootArrow() {
        System.out.println("Shoot");
    }

    @Override
    public void move(Direction d) {
        try {
            execute(new Move(d));
            updateView();
        } catch (IllegalArgumentException ex) {
            view.showErrorMessage(ex.toString());
        }
    }

    @Override
    public void pickUp() {
        try {
            execute(new PickUp());
            updateView();
        } catch (IllegalArgumentException ex) {
            view.showErrorMessage(ex.toString());
        }
    }

    @Override
    public void exitProgram() {
        System.exit(0);
    }

    private void execute(OtyughDungeonCommand cmd) {
        cmd.execute(model);
    }

    private void updateView() {
        view.refresh();
        view.resetFocus();
    }
}
