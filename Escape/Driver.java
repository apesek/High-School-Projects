import javax.swing.*;
import java.awt.*;

public class Driver
{
    public static void main( String[] args)
    {
        JFrame frame = new JFrame("BULLET HELL!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(5,5);
        Board board = new Board(1000, 800);
        frame.getContentPane().add(board);
        frame.pack();
        frame.setVisible(true);
    }
}