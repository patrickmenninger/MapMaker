import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Upload {

    int x1;
    int x2;
    int y1;
    int y2;

    BufferedImage image;
    MapPanel mp;

    FileInputStream fis = null;
    Scanner reader = null;
    Scanner input = null;

    String root = "C:\\Users\\patri\\JavaProjects\\Adventure\\src\\BlockImages\\";
    String type = ".png";

    public Upload (int x, int y, MapPanel mp) {

        this.mp = mp;

        this.x1 = x;
        this.x2 = x + 48;
        this.y1 = y;
        this.y2 = y + 48;

        try {
            image = ImageIO.read(new File("\\C:\\Users\\patri\\JavaProjects\\MapMaker\\src\\upload.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public BufferedImage getImage() {

        return this.image;

    }

    public void upload() {

        input = new Scanner(System.in);

        System.out.println("What is the name of the file to be uploaded?");
        String fileName = input.nextLine();

        try {

            fis = new FileInputStream(fileName);
            reader = new Scanner(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int row = 0;
        while (reader.hasNextLine()) {

            String line = reader.nextLine();
            String[] codes = line.split(" ");

            for (int col = 0; col < mp.mapTiles[0].length; col++) {

                mp.mapTiles[row][col].setCode(codes[col]);
                mp.mapTiles[row][col].setImage(root + codes[col] + type);
                mp.mapTiles[row][col].setBlank(false);

            }

            row++;

        }

        System.out.println(row);

    }

    public int getX1() {return this.x1;}
    public int getX2() {return this.x2;}
    public int getY1() {return this.y1;}
    public int getY2() {return this.y2;}

}
