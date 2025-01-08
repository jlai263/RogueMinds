package main;

import entities.Entity;
/**
 * Handles collision detection for entities within the game world, evaluating both tile and object collisions.
 * <p>
 * This class checks for collisions against the game's map tiles and other entities, adjusting the entity's state based on the results. 
 * It's used primarily to prevent entities from moving through solid objects or tiles and to trigger interactions with collectible or interactive objects.
 * 
 * @author Gurshaan Gill
 * @version 1.2
 * @since 1.0
 */
public class CollisionChecker {
    GamePanel gp;
    /**
     * Constructs a CollisionChecker with a reference to the GamePanel.
     * 
     * @param gp The game panel which contains tile and object data necessary for collision detection.
     */
    public CollisionChecker (GamePanel gp){
        this.gp = gp;
    }
      /**
     * Checks for collisions between the specified entity and map tiles.
     * <p>
     * This method calculates the entity's projected position based on its current direction and speed,
     * then checks if the new position would result in a collision with a solid map tile.
     * 
     * @param entity The entity for which to check tile collisions.
     */
    public void checkTile (Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

        }
    }
    /**
     * Checks for collisions between the specified entity and other objects in the game world.
     * <p>
     * This method determines if the entity's projected movement results in an overlap with any interactive or solid objects.
     * If a collision is detected with an object, and the entity is the player, the index of the object is returned for further processing.
     * 
     * @param entity The entity for which to check object collisions.
     * @param player Indicates whether the entity is the player, affecting collision processing.
     * @return The index of the collided object if the entity is the player; otherwise, returns 999.
     */
    public int checkObject (Entity entity, boolean player){
        int index = 999;

        for ( int i = 0; i < gp.obj.length; i++){

            if (gp.obj[i] != null){
                // Getting entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Getting object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision == true) entity.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision == true) entity.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision == true) entity.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;

                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision == true) entity.collisionOn = true;
                            if(player == true) index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
