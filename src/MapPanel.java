import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapPanel extends JPanel implements Runnable {

    int FPS = 60;

    int tileSize = 16;
    String tileLocation = "C:\\Users\\patri\\JavaProjects\\Adventure\\src\\BlockImages\\";
    String type = ".png";

    Tiles[][] mapTiles = new Tiles[41][50];
    Tiles[][] blockTiles = new Tiles[3][25];
    String codes = "src/blockCodes.txt";

    Exit exitTile;
    Upload uploadTile;

    MouseControl mc = new MouseControl(this);
    MouseMovement mm = new MouseMovement(this, mc);

    FileInputStream fis = null;
    Scanner reader = null;

    Thread gameThread;

    public MapPanel() {

        this.setPreferredSize(new Dimension((50 * tileSize + (tileSize * 3)), (50 * tileSize)));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addMouseListener(mc);
        this.addMouseMotionListener(mm);

        exitTile = new Exit(50 * tileSize, 0, this);
        uploadTile = new Upload(50 * tileSize, tileSize * 3, this);

        try {
            fis = new FileInputStream(codes);
            reader = new Scanner(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int row = 0; row < mapTiles.length; row++) {

            for (int col = 0; col < mapTiles[0].length; col++) {

                mapTiles[row][col] = new Tiles();

            }

        }

        for (int row = 0; row < blockTiles.length; row++) {

            for (int col = 0; col < blockTiles[0].length; col++) {

                if (reader.hasNextLine()) {

                    String code = reader.nextLine();
                    String file = tileLocation + code + type;

                    blockTiles[row][col] = new Tiles(file, code, false, false);

                } else {

                    blockTiles[row][col] = new Tiles(null, null, true, false);

                }

            }

        }

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void update() {

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D) g;

        drawMap(g2);
        drawBlocks(g2);
        drawExit(g2);
        drawUpload(g2);



    }

    public void drawMap(Graphics2D g2) {

        int x = 0;
        int y = 0;

        for (int row = 0; row < mapTiles.length; row++) {

            for (int col = 0; col < mapTiles[0].length; col++) {

                if (mapTiles[row][col].blank) {

                    g2.drawRect(x, y, tileSize, tileSize);

                } else {

                    g2.drawImage(mapTiles[row][col].getImage(), x, y, tileSize, tileSize, null);

                }

                mapTiles[row][col].setX(x);
                mapTiles[row][col].setY(y);

                x += tileSize;

            }

            x = 0;
            y += tileSize;

        }

    }

    public void drawBlocks(Graphics2D g2) {

        int x = 0;
        int y = 576 + tileSize * 5;

        for (int row = 0; row < blockTiles.length; row++) {

            for (int col = 0; col < blockTiles[0].length; col++) {

                if (blockTiles[row][col].blank) {

                    //g2.drawRect(x, y, tileSize, tileSize);

                } else {

                    blockTiles[row][col].setX(x);
                    blockTiles[row][col].setY(y);

                    g2.drawImage(blockTiles[row][col].getImage(), x, y, tileSize * 2, tileSize * 2, null);
                    g2.drawRect(x, y, tileSize * 2, tileSize * 2);

                }

                x += tileSize * 2;

            }

            y += tileSize * 2;
            x = 0;

        }

    }

    public void drawExit(Graphics2D g2) {

        g2.drawImage(exitTile.getImage(), exitTile.getX1(), exitTile.getY1(), tileSize * 3, tileSize * 3, null);

    }

    public void drawUpload(Graphics2D g2) {

        g2.drawImage(uploadTile.getImage(), uploadTile.getX1(), uploadTile.getY1(), tileSize * 3, tileSize * 3, null);

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        int frameCounter = 0;
        long timer = 0;

        // Initialize tiles


        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {

                //UPDATE
                //update();
                //PAINT
                repaint();

                delta--;
                frameCounter++;

            }

            if (timer >= 1000000000) {

                //System.out.println("FPS: " + frameCounter);
                frameCounter = 0;
                timer = 0;

            }


        }

    }

}
