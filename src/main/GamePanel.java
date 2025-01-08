package main;

import GameStart.Main;
import data.SaveLoad;
import entities.Player;
import object.SuperObject;
import tile.TileManager;
import GameStart.DebugMenu;
import GameStart.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * The main game panel, responsible for initializing and running the game loop, rendering game elements, and managing game state.
 * <p>
 * This class sets up the game window, initializes game objects, and handles the main game loop and rendering. It includes settings for
 * tile size, screen dimensions, and world dimensions, as well as references to key game components like the player, tiles, objects, and UI elements.
 * 
 * @author Gurshaan Gill
 * @version 1.2
 * @since 1.0
 */
public class GamePanel extends JPanel implements Runnable{

    // Creating Screen Settings
    public final int originalTileSize = 16; // 16 x 16 tile pixel
    public final int scale = 3; // tile multiplier
    public final int tileSize = originalTileSize * scale;// 48 x 48 pixel size
    public final int screenCols = 16;
    public final int screenRows = 12;

    public final int screenWidth = tileSize * screenCols; // Horizontal Length = 768 pixels
    public final int screenHeight = tileSize * screenRows; // Vertical Length = 576 pixels
    // Resolution = 768 x 576 //

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // Full Screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    final int FPS = 60;

    KeyHandler keyH = new KeyHandler(this);


    Sound music = new Sound();
    Sound se = new Sound();
    Thread gameThread; //Sets the Frames (fps)
    public CollisionChecker cChecker = new CollisionChecker(this);
    public ObjSetter oSetter = new ObjSetter(this);
    public UI ui = new UI(this);
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);
    public SuperObject obj[] = new SuperObject[20];
    public SaveLoad saveLoad = new SaveLoad(this);

    // Pause Feature
    public int gameState = 1;
    public final int playState = 1;
    public final int pauseState = 2;

    public DebugMenu debugMenu;
    private SoundManager soundManager;
   /**
     * Constructor for initializing the game panel.
     */
    public GamePanel(){

        boolean[][] volumeStates = new boolean[3][10]; // Example size, adjust according to your needs
        for (int i = 0; i < volumeStates.length; i++) {
            for (int j = 0; j < volumeStates[i].length; j++) {
                volumeStates[i][j] = true; // Set initial volume state, adjust as necessary
            }
        }
        soundManager = new SoundManager(volumeStates);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Sets true for offscreen painting buffers, improves distance rendering
        this.addKeyListener(keyH);
        this.setFocusable(true); // Panel to focus on keyboard inputs
        debugMenu = new DebugMenu(soundManager, this);
    }
    /**
     * Initializes game objects and starts background music.
     */
    public void setGameObj(){
        oSetter.setObject();
        playMusic(0);

        //setFullScreen();
    }

    public void setFullScreen(){
        // Get Local Screen Size
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(MathRogueQuest.window);

        // Full screen Width and Height
        screenWidth2 = MathRogueQuest.window.getWidth();
        screenHeight2 = MathRogueQuest.window.getHeight();
    }



    /**
     * Starts the game thread, kicking off the game loop.
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run() {

        double drawInterval = 1000000000/FPS;
        // Calculation:
        // 1 interval in 0.016 seconds, i.e. 60 intervals in 1 second
        // so in a second 60 intervals(frames), FPS = 60;
        double nextDrawTime = System.nanoTime() + drawInterval; //current time + 0.016 second interval

        while(gameThread != null){

            long currentTime= System.nanoTime();
            //Update the character position on each key (character movement)
            update();
            //Draw the screen again with the updated information
            repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // Time elapsed while running a single game thread
                remainingTime = remainingTime/1000000; //Converting remaining time to milliseconds
                if (remainingTime < 0) remainingTime = 0; //catching for system errors for bugs in the time loop
                Thread.sleep((long) remainingTime); //Sleep creates the illusion of frames and motion

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates the state of game entities.
     */
    protected void update(){
        if ( gameState == playState) {
            player.update();
        }
        if (gameState == pauseState){

        }
    }

    /**
     * This method is called by Swing during the repaint process and is responsible for drawing all visible game elements.
     * It utilizes Graphics2D for rendering, offering enhanced control over graphics operations. The method
     * iterates through all game objects and the player, invoking their respective draw methods. It ensures that the
     * UI is drawn last so that it overlays other game elements.
     *
     * @param g the Graphics object to project
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //Tile
        tileM.draw(g2);
        //Objects
        for (int i = 0; i < obj.length; i++){
            if(obj[i] != null) obj[i].draw(g2, this);
        }
        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose(); // Clear cache and saves memory
    }
  
    /**
     * Plays background music based on the specified track index.
     * 
     * @param i The index of the music track to play.
     */
  
    public void playMusic (int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
  
    /**
     * Stops the currently playing music.
     */
  
    public  void stopMusic(){music.stop();}
  
      /**
     * Plays a sound effect based on the specified sound effect index.
     * 
     * @param i The index of the sound effect to play.
     */
  
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
