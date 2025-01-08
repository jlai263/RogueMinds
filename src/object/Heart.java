package object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a Heart object in the game, extending the {@code SuperObject} class.
 * <p>
 * This class is responsible for the heart Sprite which will show the player how many
 * lives the have remaining.
 * 
 * @author Justin Lai
 * @version 1.0
 * @since 1.0
 */

public class Heart extends SuperObject{
      /**
     * Constructor for the Heart class. Sets the name of the heart object and attempts to
     * load its image from the resources.
     */
    public Heart(){
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/heartFull.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
