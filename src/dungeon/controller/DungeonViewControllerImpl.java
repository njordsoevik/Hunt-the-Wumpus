package dungeon.controller;

import dungeon.model.OtyughDungeon;
import dungeon.view.DungeonView;

public class DungeonViewControllerImpl implements DungeonViewController {
    private OtyughDungeon m;
    private DungeonView v;

    public DungeonViewControllerImpl(DungeonView v) {
        this.v = v;
        v.makeVisible();
    }

    @Override
    public void playGame(OtyughDungeon m) {
        this.v.addClickListener(this);
    }

    @Override
    public void createMenu() {
        this.v.addClickListener(this);
    }


    @Override
    public void handleCellClick(int row, int col) {
            System.out.println(row);
        System.out.println(col);
            v.refresh();
    }




}
