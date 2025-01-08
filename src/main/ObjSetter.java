package main;

import object.Chest;
import object.Door;
import object.Key;
import object.EnemyOne;
import object.EnemyTwo;
import object.EnemyThree;
import object.EnemyFour;
import object.NpcOne;
import object.NpcTwo;
import object.NpcThree;
import object.NpcFour;

/**
 * Initializes and positions interactable objects within the game world.
 * <p>
 * This class is responsible for setting up the game objects by placing them at specific locations in the game world.
 * It directly manipulates the game panel's object array to populate it with instances of keys, doors, and chests,
 * placing them at predetermined coordinates.
 * 
 * @author Gurshaan Gill
 * @version 1.3
 * @since 1.0
 */
public class ObjSetter {
    GamePanel gp;
  
      /**
     * Constructs an ObjSetter with a reference to the GamePanel.
     * 
     * @param gp The game panel which this ObjSetter will use to place objects.
     */
    public ObjSetter(GamePanel gp) {
        this.gp = gp;
    }
  
    /**
     * Populates the game world with interactable objects at predefined locations.
     * 
     * This method initializes various game objects, such as keys, doors, chests, enemies, and npcs, and sets their
     * position within the game world based on tile coordinates. The method directly assigns these
     * objects to the game panel's object array, making them part of the game environment.
     */
    public void setObject(){
        gp.obj[0]= new Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1]= new Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2]= new Key();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3]= new Door();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4]= new Door();
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5]= new Door();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6]= new Chest();
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;

        gp.obj[7] = new EnemyOne();
        gp.obj[7].worldX = 23 * gp.tileSize;
        gp.obj[7].worldY = 10 * gp.tileSize;

        gp.obj[8] = new EnemyTwo();
        gp.obj[8].worldX = 23 * gp.tileSize;
        gp.obj[8].worldY = 35 * gp.tileSize;

        gp.obj[9] = new EnemyThree();
        gp.obj[9].worldX = 38 * gp.tileSize;
        gp.obj[9].worldY = 13 * gp.tileSize;

        gp.obj[10] = new EnemyFour();
        gp.obj[10].worldX = 14 * gp.tileSize;
        gp.obj[10].worldY = 21 * gp.tileSize;

        gp.obj[11] = new NpcOne();
        gp.obj[11].worldX = 25 * gp.tileSize;
        gp.obj[11].worldY = 19 * gp.tileSize;

        gp.obj[12] = new NpcTwo();
        gp.obj[12].worldX = 38 * gp.tileSize;
        gp.obj[12].worldY = 36 * gp.tileSize;

        gp.obj[13] = new NpcThree();
        gp.obj[13].worldX = 35 * gp.tileSize;
        gp.obj[13].worldY = 27 * gp.tileSize;

        gp.obj[14] = new NpcFour();
        gp.obj[14].worldX = 9 * gp.tileSize;
        gp.obj[14].worldY = 29 * gp.tileSize;
    }

}
