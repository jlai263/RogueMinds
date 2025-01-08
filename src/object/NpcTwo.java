package object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents am enemy object in the game, extending the {@code SuperObject} class.
 * <p>
 * This class is responsible for initializing the enemy object with its specific properties,
 * such as its name and image. The image is loaded from the game's resources.
 * 
 * @author Darren Chen
 * @version 1.0
 * @since 1.0
 */

public class NpcTwo extends SuperObject {
    
      /**
     * Constructor for {@code NpcTwo}. Sets the name of the enemy and attempts to load its image
     * from the resources.
     */
    public NpcTwo(){
        name = "npcTwo";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/npc/npc2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}