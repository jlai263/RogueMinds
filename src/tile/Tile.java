package tile;

import java.awt.image.BufferedImage;
/**
 * Represents a single tile in the game's environment.
 * <p>
 * This class encapsulates the data for individual tiles used to construct the game's maps or levels. Each tile
 * has an image representing its appearance and a collision flag indicating whether it blocks movement, thereby
 * affecting how entities interact with the game world.
 * @author Gurshaan Gill
 * @version 1.0
 * @since 1.0
 */
public class Tile {
    public BufferedImage image;
    public Boolean collision = false;
}
