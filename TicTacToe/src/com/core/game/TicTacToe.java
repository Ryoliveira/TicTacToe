package com.core.game;

import java.util.*;

public class TicTacToe {
   //Character to denote blank square
   private final char BLANK = '-';

   //Squares 1-9
   private final int TOP_LEFT = 0;
   private final int TOP_MID = 1;
   private final int TOP_RIGHT = 2;
   private final int MID_LEFT = 3;
   private final int MIDDLE = 4;
   private final int MID_RIGHT = 5;
   private final int BOTTOM_LEFT = 6;
   private final int BOTTOM_MID = 7;
   private final int BOTTOM_RIGHT = 8;
   
   private char squares[], winner, curTurn;

   // Default Constructor
   public TicTacToe() {
      this(false);
   }

   /**
    * Debug constructor
    * Change the values in char array to manipulate board
    * 
    * @param debug
    */
   public TicTacToe(boolean debug) {
      squares = (debug) ? new char[] { 'x', 'o', 'x', 'o', 'x', 'x', 'o', 'o', 'o' } : "---------".toCharArray();
      winner = ' ';
      setFirst();
   }

   // Returns winner of game
   public char getWinner() {
      return winner;
   }

   // Sets the winner of game
   public void setWinner(char c) {
      winner = c;
   }
   
   /**
    * Denotes winning row by changing each square in row
    * to upper-case characters
    * @param sqr1 First Square
    * @param sqr2 Second Square
    * @param sqr3 Third Square
    */
   public void setWinningRow(int sqr1, int sqr2, int sqr3) {
      squares[sqr1] = squares[sqr2] = squares[sqr3] = Character.toUpperCase(squares[sqr1]);
      setWinner(squares[sqr1]);
   }

   //Generates number 0 or 1 to select who starts the game
   public void setFirst() {
      curTurn = (new Random().nextInt(2)) == 0 ? 'x' : 'o';
   }

   // Returns current symbol of curTurn, 'x' or 'o'
   public char getCurrentTurn() {
      return curTurn;
   }

   /**
    * Sets a square with current player symbol.
    * 
    * @param index Selected square index.
    * @throws IndexOutOfBoundsException if selected square is out of range.
    * @throws IllegalArgumentException if selected square is already occupied with player symbol.
    */
   public void setSquare(int index) throws IndexOutOfBoundsException, IllegalArgumentException {
      if (index < 0 || index > 8) {
         throw new IndexOutOfBoundsException("setSquare(): Index must be between 0-8 inclusive.");
      }
      if (isOccupied(index)) {
         throw new IllegalArgumentException("setSquare(): Index is currently occupied, need an empty square.");
      }
      squares[index] = curTurn;
      curTurn = curTurn == 'x' ? 'o' : 'x';
   }

   /**
    * Checks if current square has been chosen.
    * 
    * @param index Select square index.
    * @return true if square is occupied, false otherwise.
    * @throws IndexOutOfBoundsException if selected square is out of range.
    */
   public boolean isOccupied(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index > 8) {
         throw new IndexOutOfBoundsException("isOccupied(): Index must be between 0-8 inclusive.");
      }
      return squares[index] != BLANK;
   }

   // Returns if the table is out of spaces
   public boolean isFull() {
      for (char val : squares) {
         if (val == BLANK) {
            return false;
         }
      }
      return true;
   }

   /**
    * Checks for winning row.
    * if row is found, then winning row is denoted by upper-case symbols.
    * 
    * @return true is winning row is found, false otherwise.
    */
   public boolean checkWinner() {
      // Top Left - Top Right
      if (squares[TOP_LEFT] == squares[TOP_MID] && squares[TOP_LEFT] == squares[TOP_RIGHT] && squares[TOP_LEFT] != BLANK) { 
         setWinningRow(TOP_LEFT, TOP_MID, TOP_RIGHT);
      }
      // Top Left - Bottom Left
      else if (squares[TOP_LEFT] == squares[MID_LEFT] && squares[TOP_LEFT] == squares[BOTTOM_LEFT] && squares[TOP_LEFT] != BLANK) {
         setWinningRow(TOP_LEFT, MID_LEFT, BOTTOM_LEFT);
      }
      // Bottom Left - Bottom Right
      else if ((squares[BOTTOM_LEFT] == squares[BOTTOM_MID] && squares[BOTTOM_LEFT] == squares[BOTTOM_RIGHT]) && squares[BOTTOM_LEFT] != BLANK) {
         setWinningRow(BOTTOM_LEFT, BOTTOM_MID, BOTTOM_RIGHT);
      }
      // Top Right - Bottom Right
      else if (squares[TOP_RIGHT] == squares[MID_RIGHT] && squares[BOTTOM_RIGHT] == squares[TOP_RIGHT] && squares[TOP_RIGHT] != BLANK) {         
         setWinningRow(TOP_RIGHT, MID_RIGHT, BOTTOM_RIGHT);
      }
      //Middle Vertical
      else if ((squares[MIDDLE] == squares[TOP_MID] && squares[MIDDLE] == squares[BOTTOM_MID]) && squares[MIDDLE] != BLANK) {
         setWinningRow(TOP_MID, MIDDLE, BOTTOM_MID);
      }
      //Middle Horizontal
      else if (squares[MIDDLE] == squares[MID_LEFT] && squares[MIDDLE] == squares[MID_RIGHT] && squares[MIDDLE] != BLANK) {
         setWinningRow(MID_LEFT, MIDDLE, MID_RIGHT);
      }
      // Middle Diagonal -- top left - bottom right
      else if ((squares[MIDDLE] == squares[TOP_LEFT] && squares[MIDDLE] == squares[BOTTOM_RIGHT]) && squares[MIDDLE] != BLANK) {
         setWinningRow(TOP_LEFT, MIDDLE, BOTTOM_RIGHT);
      }
      // Middle Diagonal -- bottom left - top right
      else if (squares[MIDDLE] == squares[BOTTOM_LEFT] && squares[MIDDLE] == squares[TOP_RIGHT] && squares[MIDDLE] != BLANK) {
         setWinningRow(BOTTOM_LEFT, MIDDLE, TOP_RIGHT);
      }
      else {
         return false;
      }
      return true;
   }

   // Displays TicTacToe board to screen
   public void displayBoard() {
      String sym = "*"; // Symbol to denote winning line
      String space = " ";
      String sq1, sq2, sq3;
      for (int i = 0; i < 9; i += 3) {
         sq1 = Character.isUpperCase(squares[i]) ? sym + squares[i] + sym : space + squares[i] + space;
         sq2 = Character.isUpperCase(squares[i + 1]) ? sym + squares[i + 1] + sym : space + squares[i + 1] + space;
         sq3 = Character.isUpperCase(squares[i + 2]) ? sym + squares[i + 2] + sym : space + squares[i + 2] + space;
         if (i == 3 || i == 6) {
            System.out.println(" ---|---|---");
         }
         System.out.println(" " + sq1 + "|" + sq2 + "|" + sq3);
      }
   }

   // Clears game board
   public void clearBoard() {
      squares = "---------".toCharArray();
   }
   
   /**
    *  Displays available squares
    *  If square is not available, the option will not be displayed to console
    */
   public void displayOpenSquares() {
      String[] sqrLocs = { "Top-Left", "Top-Mid", "Top-Right", 
                            "Mid-Left", "Middle", "Mid-Right", 
                            "Bottom-Left", "Bottom-Mid", "Bottom-Right" };
      System.out.println("\nSquares Available");
      System.out.println("=================");
      for (int i = 0; i < squares.length; i++) {
         if (!isOccupied(i)) {
            System.out.println(i + 1 + ") " + sqrLocs[i]);
         }
      }
      System.out.println("=================");
   }
}
