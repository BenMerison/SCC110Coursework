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
        buttons[1][1].SetImage(2);
        buttons[1][3].SetImage(2);
        buttons[2][2].SetImage(2);
        buttons[4][0].SetImage(2);
        buttons[4][4].SetImage(2);
        buttons[4][2].SetImage(3);

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

      if (squareToMove != null && buttons[r][c].GetType() != 0 && squareToMove != source) 
      {
        squareToMove.MoveTo(buttons[r][c]);
        buttons[r][c].SetImage(squareToMove.GetType());
        squareToMove.SetImage(1);
        squareToMove = null;

        buttons[r][c].ButtonClicked();
      }

      else if (buttons[r][c].GetType() == 0)
      {
        return;
      }

      else if (squareToMove == source)
      {
        squareToMove = null;
        buttons[r][c].ButtonClicked();
        return;
      }

      else 
      {
        buttons[r][c].ButtonClicked();
        squareToMove = buttons[r][c];
        return;
      }
    }

    public void validMove (Square newLocation)
    {

    }
}
