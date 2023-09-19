import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        MapPanel mp = new MapPanel();
        frame.add(mp);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        mp.startGameThread();

    }

}