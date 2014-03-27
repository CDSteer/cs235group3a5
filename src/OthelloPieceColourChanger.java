/**
 * @file OthelloPieceColourChanger.java
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

public class OthelloPieceColourChanger extends JFrame {

	private JLabel container;
	private ImageIcon blackToWhite;
	private ImageIcon whiteToBlack;
    private static OthelloPieceColourChanger m_ColourChanger;
    private static ImageIcon m_ImageIcon;

	public OthelloPieceColourChanger(){}

	/**
	 *	Calls either blackToWhiteFlip() method or whiteToBlackFlip() method depending on the
	 *	input from the getColour() method parsed in from the Piece class.
	 *	@param p			Parses in the Piece class so that method can get colour of piece.
	 *	@return	ImageIcon	This returns the gif image which represents
	 *  the piece flipping when a colour is changed in the Othello game.
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
		ImageIcon blackToWhite = new ImageIcon("../Images/BlackToWhitePiece.gif");
		blackToWhite.getImage().flush();
		return blackToWhite;
	}

	/**
	 *	Fetches the gif which animates the piece flipping from white to black and returns it.
	 *	@return	whiteToBlack	The animated Gif which flips a piece from white to black.
	 */
	private ImageIcon whiteToBlackFlip(){
		ImageIcon whiteToBlack = new ImageIcon("../Images/WhiteToBlackPiece.gif");
		whiteToBlack.getImage().flush();
		return whiteToBlack;
	}

	public static void main(String[] args) throws IOException {

        //	Tests whether the black to white gif animation from file equals the gif animation parsed
        //	out from the ColourChange class.

            m_ColourChanger = new OthelloPieceColourChanger();
            m_ImageIcon = new ImageIcon("Images/BlackToWhitePiece.gif");


        if(m_ColourChanger.flip(new Piece("White")).getImage().equals(m_ImageIcon.getImage())) {
            System.out.println("OthelloPieceColourChanger Test One BlackToWhite Evaluated: Correct");
        } else {
            System.out.println("OthelloPieceColourChanger Test One BlackToWhite Evaluated: Incorrect");
        }


        //	Tests whether the white to black gif animation from file equals the gif animation parsed
        //	out from the ColourChange class.

            m_ColourChanger = new OthelloPieceColourChanger();
            m_ImageIcon = new ImageIcon("Images/WhiteToBlackPiece.gif");


        if(m_ColourChanger.flip(new Piece("Black")).getImage().equals (m_ImageIcon.getImage())) {
            System.out.println("OthelloPieceColourChanger Test Two WhiteToBlack Evaluated: Correct");
        } else {
            System.out.println("OthelloPieceColourChanger Test Two WhiteToBlack Evaluated: Incorrect");
        }


        //	Tests if an incorrect string is inputted so that it doesn't output one of the GIFs and outputs
        //	a blank ImageIcon.

            m_ColourChanger = new OthelloPieceColourChanger();
            m_ImageIcon = new ImageIcon();

        try {
         if(m_ColourChanger.flip(new Piece("Blue")).getImage().equals (m_ImageIcon.getImage())) {
            System.out.println("OthelloPieceColourChanger Test Three BlankIcon Evaluated: Correct");
        }

        } catch (Exception e) {

            System.out.println("OthelloPieceColourChanger Test Three BlankIcon Evaluated: Invalid String Entered");

        }

	}
}