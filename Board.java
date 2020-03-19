import javax.swing.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener{
    private int size = 5;
    private Square buttons[][] = new Square[size][size];
    private Square squareToMove = null;
    private Object source;
    private Square squareToRemove;

    public Board (){
        JFrame board = new JFrame();
        board.setTitle("Frog Hopper!");
        board.setSize(750,750);
        board.setLayout(new GridLayout(size,size));
        board.setResizable(false);

        for(int row = 0; row<size; row++){
          for(int col = 0; col<size; col++){
            if ((row + col) %2 == 0){
              Square s = new Square (1, row, col);
              buttons[row][col] = s;
            }
            else{
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

    public void actionPerformed(ActionEvent e){
        source = e.getSource();

        for (int r = 0; r < 5; r++){
          for (int c = 0; c< 5; c++){
            if (source == buttons[r][c]){
              this.processMove(r, c);
            }
          }
        }
    }

    public void processMove (int r, int c){ 
      if (squareToMove != null && buttons[r][c].getIconType() != 0 && squareToMove != source){
        if (validMove(buttons[r][c], squareToMove)){
          buttons[r][c].moveTo(squareToMove);
          squareToMove.changeImage(1);
          squareToMove = null;
          buttons[r][c].buttonClicked();
          squareToRemove.changeImage(1);
        }
      }
      else if (buttons[r][c].getIconType() == 0){
        return;
      }
      else if (squareToMove == source){
        squareToMove = null;
        buttons[r][c].buttonClicked();
      }
      else{
        if (buttons[r][c].getIconType() != 0 && buttons[r][c].getIconType() != 1){
          buttons[r][c].buttonClicked();
          squareToMove = buttons[r][c];
        }
      }
    }
    public boolean validMove (Square newSquare, Square oldSquare){

      int rN = newSquare.getSquareX();
      int rO = oldSquare.getSquareX();
      int cN = newSquare.getSquareY();
      int cO = oldSquare.getSquareY();
      
      int rowChange = Math.abs(rO - rN);
      int colChange = Math.abs(cO - cN);

      squareToRemove = jumpOver(newSquare, oldSquare);

      if (buttons[rN][cN].getIconType() == 1){
        if (rowChange == 2 && colChange == 2 && squareToRemove.getIconType() == 2){
          return true;
        }
        else if (squareToRemove.getIconType() == 2){
          if (rowChange == 0 && colChange == 4 || rowChange == 4 && colChange == 0){
            return true;
          }
         return false;
        }
        return false;
      }
      return false;
    }

    public Square jumpOver (Square newSquare, Square oldSquare){
      int rN = newSquare.getSquareX();
      int rO = oldSquare.getSquareX();
      int cN = newSquare.getSquareY();
      int cO = oldSquare.getSquareY();

      int halfRowChange = (Math.abs(rO - rN))/2;
      int halfColChange = (Math.abs(cO - cN))/2;

      if (rN > rO && cN == cO){
        return buttons[rN - halfRowChange][cN];
      }

      else if (rN < rO && cN == cO){
        return buttons[rN + halfRowChange][cN];
      }

      else if (rN == rO && cN > cO){
        return buttons[rN][cN - halfColChange];
      }

      else if (rN == rO && cN < cO){
        return buttons[rN][cN + halfColChange];
      }

      else if (rN > rO && cN > cO){
        return buttons[rN - halfRowChange][cN - halfColChange];
      }

      else if (rN < rO && cN < cO){
        return buttons[rN + halfRowChange][cN + halfColChange];
      }

      else if (rN < rO && cN > cO){
        return buttons[rN + halfRowChange][cN - halfColChange];
      }

      else if (rN > rO && cN < cO){
        return buttons[rN - halfRowChange][cN + halfColChange];
      }

      else{
        return null;
      }
    }
}
