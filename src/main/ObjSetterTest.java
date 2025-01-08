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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author          Darren Chen
 * @version         1.0
 * @since           1.0
 * This class contains JUnit tests for the ObjSetter class.
 * <p>
 * It includes test cases for setting up game objects in the game world, ensuring that the objects
 * are correctly initialized and positioned.
 */
class ObjSetterTest {

    @Test
    void testSetObject() {
        // Test setObject method of ObjSetter class
        // This test verifies that the setObject method populates the game world with objects
        // It checks if each object is not null and if its position is set correctly
        
        // Create a game panel
        GamePanel gamePanel = new GamePanel();
        
        // Create an ObjSetter object
        ObjSetter objSetter = new ObjSetter(gamePanel);
        
        // Call the setObject method to populate objects
        objSetter.setObject();
        
        // Test each object type
        for (int i = 0; i < gamePanel.obj.length; i++) {
            assertNotNull(gamePanel.obj[i]); // Check if object is not null
            switch (i) {
                case 0: 
                case 1:
                case 2:
                    assertTrue(gamePanel.obj[i] instanceof Key); // Check if object is instance of Key
                    assertEquals(23 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertTrue(gamePanel.obj[i].worldY == 7 * gamePanel.tileSize || 
                               gamePanel.obj[i].worldY == 40 * gamePanel.tileSize); // Check Y position
                    break;
                case 3:
                case 4:
                case 5:
                    assertTrue(gamePanel.obj[i] instanceof Door); // Check if object is instance of Door
                    assertTrue(gamePanel.obj[i].worldX == 10 * gamePanel.tileSize || 
                               gamePanel.obj[i].worldX == 8 * gamePanel.tileSize || 
                               gamePanel.obj[i].worldX == 12 * gamePanel.tileSize); // Check X position
                    assertTrue(gamePanel.obj[i].worldY == 11 * gamePanel.tileSize || 
                               gamePanel.obj[i].worldY == 28 * gamePanel.tileSize || 
                               gamePanel.obj[i].worldY == 22 * gamePanel.tileSize); // Check Y position
                    break;
                case 6:
                    assertTrue(gamePanel.obj[i] instanceof Chest); // Check if object is instance of Chest
                    assertEquals(10 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(7 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 7:
                    assertTrue(gamePanel.obj[i] instanceof EnemyOne); // Check if object is instance of EnemyOne
                    assertEquals(23 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(10 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 8:
                    assertTrue(gamePanel.obj[i] instanceof EnemyTwo); // Check if object is instance of EnemyTwo
                    assertEquals(23 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(35 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 9:
                    assertTrue(gamePanel.obj[i] instanceof EnemyThree); // Check if object is instance of EnemyThree
                    assertEquals(38 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(13 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 10:
                    assertTrue(gamePanel.obj[i] instanceof EnemyFour); // Check if object is instance of EnemyFour
                    assertEquals(14 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(21 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 11:
                    assertTrue(gamePanel.obj[i] instanceof NpcOne); // Check if object is instance of NpcOne
                    assertEquals(25 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(19 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 12:
                    assertTrue(gamePanel.obj[i] instanceof NpcTwo); // Check if object is instance of NpcTwo
                    assertEquals(38 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(36 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 13:
                    assertTrue(gamePanel.obj[i] instanceof NpcThree); // Check if object is instance of NpcThree
                    assertEquals(35 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(27 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                case 14:
                    assertTrue(gamePanel.obj[i] instanceof NpcFour); // Check if object is instance of NpcFour
                    assertEquals(9 * gamePanel.tileSize, gamePanel.obj[i].worldX); // Check X position
                    assertEquals(29 * gamePanel.tileSize, gamePanel.obj[i].worldY); // Check Y position
                    break;
                default:
                    fail("Invalid object type");
                    break;
            }
        }
    }
}
