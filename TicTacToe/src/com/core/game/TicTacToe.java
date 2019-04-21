package com.core.game;

import java.util.*;

public class TicTacToe {
   //Character to denote blank square
   final static char BLANK = '-';

   //Squares 1-9
   final static int TOP_LEFT = 0;
   final static int TOP_MID = 1;
   final static int TOP_RIGHT = 2;
   final static int MID_LEFT = 3;
   final static int MIDDLE = 4;
   final static int MID_RIGHT = 5;
   final static int BOTTOM_LEFT = 6;
   final static int BOTTOM_MID = 7;
   final static int BOTTOM_RIGHT = 8;
   
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
   public void setSquare(int index) throws IllegalArgumentException {
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
   public boolean isOccupied(int index){
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
    * @return true if winning row is found, false otherwise.
    */
   public boolean hasWinner() {
      int[][] allRows = new int[][]{{TOP_LEFT, TOP_MID, TOP_RIGHT}, 
                                    {MID_LEFT, MIDDLE, MID_RIGHT}, 
                                    {BOTTOM_LEFT, BOTTOM_MID, BOTTOM_RIGHT},
                                    {TOP_LEFT, MID_LEFT, BOTTOM_LEFT},
                                    {TOP_MID, MIDDLE, BOTTOM_MID},
                                    {TOP_RIGHT, MID_RIGHT, BOTTOM_RIGHT},
                                    {TOP_LEFT, MIDDLE, BOTTOM_RIGHT},
                                    {BOTTOM_LEFT, MIDDLE, TOP_RIGHT}}; 
      for(int[] row : allRows) {
         if(squares[row[0]] != '-') {
            char sqr1 = squares[row[0]];
            char sqr2 = squares[row[1]];
            char sqr3 = squares[row[2]];
    
            if(sqr1 == sqr2 && sqr1 == sqr3) { 
               setWinningRow(row[0], row[1], row[2]);
               return true;
            }
         }
      }
      return false; 
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
