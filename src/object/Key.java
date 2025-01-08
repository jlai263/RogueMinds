package object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a key object in the game, extending the {@code SuperObject} class.
 * <p>
 * This class is responsible for initializing key objects with their specific properties,
 * such as their name and image. The image is loaded from the game's resources, providing
 * a visual representation of the key within the game environment.
 * 
 * @author Gurshaan Gill
 * @version 1.3
 * @since 1.0
 */

public class Key extends SuperObject{
      /**
     * Constructor for the Key class. Sets the name of the key object and attempts to
     * load its image from the resources.
     */
    public Key(){
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
