import javax.swing.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener
{
    private Square buttons[][] = new Square[5][5];
    private Square squareToMove = null;
    private Object source;

    public Board ()
    {
        JFrame board = new JFrame();
        board.setTitle("Frog Hopper!");
        board.setSize(750,750);
        board.setLayout(new GridLayout(5,5));
        board.setResizable(false);

        for(int row = 0; row<5; row++)
        {
          for(int col = 0; col<5; col++)
          {
            if ((row + col) %2 == 0)
            {
              Square s = new Square (1, row, col);
              buttons[row][col] = s;
            }
            else
            {
              Square s = new Square (0, row, col);
              buttons[row][col] = s;

            }
            board.add(buttons[row][col]);
            buttons[row][col].addActionListener(this);
          }
        }
        buttons[1][1].changeImage(2);
        buttons[1][3].changeImage(2);
        buttons[2][2].changeImage(2);
        buttons[4][0].changeImage(2);
        buttons[4][4].changeImage(2);
        buttons[4][2].changeImage(3);

        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        source = e.getSource();

        for (int r = 0; r < 5; r++)
        {
          for (int c = 0; c< 5; c++)
          {
            if (source == buttons[r][c])
            {
              this.processMove(r, c);
            }
          }
        }
    }

    public void processMove (int r, int c)
    { 
      if (squareToMove != null && buttons[r][c].getIconType() != 0 && squareToMove != source) 
      {
        if (validMove(buttons[r][c].getSquareX(), buttons[r][c].getSquareY(), squareToMove.getSquareX(), squareToMove.getSquareY()))
        {
          buttons[r][c].moveTo(squareToMove);
          squareToMove.changeImage(1);
          squareToMove = null;
          buttons[r][c].buttonClicked();
        }

        else{
          return;
        }
    
      }
      else if (buttons[r][c].getIconType() == 0)
      {
        return;
      }
      else if (squareToMove == source)
      {
        squareToMove = null;
        buttons[r][c].buttonClicked();
        return;
      }
      else 
      {
        if (buttons[r][c].getIconType() != 0 && buttons[r][c].getIconType() != 1)
        {
          buttons[r][c].buttonClicked();
          squareToMove = buttons[r][c];
        }
        return;
      }
    }
    public boolean validMove (int rN, int cN, int rO, int cO)
    {
      int rowChange = Math.abs(rO - rN);
      int colChange = Math.abs(cO - cN);

      if (rowChange == 2 && colChange == 2)
      {
        return true;
      }
      else if (rowChange == 0 && colChange == 4 || rowChange == 4 && colChange == 0)
      {
        return true;
      }
      return false;
    }
}
