package object;

import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Represents a door object in the game, extending the {@code SuperObject} class.
 * <p>
 * This class specifies door objects by setting their name, loading their visual representation from resources,
 * and marking them as solid objects that can potentially block the player's movement or require interaction to open.
 * 
 * @author Gurshaan Gill
 * @version 1.0
 * @since 1.0
 */
public class Door extends SuperObject{
    /**
     * Constructor for the Door class. Initializes the door with its name, attempts to load its image,
     * and sets its collision property to indicate it is a solid object.
     */
    public Door(){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
