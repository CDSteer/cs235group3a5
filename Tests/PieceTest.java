/**
 * \file PieceTest.java
 * 
 * \author A. Alakeel
 * 
 * \date 27 Feb '14
 * 
 * \brief Unit test class for the Piece class.
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class PieceTest {

	@Test 
	public void testRedColour(){
		Piece testPiece = new Piece("Red");
		
		assertEquals("Red", testPiece.getColour());
	}
	
	@Test
	public void testYellowColour(){
		Piece testPiece = new Piece("Yellow");
		
		assertEquals("Yellow", testPiece.getColour());
	}
	
	@Test
	public void testBlueColour(){
		Piece testPiece = new Piece("Black");
		
		assertEquals("Black", testPiece.getColour());
	}

	@Test
	public void testBlueColour(){
		Piece testPiece = new Piece("White");
		
		assertEquals("White", testPiece.getColour());
	}
}
