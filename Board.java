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

        for (int i = 0; i<25; i++)
        {
        JButton b = new JButton(x);
        gridPanel.add(b);
        b.setIcon(x);
        }
        
        a.setTitle("Frog Hopper!");
        a.setSize(750,750);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);

    }


}