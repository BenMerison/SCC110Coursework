import javax.swing.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to create a board, add a grid and 
 * populate it with buttons/Squares. 
 * The Square class is used to populate the grid.
 * The board and the squares are displayed on screen.
 * Implements ActionListener for the buttons.
 */

public class Board implements ActionListener{

    //The following variable is used to determine the size of the 2d 
    //array and the size of the game board. Size 5 would result in a 
    //5X5 board and array of size [5][5].
    //The array stored the Squares which populate the game board.
    private int size = 5;
    private Square buttons[][] = new Square[size][size];

    //The following variables are used to store a square which the user wishes
    //to move, the source of a button click and a square which should be removed
    //after the player has jumped over it. 
    private Square squareToMove = null;
    private Object source;
    private Square squareToRemove;

    /**
     * Constructor method. This is used to create the game board, 
     * populate it with the required squares and display it to the user. 
     */
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

    /**
     * Called when the user clicks a button. Determines which button has
     * been clicked and then calls the processMove method passing the r
     * and c (coordinates) variables.
     */
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

    /**
     * Processes the move that the user wishes to make. Stores the Square
     * that the user wishes to move, calls the validMove method to ensure 
     * the user is making a legal move and changes the various icons to
     * impliment the move.
     * @param r represents the rows in the gridlayout and the 1st index in the 2d array.
     * @param c represents the columns in the gridlayout and the 2nd index in the 2d array.
     */
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

    /**
     * Determines if the users requested move is a valid one (must jump over
     * another frog, must jump to another lilypad, etc.). 
     * @param newSquare The square the user wishes to move to.
     * @param oldSquare The square the user wishes to move.
     * @return boolean of true or false depending on the result of the checks.
     */
    public boolean validMove (Square newSquare, Square oldSquare){

      int rN = newSquare.getSquareX();
      int rO = oldSquare.getSquareX();
      int cN = newSquare.getSquareY();
      int cO = oldSquare.getSquareY();
      
      //math.abs is used to ensure only the difference in the numbers is stored
      //and not a negative number which would not work.
      int rowChange = Math.abs(rO - rN);
      int colChange = Math.abs(cO - cN);

      //The jump over method is used to determine which square has been 
      //jumped over. The result of this method is then stored in the 
      //squareToRemove variable. 
      squareToRemove = jumpOver(newSquare, oldSquare);

      if (buttons[rN][cN].getIconType() == 1){
        if (rowChange == 2 && colChange == 2 && squareToRemove.getIconType() == 2 || squareToRemove.getIconType() == 3){
          return true;
        }
        else if (squareToRemove.getIconType() == 2 || squareToRemove.getIconType() == 3){
          if (rowChange == 0 && colChange == 4 || rowChange == 4 && colChange == 0){
            return true;
          }
          return false;
        }
        return false;
      }
      return false;
    }

    /**
     * Method to determine what square has been jumped over and then 
     * returns this square.
     * @param newSquare The square the user wishes to move to.
     * @param oldSquare The square the user wishes to move.
     * @return the square that the user wishes to jump over.
     */
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
