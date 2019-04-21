package com.core.game;

import java.util.*;

public class GameInterface {

   private final TicTacToe game = new TicTacToe();
   private final Scanner input = new Scanner(System.in);

   // Starts game for users
   public void play() {
      System.out.println("***Game Start***");
      int turn = 1;
      while (!game.hasWinner() && !game.isFull()) {
         System.out.println("\nTurn #" + turn++);
         takeTurn();
      }
      if (game.hasWinner()) {
         displayWinner();
      } else {
         game.displayBoard();
         System.out.println("\nDRAW!! Even Match!\n");
      }

   }

   // Lets users select space
   public void takeTurn() {
      boolean valid = false;
      System.out.println("Player: *" + Character.toUpperCase(game.getCurrentTurn()) + "*\n");
      while (!valid)
         try {
            game.displayBoard();
            game.displayOpenSquares();
            System.out.print("\nPlease choose a square: ");
            int choice = Integer.parseInt(input.nextLine()) - 1;
            game.setSquare(choice);
            valid = true;
         } catch (IllegalArgumentException e) {
            System.out.println("\nPlease enter a valid selection\n");
         } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("\nPlease enter a number between 1-9 Inclusive.\n");
         }
   }

   // Displays board with winning line along with winning message
   public void displayWinner() {
      System.out.println("\n***WE HAVE A WINNER!!***\n");
      game.displayBoard();
      System.out.println("\n=========================");
      System.out.printf("Congratulations Player %c!!! \nYou win the game!\n", game.getWinner());
      System.out.println("=========================");
   }

   // Clears board and reselects starting symbol
   public void gameReset() {
      System.out.println("Clearing board...");
      System.out.println("Choosing starting symbol...\n");
      game.clearBoard();
      game.setFirst();
   }
   
   //Starts game
   public void run() {
      boolean playAgain = true;
      while (playAgain) {
         playAgain = false;
         play();
         System.out.print("Would you like to play again? Y/N?");
         playAgain = input.nextLine().trim().toUpperCase().equals("Y") ? true : false;
         if (playAgain) {
            gameReset();
         }
      }
      System.out.println("Thanks for playing, Good-Bye!");  
   }
}
