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
        this.changeImage(type);
        this.xCoord = x;
        this.yCoord = y;
    }

    public int getSquareX()
    {
        return xCoord;
    }

    public int getSquareY()
    {
        return yCoord;
    }

    public int getIconType()
    {
        return type;
    }

    public void changeImage (int type)
    {

        iconArray[0] = new ImageIcon("Water.png");
        iconArray[1] = new ImageIcon("Lilypad.png");
        iconArray[2] = new ImageIcon("GreenFrog.png");
        iconArray[3] = new ImageIcon("RedFrog.png");
        iconArray[4] = new ImageIcon("GreenFrog2.png");
        iconArray[5] = new ImageIcon("RedFrog2.png");

        this.setIcon(iconArray[type]);
        this.type = type;
        
    }

    public void moveTo(Square target)
    {
        this.changeImage(target.getIconType());
        this.type = target.getIconType();
    }

    public void buttonClicked ()
    {
        if (this.type == 4)
        {
          this.changeImage(2);
          this.type = 2;
        }
        else if (this.type == 5)
        {
          this.changeImage(3);
          this.type = 3;
        }
        else if (this.type == 2)
        {
          this.changeImage(4);
          this.type = 4;
        }
        else if (this.type == 3)
        {
          this.changeImage(5);
          this.type = 5;
        }
    }
}
