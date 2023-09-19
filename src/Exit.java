import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Exit {

    int x1;
    int x2;
    int y1;
    int y2;

    BufferedImage image;
    MapPanel mp;

    Scanner input = null;
    FileOutputStream fos = null;
    PrintWriter out = null;

    public Exit(int x1, int y1, MapPanel mp) {

        this.mp = mp;

        this.x1 = x1;
        this.x2 = x1 + 48;
        this.y1 = y1;
        this.y2 = y1 + 48;

        try {
            image = ImageIO.read(new File("\\C:\\Users\\patri\\JavaProjects\\MapMaker\\src\\exit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {

        return this.image;

    }

    public void close() {

        input = new Scanner(System.in);

        System.out.println("What do you want to be the name of the map file?");
        String fileName = input.nextLine();

        try {
            if (Files.exists(Path.of(fileName))) {

                System.out.println(fileName + " exists, do you want to override(y,n)");
                char answer = input.nextLine().charAt(0);

                if (answer == 'y') {

                    fos = new FileOutputStream(fileName);
                    out = new PrintWriter(fos);

                }

            } else {

                fos = new FileOutputStream(fileName);
                out = new PrintWriter(fos);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int row = 0; row < mp.mapTiles.length; row++) {

            for (int col = 0; col < mp.mapTiles[0].length; col++) {

                if (col == mp.mapTiles[0].length - 1) {

                    out.print(mp.mapTiles[row][col].getCode());

                } else {

                    out.print(mp.mapTiles[row][col].getCode() + " ");

                }

            }

            out.println();

        }

        out.close();
        System.exit(1);

    }

    public int getX1() {return this.x1;}
    public int getX2() {return this.x2;}
    public int getY1() {return this.y1;}
    public int getY2() {return this.y2;}

}
