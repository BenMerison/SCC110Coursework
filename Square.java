import javax.swing.*;
import java.awt.*;
import java.applet.Applet;

public class Square extends JButton
{
    private ImageIcon [] iconArray = new ImageIcon[6];

    private int xCoord;
    private int yCoord;
    private int type;

    public Square(int t, int x, int y)
    {
        this.type = t;
        this.SetImage(type);
        this.xCoord = x;
        this.yCoord = y;
    }

    public int GetX()
    {
        return xCoord;
    }

    public int GetY()
    {
        return yCoord;
    }

    public int GetType()
    {
        return type;
    }

    public void SetImage (int type)
    {

        iconArray[0] = new ImageIcon("Water.png", "Water icon");
        iconArray[1] = new ImageIcon("Lilypad.png", "Lilypad icon");
        iconArray[2] = new ImageIcon("GreenFrog.png", "GreenFrog icon");
        iconArray[3] = new ImageIcon("RedFrog.png", "RedFrog icon");
        iconArray[4] = new ImageIcon("GreenFrog2.png", "GreenFrogClicked icon");
        iconArray[5] = new ImageIcon("RedFrog2.png", "RedFrogClicked icon");

        this.setIcon(iconArray[type]);
        this.type = type;
        
    }

    public void MoveTo(Square target)
    {
        int newX = target.GetX();
        int newY = target.GetY();

        xCoord = newX;
        yCoord = newY;
    }

    public void ButtonClicked ()
    {
        if (this.type == 4)
        {
          this.SetImage(2);
          this.type = 2;
        }
        else if (this.type == 5)
        {
          this.SetImage(3);
          this.type = 3;
        }
        else if (this.type == 2)
        {
          this.SetImage(4);
          this.type = 4;
        }
        else if (this.type == 3)
        {
          this.SetImage(5);
          this.type = 5;
        }
    }
}
