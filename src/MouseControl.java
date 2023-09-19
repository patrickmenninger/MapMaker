import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseControl implements MouseListener {

    MapPanel mp;
    Tiles selectedTile = new Tiles();
    boolean tileSelected = false;

    boolean pressed = false;

    public MouseControl(MapPanel mp) {

        this.mp = mp;

    }
    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        //System.out.println("x: " + x + ", y: " + y);

        if (y > 576 + mp.tileSize * 5) {
            for (int rows = 0; rows < mp.blockTiles.length; rows++) {

                for (int cols = 0; cols < mp.blockTiles[0].length; cols++) {

                    if ((x >= mp.blockTiles[rows][cols].getX1() && x <= mp.blockTiles[rows][cols].getX2()) &&
                            y <= mp.blockTiles[rows][cols].getY2() && y >= mp.blockTiles[rows][cols].getY1()) {


                        selectedTile.setImage(mp.blockTiles[rows][cols].getFileLocation());
                        selectedTile.setCode(mp.blockTiles[rows][cols].getCode());
                        tileSelected = true;
                        System.out.println(mp.blockTiles[rows][cols].getCode());

                    }

                }

            }
        } else if (y < 576 + mp.tileSize * 5 && x < 800){
            if (tileSelected) {
                for (int rows = 0; rows < mp.mapTiles.length; rows++) {

                    for (int cols = 0; cols < mp.mapTiles[0].length; cols++) {

                        if ((x >= mp.mapTiles[rows][cols].getX1() && x <= mp.mapTiles[rows][cols].getX2()) &&
                                y <= mp.mapTiles[rows][cols].getY2() && y >= mp.mapTiles[rows][cols].getY1()) {

                            mp.mapTiles[rows][cols].setImage(selectedTile.getFileLocation());
                            mp.mapTiles[rows][cols].setBlank(false);
                            mp.mapTiles[rows][cols].setCode(selectedTile.getCode());

                        }

                    }

                }
            }
        } else if (x >= mp.exitTile.getX1() && x <= mp.exitTile.getX2() &&
                y <= mp.exitTile.getY2() && y >= mp.exitTile.getY1()) {

            mp.exitTile.close();

        } else if (x >= mp.uploadTile.getX1() && x <= mp.uploadTile.getX2() &&
                y <= mp.uploadTile.getY2() && y >= mp.uploadTile.getY1()){

            mp.uploadTile.upload();

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
