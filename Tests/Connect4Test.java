/**
 * \file Connect4Test.java
 * 
 * \author I.C. Skinner
 * 
 * \date 26 Feb '14
 * 
 * \brief Unit test class for the Connect4 class.
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class Connect4Test {
	
	@Test
	public void testSetPieceTrue() {
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		testPlayer.setColour("Red");
		
		C4.setPiece(0, 0, testPlayer);
		assertEquals("Red", C4.getBoard().getBoard()[0][6].getColour());
	}
	
	@Test
	public void testSetPieceFalse(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		testPlayer.setColour("Red");

		Piece testPiece = new Piece("Yellow");
		
		/* 
		 * Set the top piece of the board to yellow. This is a quasi-hack because
		 * now the top piece is occupied whilst everyone below it isn't and that 
		 * wouldn't happen in the running implementation but it suffices here
		 * for test purposes.
		 * 
		 */
		C4.getBoard().setPiece(testPiece, 0, 0);
		
		C4.setPiece(0, 0, testPlayer);
		/* 
		 * If top piece is still yellow then setPiece did nothing which is correct.
		 * Basically our implementation tests if a move is valid before setting a piece
		 * and so won't set a piece if it's not valid anyway but we wanted to include
		 * a test to show this.
		 * 
		 */
		assertEquals("Yellow", C4.getBoard().getBoard()[0][0].getColour());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPieceTooBig(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		
		C4.setPiece(42, 42, testPlayer);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPieceTooSmall(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		
		C4.setPiece(-42, -42, testPlayer);
	}
	
	@Test
	public void testMoveIsValidTrue(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		
		C4.checkValid(0, 0, testPlayer);
		assertTrue(true);
	}
	
	@Test
	public void testMoveIsValidFalse(){
		Connect4 C4 = new Connect4();
		Piece testPiece = new Piece("Red");
		Player testPlayer = new Human();
		
		/* 
		 * Set the top piece of row 0 to be occupied 
		 * 
		 */
		C4.getBoard().setPiece(testPiece, 0, 0);
		
		C4.checkValid(0, 0, testPlayer);
		assertFalse(false);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testMoveIsValidTooBig(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		
		C4.checkValid(42, 42, testPlayer);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testMoveIsValidTooSmall(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		
		C4.checkValid(-42, -42, testPlayer);
	}
	
	@Test
	public void checkWinVertical(){
		Connect4 C4 = new Connect4();
		Piece testPiece = new Piece("Red");
		C4.getBoard().setPiece(testPiece, 0, 6);
		C4.getBoard().setPiece(testPiece, 0, 5);
		C4.getBoard().setPiece(testPiece, 0, 4);
		C4.getBoard().setPiece(testPiece, 0, 3);
		
		C4.checkWin();
		assertTrue(true);
	}
	
	@Test
	public void checkWinHorizontal(){
		Connect4 C4 = new Connect4();
		Piece testPiece = new Piece("Red");
		C4.getBoard().setPiece(testPiece, 0, 6);
		C4.getBoard().setPiece(testPiece, 1, 6);
		C4.getBoard().setPiece(testPiece, 2, 6);
		C4.getBoard().setPiece(testPiece, 3, 6);
		
		C4.checkWin();
		assertTrue(true);
	}
	
	@Test
	public void checkWinRightDiagonal(){
		Connect4 C4 = new Connect4();
		Piece testPiece = new Piece("Red");
		C4.getBoard().setPiece(testPiece, 0, 3);
		C4.getBoard().setPiece(testPiece, 1, 4);
		C4.getBoard().setPiece(testPiece, 2, 5);
		C4.getBoard().setPiece(testPiece, 3, 6);
		
		C4.checkWin();
		assertTrue(true);
	}
	
	@Test
	public void checkWinLeftDiagonal(){
		Connect4 C4 = new Connect4();
		Piece testPiece = new Piece("Red");
		C4.getBoard().setPiece(testPiece, 9, 3);
		C4.getBoard().setPiece(testPiece, 8, 4);
		C4.getBoard().setPiece(testPiece, 7, 5);
		C4.getBoard().setPiece(testPiece, 6, 6);
		
		C4.checkWin();
		assertTrue(true);
	}
	
	@Test
	public void checkWinFalse(){
		Connect4 C4 = new Connect4();
		
		/* Board is completely empty */
		C4.checkWin();
		assertFalse(false);
	}
	
	@Test
	public void checkWinDraw(){
		Connect4 C4 = new Connect4();
		Piece testPiece1 = new Piece("Red");
		Piece testPiece2 = new Piece("Yellow");
		int x;
	
		for(x = 0; x < C4.getBoard().getBoardWidth(); x = x + 4){
			for(int y = 0; y < C4.getBoard().getBoardHeight(); y++){
				if((y % 2) == 0){
					C4.getBoard().setPiece(testPiece1, x, y);
					C4.getBoard().setPiece(testPiece1, x + 1, y);
				}else{
					C4.getBoard().setPiece(testPiece2, x, y);
					C4.getBoard().setPiece(testPiece2, x + 1, y);
				}
			}	
		}
		
		for(x = 2; x < C4.getBoard().getBoardWidth(); x = x + 4){
			for(int y = 0; y < C4.getBoard().getBoardHeight(); y++){
				if((y % 2) == 0){
					C4.getBoard().setPiece(testPiece2, x, y);
					C4.getBoard().setPiece(testPiece2, x + 1, y);
				}else{
					C4.getBoard().setPiece(testPiece1, x, y);
					C4.getBoard().setPiece(testPiece1, x + 1, y);
				}	
			}
		}

		C4.checkWin();
		/* false because board is full with no winners*/
		assertFalse(false);
		
		/* This would mean the game is a draw*/
		assertEquals(3, C4.getWinner());
	}
	
	/* 
	 * If you try and pass a false Piece to the setWinner method it does nothing
	 * and so it can't be "failed".
	 */
	@Test
	public void setWinnerFalse(){
		Connect4 C4 = new Connect4();
		Piece testPiece = new Piece("Blue");
		
		C4.setWinner(testPiece);
	}
	
	@Test
	public void setWinnerTrue(){
		Connect4 C4 = new Connect4();
		Piece testRedPiece = new Piece("Red");
		Piece testYellowPiece = new Piece("Yellow");
		
		C4.setWinner(testRedPiece);
		assertEquals(1, C4.getWinner());
		
		C4.setWinner(testYellowPiece);
		assertEquals(2, C4.getWinner());
	}
	
	@Test
	public void checkTakeableTurnTrue(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		
		C4.checkTakeableTurn(testPlayer);
		assertTrue(true);
	}
	
	@Test
	public void checkTakeableTurnFalseWinVertical(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		Piece testPiece = new Piece("Red");

		/* Testing with player 1 */
		C4.getBoard().setPiece(testPiece, 0, 3);
		C4.getBoard().setPiece(testPiece, 0, 4);
		C4.getBoard().setPiece(testPiece, 0, 5);
		C4.getBoard().setPiece(testPiece, 0, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	
		testPiece.changeColour("Yellow");

		/* Testing with player 2 */
		C4.getBoard().setPiece(testPiece, 0, 3);
		C4.getBoard().setPiece(testPiece, 0, 4);
		C4.getBoard().setPiece(testPiece, 0, 5);
		C4.getBoard().setPiece(testPiece, 0, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	}
	
	@Test
	public void checkTakeableTurnFalseWinHorizontal(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		Piece testPiece = new Piece("Red");
		
		/* Testing with player 1 */
		C4.getBoard().setPiece(testPiece, 9, 6);
		C4.getBoard().setPiece(testPiece, 8, 6);
		C4.getBoard().setPiece(testPiece, 7, 6);
		C4.getBoard().setPiece(testPiece, 6, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	
		testPiece.changeColour("Yellow");
		
		/* Testing with player 2 */
		C4.getBoard().setPiece(testPiece, 9, 6);
		C4.getBoard().setPiece(testPiece, 8, 6);
		C4.getBoard().setPiece(testPiece, 7, 6);
		C4.getBoard().setPiece(testPiece, 6, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	}
	
	@Test
	public void checkTakeableTurnFalseWinRightDiagonal(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		Piece testPiece = new Piece("Red");
		
		/* Testing with player 1 */
		C4.getBoard().setPiece(testPiece, 0, 3);
		C4.getBoard().setPiece(testPiece, 1, 4);
		C4.getBoard().setPiece(testPiece, 2, 5);
		C4.getBoard().setPiece(testPiece, 3, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	
		testPiece.changeColour("Yellow");
		
		/* Testing with player 2 */
		C4.getBoard().setPiece(testPiece, 0, 3);
		C4.getBoard().setPiece(testPiece, 1, 4);
		C4.getBoard().setPiece(testPiece, 2, 5);
		C4.getBoard().setPiece(testPiece, 3, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	}

	@Test
	public void checkTakeableTurnFalseWinLeftDiagonal(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		Piece testPiece = new Piece("Red");
		
		/* Testing with player 1 */
		C4.getBoard().setPiece(testPiece, 9, 3);
		C4.getBoard().setPiece(testPiece, 8, 4);
		C4.getBoard().setPiece(testPiece, 7, 5);
		C4.getBoard().setPiece(testPiece, 6, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	
		testPiece.changeColour("Yellow");
		
		/* Testing with player 2 */
		C4.getBoard().setPiece(testPiece, 9, 3);
		C4.getBoard().setPiece(testPiece, 8, 4);
		C4.getBoard().setPiece(testPiece, 7, 5);
		C4.getBoard().setPiece(testPiece, 6, 6);
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	}
	
	@Test
	public void checkTakeableTurnFalseDraw(){
		Connect4 C4 = new Connect4();
		Player testPlayer = new Human();
		Piece testPiece1 = new Piece("Red");
		Piece testPiece2 = new Piece("Yellow");
		
		int x;
		
		for(x = 0; x < C4.getBoard().getBoardWidth(); x = x + 4){
			for(int y = 0; y < C4.getBoard().getBoardHeight(); y++){
				if((y % 2) == 0){
					C4.getBoard().setPiece(testPiece1, x, y);
					C4.getBoard().setPiece(testPiece1, x + 1, y);
				}else{
					C4.getBoard().setPiece(testPiece2, x, y);
					C4.getBoard().setPiece(testPiece2, x + 1, y);
				}
			}	
		}
		
		for(x = 2; x < C4.getBoard().getBoardWidth(); x = x + 4){
			for(int y = 0; y < C4.getBoard().getBoardHeight(); y++){
				if((y % 2) == 0){
					C4.getBoard().setPiece(testPiece2, x, y);
					C4.getBoard().setPiece(testPiece2, x + 1, y);
				}else{
					C4.getBoard().setPiece(testPiece1, x, y);
					C4.getBoard().setPiece(testPiece1, x + 1, y);
				}	
			}
		}
		
		C4.checkTakeableTurn(testPlayer);
		assertFalse(false);
	}
	
}
