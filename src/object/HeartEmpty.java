package object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents an Empty Heart object in the game, extending the {@code SuperObject} class.
 * <p>
 * This class is responsible for the heart Sprite which will show the player how many
 * lives the have remaining.
 * 
 * @author Justin Lai
 * @version 1.0
 * @since 1.0
 */

public class HeartEmpty extends SuperObject{
      /**
     * Constructor for the Heart class. Sets the name of the heart object and attempts to
     * load its image from the resources.
     */
    public HeartEmpty(){
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/heartEmpty.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
