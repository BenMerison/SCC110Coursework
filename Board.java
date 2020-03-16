import javax.swing.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener
{
    private Square buttons[][] = new Square[5][5];

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

    private Square squareToMove = null;

    public void actionPerformed(ActionEvent e) 
    {

        Object source = e.getSource();

        for (int r = 0; r < 5; r++)
        {
          for (int c = 0; c< 5; c++)
          {

            if (source == buttons[r][c])
            {
              if (squareToMove != null)
              {
                squareToMove.MoveTo(buttons[r][c]);
                buttons[r][c].SetImage(squareToMove.GetType());
                squareToMove.SetImage(1);
                squareToMove = null;

                if (buttons[r][c].GetType() == 4)
                {
                  buttons[r][c].SetImage(2);
                }
                else if (buttons[r][c].GetType() == 5)
                {
                  buttons[r][c].SetImage(3);
                }
              }

              else if (squareToMove == null)        
              {
                if (buttons[r][c].GetType() != 0 || buttons[r][c].GetType() != 1)
                {
                  if (buttons[r][c].GetType() == 2)
                  {
                    buttons[r][c].SetImage(4);
                  }
                  else if (buttons[r][c].GetType() == 3)
                  {
                    buttons[r][c].SetImage(5);
                  }
                }
                else 
                {
                  squareToMove = null;
                }

                squareToMove = buttons[r][c];
              }
              }
            }
          }
        }
      }
