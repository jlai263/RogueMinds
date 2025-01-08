package data;

import main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;
    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }
    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds = new DataStorage();

            ds.coins = gp.player.hasCoin;
            ds.time = gp.player.time;
            ds.username = gp.player.username;

            // Write the Data Storage object
            oos.writeObject(ds);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void load(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the Data Storage
            DataStorage ds = (DataStorage) ois.readObject();
            gp.player.time = ds.time;
            gp.player.hasCoin = ds.coins;
            gp.player.username = ds.username;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load Error!");
        }
    }
}
