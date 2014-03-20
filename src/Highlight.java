/**
 * @file Highlight.java
 *
 * @author I.C. Skinner
 *
 * @date 18 Feb '14
 * 
 * @brief This highlights the winning pieces in Connect4.
 * 
 * This class will highlight the winning pieces after a game of 
 * Connect4 has been played through to completion.
 */

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Highlight{
	/**
	 * Method for highlighting the winning combination in connect 4.
	 * @param column				The column which contains the Piece 
	 *	which begins the chain of winning pieces in Connect 4.
	 * @param row					The row which contains the Piece
	 *  which begins the chain of winning pieces in Connect 4.
	 * @param ProgramController		The program controller, so that the
	 *  class can access various methods that it uses.
	 *	@return null
	 */
	public void C4Highlight(int column, int row, JLabel[][] board, 
												 GameImplementation game) 
															throws IOException{
		BufferedImage highlight_Piece = null;
		
		int winningMove = game.getWinningMove();
		
		if(game.getWinner() == PLAYER_ONE){
			String directory = "Images/RedPieceHighlighted.png";
			highlight_Piece = ImageIO.read(new File(directory));
		}else if(game.getWinner() == PLAYER_TWO){
			String directory = "Images/YellowPieceHighlighted.png";
			highlight_Piece = ImageIO.read(new File(directory));
		}else{
			
		}	
		try{
			if(winningMove == HORIZONTAL_WIN){
				board[column][row].setIcon(new ImageIcon(highlight_Piece));
				board[column+1][row].setIcon(new ImageIcon(highlight_Piece));
				board[column+2][row].setIcon(new ImageIcon(highlight_Piece));
				board[column+3][row].setIcon(new ImageIcon(highlight_Piece));
			}else if(winningMove == VERTICAL_WIN){
				board[column][row].setIcon(new ImageIcon(highlight_Piece));
				board[column][row+1].setIcon(new ImageIcon(highlight_Piece));
				board[column][row+2].setIcon(new ImageIcon(highlight_Piece));
				board[column][row+3].setIcon(new ImageIcon(highlight_Piece));
			}else if(winningMove == RIGHT_DIAGONAL_WIN){
				board[column][row].setIcon(new ImageIcon(highlight_Piece));
				board[column+1][row+1].setIcon(new ImageIcon(highlight_Piece));
				board[column+2][row+2].setIcon(new ImageIcon(highlight_Piece));
				board[column+3][row+3].setIcon(new ImageIcon(highlight_Piece));
			}else if(winningMove == LEFT_DIAGONAL_WIN){
				board[column][row].setIcon(new ImageIcon(highlight_Piece));
				board[column-1][row+1].setIcon(new ImageIcon(highlight_Piece));
				board[column-2][row+2].setIcon(new ImageIcon(highlight_Piece));
				board[column-3][row+3].setIcon(new ImageIcon(highlight_Piece));
			}else{}
		}catch(NullPointerException e){
			System.out.println("Must have been a draw!");
		}
	}
	/*
	public static void main(String[] args){
		// Testing
	}
	*/
	/** Symbolic constants */
	private final int HORIZONTAL_WIN = 0;
	private final int VERTICAL_WIN = 1;
	private final int RIGHT_DIAGONAL_WIN = 2;
	private final int LEFT_DIAGONAL_WIN = 3;
	
	private final int PLAYER_ONE = 1;
	private final int PLAYER_TWO = 2;
}