import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMovement implements MouseMotionListener {

    MapPanel mp;
    MouseControl mc;

    public MouseMovement (MapPanel mp, MouseControl mc) {

        this.mp = mp;
        this.mc = mc;

    }


    @Override
    public void mouseDragged(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        if (mc.tileSelected && y <= 576 + (mp.tileSize * 5)) {

            for (int rows = 0; rows < mp.mapTiles.length; rows++) {

                for (int cols = 0; cols < mp.mapTiles[0].length; cols++) {

                    if ((x >= mp.mapTiles[rows][cols].getX1() && x <= mp.mapTiles[rows][cols].getX2()) &&
                            y <= mp.mapTiles[rows][cols].getY2() && y >= mp.mapTiles[rows][cols].getY1()) {

                        mp.mapTiles[rows][cols].setImage(mc.selectedTile.getFileLocation());
                        mp.mapTiles[rows][cols].setBlank(false);
                        mp.mapTiles[rows][cols].setCode(mc.selectedTile.getCode());

                    }

                }

            }

        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
