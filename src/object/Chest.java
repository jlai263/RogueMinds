package object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a chest object in the game, extending the {@code SuperObject} class.
 * <p>
 * This class is responsible for initializing the chest object with its specific properties,
 * such as its name and image. The image is loaded from the game's resources.
 * 
 * @author Gurshaan Gill
 * @version 1.3
 * @since 1.0
 */
public class Chest extends SuperObject{
  
      /**
     * Constructor for {@code Chest}. Sets the name of the chest and attempts to load its image
     * from the resources.
     */
    public Chest(){
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
