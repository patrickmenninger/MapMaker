import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {

    boolean mapTile = true;
    boolean blank;

    BufferedImage image;
    String fileLocation;
    String code;
    int x1;
    int y1;
    int x2;
    int y2;


    public Tiles () {

        image = null;
        blank = true;

    }

    public Tiles(String fileLocation, String code, boolean blank, boolean mapTile) {

        this.mapTile = mapTile;
        this.blank = blank;

        if (this.blank) {

            image = null;


        } else {

            this.fileLocation = fileLocation;
            this.code = code;

            try {
                image = ImageIO.read(new File(fileLocation));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public BufferedImage getImage() {

        return this.image;

    }

    public void setImage(String fileLocation) {

        this.fileLocation = fileLocation;

        try {
            this.image = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            this.image = null;
            System.out.println("Couldn't load tile");
        }

    }

    public void setBlank(boolean blank) {

        this.blank = blank;

    }

    public void setX (int x) {

        this.x1 = x;

        if (mapTile) {
            this.x2 = x + 16;
        } else {
            this.x2 = x + 32;
        }

    }

    public int getX1() {
        return this.x1;
    }

    public int getX2() {
        return this.x2;
    }

    public void setY (int y) {

        this.y1 = y;

        if (mapTile) {
            this.y2 = y + 16;
        } else {
            this.y2 = y + 32;
        }

    }

    public int getY1() {
        return this.y1;
    }

    public int getY2() {
        return this.y2;
    }

    public String getFileLocation() {

        return this.fileLocation;

    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {

        this.code = code;

    }

}
