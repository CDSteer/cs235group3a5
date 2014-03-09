/**
 * \file ColourChangeTest.java
 * 
 * \author A. Keskin
 * 
 * \date 27 Feb '14
 * 
 * \brief Unit test class for the ColourChange class.
 * 
 */

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;


public class ColourChangeTest {

	//	Tests whether the black to white gif animation from file equals the gif animation parsed 
	//	out from the ColourChange class.
	public void testFlipBlack() {
		ColourChange c = new ColourChange();
		ImageIcon i = new ImageIcon("Images/BlackToWhitePiece.gif");
		
		assertEquals(i.getImage(), c.flip(new Piece("White")).getImage());
	}
	
	//	Tests whether the white to black gif animation from file equals the gif animation parsed 
	//	out from the ColourChange class.
	public void testFlipWhite() {
		ColourChange c = new ColourChange();
		ImageIcon i = new ImageIcon("Images/WhiteToBlackPiece.gif");
		
		assertEquals(i.getImage(), c.flip(new Piece("Black")).getImage());
	}
	
	//	Tests if an incorrect string is input that it doesn't output one of the gifs and outputs  
	//	a blank ImageIcon.
	public void testFlipElse() {
		ColourChange c = new ColourChange();
		ImageIcon i = new ImageIcon();
		
		assertEquals(i.getImage(), c.flip(new Piece("Blue")).getImage());
	}
	

}
