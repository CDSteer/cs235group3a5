/**
 * @file ColourChange.java
 *
 * @author A. Keskin
 *
 * @date 25 Feb '14
 * 
 * @brief Animates the Othello board pieces.
 * 
 * This classes, when passed a piece
 */

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ColourChange extends JFrame {
	
	private JLabel container;
	private ImageIcon blackToWhite;
	private ImageIcon whiteToBlack;
	
	public ColourChange(){}
	
	/**
	 *	Calls either blackToWhiteFlip() method or whiteToBlackFlip() method depending on the
	 *	input from the getColour() method parsed in from the Piece class.
	 *	@param p			Parses in the Piece class so that method can get colour of piece.
	 *	@return	ImageIcon	This returns the gif image which represents 
	 the piece flipping when a colour is changed in the Othello game. 
	 */
	public ImageIcon flip(Piece p){
		if (p.getColour().equals("Black")){
			return whiteToBlackFlip();
		}
		else if (p.getColour().equals("White")){
			return blackToWhiteFlip();
		}
		else {
			return new ImageIcon();
		}
	}
	
	/**
	 *	Fetches the gif which animates the piece flipping from black to white and returns it.
	 *	@return	blackToWhite	The animated Gif which flips a piece from black to white. 
	 */
	private ImageIcon blackToWhiteFlip(){
		ImageIcon blackToWhite = new ImageIcon("Images/BlackToWhitePiece.gif");
		blackToWhite.getImage().flush();
		return blackToWhite;
	}
	
	/**
	 *	Fetches the gif which animates the piece flipping from white to black and returns it.
	 *	@return	whiteToBlack	The animated Gif which flips a piece from white to black.
	 */
	private ImageIcon whiteToBlackFlip(){
		ImageIcon whiteToBlack = new ImageIcon("Images/WhiteToBlackPiece.gif");
		whiteToBlack.getImage().flush();
		return whiteToBlack;
	}
	
	public static void main(String[] args) throws IOException {
	}
}