import javax.swing.*;
import java.awt.*;
import java.applet.Applet;

/*
  *class to model the individual squares/buttons
  *onthe game board. Extends JButton.
*/

public class Square extends JButton
{
    //The following array stores all the various types 
    //of icons for the various types of buttons on the
    //game board.
    private ImageIcon [] iconArray = new ImageIcon[6];

    //The following variables define the position of
    //the square and the type of square it is.
    private int xCoord;
    private int yCoord;
    private int type;

    /**
     * Constructor method. Creates a square with the given parameters.
     * @param t Defines the type of icon/button from the arracy (t being an index in the array)
     * @param x The x coordinate (1st index) in the 2d array from the Board class.
     * @param y The y coordinate (2nd index) in the 2d array from the Board class.
     */
    public Square(int t, int x, int y)
    {
        this.type = t;
        this.changeImage(type);
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * gets the current x position of this Square
     * @return the x coordinate (1st index) of this square within the 2d array
     *         in the Board class.
     */
    public int getSquareX()
    {
        return xCoord;
    }

    /**
     * gets the current y position of this Square
     * @return the y coordinate (2nd index) of this square within the 2d array
     *         in the Board class.
     */
    public int getSquareY()
    {
        return yCoord;
    }

    /**
     * Gets the type variables which defines the icon/Square type of the Square
     * @return the type variable.
     */
    public int getIconType()
    {
        return type;
    }
    
    /**
     * Populates the iconArray with the various images/icons.
     * changes the icon of this Square to a given icon from 
     * within the iconArray when called.
     * @param type int which is used as an index for the iconArray.
     */
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

    /**
     * Changes the image and type of this Square to those of a given square
     * when called.
     * @param target the square being moved. The icon and type are taken from this
     *               and applied to this Square.
     */
    public void moveTo(Square target)
    {
        this.changeImage(target.getIconType());
        this.type = target.getIconType();
    }
    
    /**
     * Called when a button has been clicked and changes 
     * the icon to its clicked (yellow border) counterpart
     * or to the original unbordered version depending on
     * the current type of this Square.
     */
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
