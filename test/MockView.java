import dungeon.controller.Features;
import dungeon.model.RDungeon;
import dungeon.view.DungeonView;

/**
 * A mock to simulate a view to check controller input to view.
 */
public class MockView implements DungeonView {
  public StringBuilder methodCalled;

  public MockView() {
    methodCalled = new StringBuilder();
  }

  @Override
  public void resetFocus() {
    methodCalled.append("resetFocus");
  }

  @Override
  public void refresh() {
    methodCalled.append("refresh");
  }

  @Override
  public void makeVisible() {
    methodCalled.append("makeVisible");
  }

  @Override
  public void setFeatures(Features f) {
    methodCalled.append("setFeatures");
  }

  @Override
  public void showErrorMessage(String error) {
    methodCalled.append("showErrorMessage");
  }

  @Override
  public void updateModel(int x, int y, RDungeon model) {
    methodCalled.append("updateModel");
  }
}
