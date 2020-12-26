import javax.swing.*;
import java.awt.*;

public class Driver
{
    /*This is the driver that starts the game
    */
    public static void main( String[] args)
    {
        JFrame frame = new JFrame("Seven-Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 50);
        Playboard playboard = new Playboard(1000, 800);
        frame.getContentPane().add(playboard);
        frame.pack();
        frame.setVisible(true);
    }
}
