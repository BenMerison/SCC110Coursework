import javax.swing.*;
import java.awt.*;
import java.applet.Applet;

public class Board 
{
    public static void main (String[] args)
    {
        JFrame a = new JFrame();
        JPanel gridPanel = new JPanel();
                              
        a.setContentPane(gridPanel);

        gridPanel.setLayout(new GridLayout(5,5));

        for (int row = 0; row < 25; row++)
        {
            gridPanel.add(new Square());
        }
        
        a.setTitle("Frog Hopper!");
        a.setSize(750,750);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);

    }
}