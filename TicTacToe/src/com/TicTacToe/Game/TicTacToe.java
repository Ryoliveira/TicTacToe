package com.TicTacToe.Game;

import java.util.*;

public class TicTacToe {

	private char s[], winner, curTurn;

	// Default Constructor
	public TicTacToe() {
		s = "---------".toCharArray(); // Initialize blank spaces
		winner = ' ';
		setFirst();
	}

	// Debug Constructor
	public TicTacToe(boolean debug) {
		s = (debug) ? new char[] { 'x', 'o', 'x', 'o', 'x', 'x', 'o', 'o', 'o' } : "---------".toCharArray();
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

	// Sets who starts the game
	public void setFirst() {
		curTurn = new Random().nextInt(2) == 0 ? 'x' : 'o';
	}

	// Returns current symbol of curTurn
	public char getCurrentTurn() {
		return curTurn;
	}

	// Sets square with symbol if empty
	public void setSquare(int index) throws IndexOutOfBoundsException, IllegalArgumentException {
		if (index < 0 || index > 8) 
			throw new IndexOutOfBoundsException("setSquare(): Index must be between 0-8 inclusive.");
		if (isOccupied(index)) 
			throw new IllegalArgumentException("setSquare(): Index is currently occupied, need an empty square.");
		s[index] = curTurn;
		curTurn = curTurn == 'x' ? 'o' : 'x';
	}

	// Checks if current square has been chosen
	public boolean isOccupied(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > 8) {
			throw new IndexOutOfBoundsException("isOccupied(): Index must be between 0-8 inclusive.");
		}
		return s[index] != '-';
	}

	// Returns if the table is out of spaces
	public boolean isFull() {
		for (char val : s) {
			if (val == '-') return false;
		}
		return true;
	}

	// Displays TicTacToe board to screen
	public void displayBoard() {
		String sym = "*"; // Symbol to denote winning line
		String sq1, sq2, sq3;
		for (int i = 0; i < 9; i += 3) {
			sq1 = Character.isUpperCase(s[i]) ? sym + s[i] + sym : " " + s[i] + " ";
			sq2 = Character.isUpperCase(s[i + 1]) ? sym + s[i + 1] + sym : " " + s[i + 1] + " ";
			sq3 = Character.isUpperCase(s[i + 2]) ? sym + s[i + 2] + sym : " " + s[i + 2] + " ";
			if (i == 3 || i == 6) System.out.println(" ---|---|---");
			System.out.println(" " + sq1 + "|" + sq2 + "|" + sq3);
		}
	}

	// Checks for winning row
	public boolean checkWinner() {
		if (s[0] == s[1] && s[0] == s[2] && s[0] != '-') { // Top Left - Top Right
			s[0] = s[1] = s[2] = Character.toUpperCase(s[0]);
			setWinner(s[0]);
			return true;
		}
		if (s[0] == s[3] && s[0] == s[6] && s[0] != '-') {// Top Left - Bottom Left
			s[0] = s[3] = s[6] = Character.toUpperCase(s[0]);
			setWinner(s[0]);
			return true;
		}
		if ((s[6] == s[7] && s[6] == s[8]) && s[6] != '-') {// Bottom Left - Bottom Right
			s[6] = s[7] = s[8] = Character.toUpperCase(s[8]);
			setWinner(s[8]);
			return true;
		}
		if (s[8] == s[5] && s[8] == s[2] && s[8] != '-') {// Top Right - Bottom Right
			s[2] = s[5] = s[8] = Character.toUpperCase(s[8]);
			setWinner(s[8]);
			return true;
		}
		if ((s[4] == s[1] && s[4] == s[7]) && s[4] != '-') {// Middle Vertical
			s[1] = s[4] = s[7] = Character.toUpperCase(s[4]);
			setWinner(s[4]);
			return true;
		}
		if (s[4] == s[3] && s[4] == s[5] && s[4] != '-') {// Middle Horizontal
			s[3] = s[4] = s[5] = Character.toUpperCase(s[4]);
			setWinner(s[4]);
			return true;
		}
		if ((s[4] == s[0] && s[4] == s[8]) && s[4] != '-') {// Middle Diagonal -- top left - bottom right
			s[0] = s[4] = s[8] = Character.toUpperCase(s[4]);
			setWinner(s[4]);
			return true;
		}
		if (s[4] == s[6] && s[4] == s[2] && s[4] != '-') {// Middle Diagonal -- bottom left - top right
			s[2] = s[4] = s[6] = Character.toUpperCase(s[4]);
			setWinner(s[4]);
			return true;
		}
		return false;
	}

}
