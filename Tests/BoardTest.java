/**
 * \file BoardTest.java
 * 
 * \author X. Chen
 * 
 * \date 27 Feb '14
 * 
 * \brief Unit test class for the Board class.
 * 
 */


import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {
	
	@Test
	// Set board with normal sizes.
	public void testSetBoard() {
		Board b = new Board();
		b.setBoard(8, 8);
		// get the right size values.
		assertEquals(8, b.getBoardWidth());
		assertEquals(8, b.getBoardHeight());
	}
	
	@Test
	// Set board with no size.
	public void testSetBoardSmaller() {
		Board b = new Board();
		b.setBoard(0, 0);
		// get the right size values.
		assertEquals(0, b.getBoardWidth());
		assertEquals(0, b.getBoardHeight());
	}
	
	@Test
	// Set board with sizes very big.
	public void testSetBoardBigger() {
		Board b = new Board();
		b.setBoard(800, 800);
		// get the right size values.
		assertEquals(800, b.getBoardWidth());
		assertEquals(800, b.getBoardHeight());
	}
	
	@Test(expected = NegativeArraySizeException.class)
	// Set board with negative sizes which is impossible.
	public void testSetBoardNegative() {
		Board b = new Board();
		b.setBoard(-1, -1);
		// so there is no board here.
		assertEquals(0, b.getBoardWidth());
		assertEquals(0, b.getBoardHeight());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	// create a board and try to set piece outside the board.
	public void testSetPieceOutBoard(){
		Board b = new Board();
		b.setBoard(8, 8);
		Piece p = new Piece("black");
		b.setPiece(p, 10, 10);
		// maybe it just stores the data. no change on the board.
		assertEquals("black", b.getBoard()[10][10].getColour());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	// set piece in a negative position which is not exist.
	public void testSetPieceNegative(){
		Board b = new Board();
		b.setBoard(8, 8);
		Piece p = new Piece("black");
		b.setPiece(p, -1, -1);
		// maybe it just stores the data. no change on the board.
		assertEquals("black", b.getBoard()[-1][-1].getColour());
	}
	
	
	@Test
	// set a black piece on the edge of the board 
	public void testSetPiece(){
		Board b = new Board();
		b.setBoard(8, 8);
		Piece p = new Piece("black");
		b.setPiece(p, 0, 7);
		// yeah! there is a black piece!
		assertEquals("black", b.getBoard()[0][7].getColour());
	}
	
	@Test
	/** set a black piece on the board
	 *  then set a white piece at the same position
	 *  please do not override it !  
	 */
	public void testReplacePiece(){
		Board b = new Board();
		b.setBoard(8, 8);
		// set a black piece on column 0, row 7.
		Piece p1 = new Piece("black");
		b.setPiece(p1, 0, 7);
		// set a white piece again on column 0, row 7.
		Piece p2 = new Piece("white");
		b.setPiece(p2, 0, 7);
		// you cannot put the white piece here !!!
		assertEquals("black", b.getBoard()[0][7].getColour());
	}
	
	@Test
	// set a board with nothing.
	public void testIsEmptyTrue(){
		Board b = new Board();
		b.setBoard(8, 8);
		// check position (0,7), it is empty.
		assertTrue(b.isEmpty(0, 7));
	}
	
	@Test
	// set a board with nothing.
	public void testIsEmptyFalse(){
		Board b = new Board();
		b.setBoard(8, 8);
		// set a piece on column 4, row 5.
		Piece p = new Piece("black");
		b.setPiece(p, 4, 5);
		// then check the position (4,5) which is not empty.
		assertEquals(false, b.isEmpty(4, 5));
		// check the position (0,7) which is empty no piece.
		assertTrue(b.isEmpty(0, 7));
	}
	// thanks for watching !    /smile/ 
}
