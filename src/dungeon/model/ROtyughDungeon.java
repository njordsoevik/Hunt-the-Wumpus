package dungeon.model;

import java.util.List;

public interface ROtyughDungeon {

    /**
     * Get the arrows that the player is currently holding.
     *
     * @return the list of arrows.
     */
    List<Arrow> getPlayerArrows();

    /**
     * Get the arrows placed at the current location.
     *
     * @return the arrow in the player's location.
     */
    List<Arrow> getCurrentLocationArrows();

    /**
     * Get player health.
     *
     * @return the players health.
     */
    Health getPlayerHealth();

    /**
     * Get smell of nearby Otyughs. Detecting a more pungent smell either means that there is a
     * single Otyugh 1 position from the player's current location or that there are multiple
     * Otyughs within 2 positions from the player's current location.
     *
     * @return the smell of nearby Otyughs.
     */
    Smell getSmell();
}
