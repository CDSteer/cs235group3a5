/**
 * \file OthelloTest.java
 * 
 * \author J. Bailey
 * 
 * \date 26 Feb '14
 * 
 * \brief Unit test class for the Othello class.
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class OthelloTest {

	// Test that placing a piece in what should be a correct place,
	// actually places it in the board
	@Test
	public void testSetPieceCorrect() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// set piece at (4,2). Should be true.
		o.setPiece(4,2,testPlayer);
		assertEquals("Black", o.getBoard().getBoard()[4][2].getColour());
	}
	
	// Test that placing a piece in what should be an incorrect place,
	// doesn't place it on the board
	@Test
	public void testSetPieceBadMove() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// set piece at (0,2). Should not place piece.
		o.setPiece(0,2,testPlayer);
		assertEquals(" ", o.getBoard().getBoard()[0][2].getColour());
	}

	// Test that placing a piece in a position that doesn't exist,
	// doesn't allow the program to do that.
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPieceOutOfBoard() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// set piece at (1000,1000). Should not place piece.
		o.setPiece(1000,1000,testPlayer);
	}
	
	// Test that placing a piece in a negative position,
	// doesn't allow  the program to do that.
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSetPieceMinusNumbers() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// set piece at (-1,-1). Should not place piece.
		o.setPiece(-1,-1,testPlayer);
	}
	
	// Test that placing a piece in a place where a piece,
	// already exists doesn't override it.
	@Test
	public void testSetPieceReplace() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		Player testPlayerTwo = new Human();
		testPlayerTwo.setColour("White");
		
		// Set black piece at (4,2). Set white piece on top.
		// White piece should not get set
		o.setPiece(4, 2, testPlayer);
		o.setPiece(4, 2, testPlayerTwo);
		assertEquals("Black", o.getBoard().getBoard()[4][2].getColour());
	}
	
	// Test that creating a board with the normal dimensions is possible.
	@Test
	public void testSetBoard() {
		Othello o = new Othello();
		
		// Set board with normal sizes
		o.setBoard(8, 8);
	}
	
	// Test that creating a board with larger dimensions.
	@Test
	public void testSetBoardBigger() {
		Othello o = new Othello();
		
		// Set board with sizes too big.
		o.setBoard(10, 80);
		
		// This passes, but is that bad, it just means you can play on a
		// bigger board if you pass one in...
	}
	
	// Test that creating a board with small dimensions.
	@Test
	public void testSetBoardSmaller() {
		Othello o = new Othello();
		
		// Set board with sizes too small.
		o.setBoard(2, 2);
	}

	// Test that creating a board with small dimensions.
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	// I am expecting this to be ArrayIndexOutOfBounds, as it cannot place
	// the 4 pieces into the centre of the board
	public void testSetBoardTooSmaller() {
		Othello o = new Othello();
		
		// Set board with sizes too small.
		o.setBoard(1, 1);
	}
	
	// Test that you cannot make a board with a negative size
	@Test(expected = NegativeArraySizeException.class)
	public void testSetBoardNegative() {
		Othello o = new Othello();
		
		// Set board with negative sizes.
		o.setBoard(-1, -1);
	}
	
	// Test that there is no winner in a game with no moves played.
	@Test
	public void testGetWinner() {
		Othello o = new Othello();
		
		assertEquals(-1, o.getWinner());
		// -1 stands for the game is not finished
	}
	
	// Test that player1 is a winner if there are more black pieces
	@Test
	public void testGetWinnerP1Win() {
		Othello o = new Othello();		
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				o.getBoard().setPiece(new Piece("Black"), i, j);
			}
		}

		assertEquals(1, o.getWinner());
		// 1 means position 0 in the player array 
	}
	
	// Test that player2 is a winner if there are more white pieces
	@Test
	public void testGetWinnerP2Win() {
		Othello o = new Othello();		
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				o.getBoard().setPiece(new Piece("White"), i, j);
			}
		}

		assertEquals(2, o.getWinner());
		// 2 means position 1 in the player array 
	}
	
	// Test that swapping the colours still returns the right winner
	@Test
	public void testGetWinnerColourChange() {
		Othello o = new Othello();		
		o.getPlayer(0).setColour("White");
		o.getPlayer(1).setColour("Black");
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				o.getBoard().setPiece(new Piece("Black"), i, j);
			}
		}

		assertEquals(2, o.getWinner());
		// 2 means position 1 in the player array 
	}
	
	// Test that it is a draw if both players have the same number of pieces
	@Test
	public void testGetWinnerDraw() {
		Othello o = new Othello();		
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (j < 4) {
					o.getBoard().setPiece(new Piece("Black"), i, j);
				} else {
					o.getBoard().setPiece(new Piece("White"), i, j);
				}
			}
		}

		assertEquals(3, o.getWinner());
		// 3 means it's a draw
	}
	
	// Test that there is a takeable turn at the start of the game.
	@Test
	public void testTakeableTurnAtStart() {
		Othello o = new Othello();
		Player testPlayer = o.getPlayer(0);
				
		assertTrue(o.checkTakeableTurn(testPlayer));
	}
	
	// Test that there is no takeable turn if the board is full.
	@Test
	public void testTakeableTurnWithFullBoard() {
		Othello o = new Othello();
		Player testPlayer = o.getPlayer(0);
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				o.getBoard().setPiece(new Piece("Black"), i, j);
			}
		}

		assertFalse(o.checkTakeableTurn(testPlayer));
	}
	
	// Test there is no takeable turn.
	@Test
	public void testTakeableTurnWithSituation() {
		Othello o = new Othello();
		Player testPlayer = o.getPlayer(0);
		
		o.getBoard().setPiece(new Piece("Black"), 3, 3);
		o.getBoard().setPiece(new Piece("Black"), 4, 3);
		o.getBoard().setPiece(new Piece("Black"), 5, 3);
		o.getBoard().setPiece(new Piece("Black"), 3, 4);
		o.getBoard().setPiece(new Piece("White"), 4, 4);
		o.getBoard().setPiece(new Piece("Black"), 5, 4);
		o.getBoard().setPiece(new Piece("Black"), 3, 5);
		o.getBoard().setPiece(new Piece("Black"), 4, 5);
		o.getBoard().setPiece(new Piece("Black"), 5, 5);

		assertFalse(o.checkTakeableTurn(testPlayer));
	}

	// Test there is a takeable turn.
	@Test
	public void testTakeableTurnWithSituation2() {
		Othello o = new Othello();
		Player testPlayer = o.getPlayer(1);
		
		o.getBoard().setPiece(new Piece("Black"), 3, 3);
		o.getBoard().setPiece(new Piece("Black"), 4, 3);
		o.getBoard().setPiece(new Piece("Black"), 5, 3);
		o.getBoard().setPiece(new Piece("Black"), 3, 4);
		o.getBoard().setPiece(new Piece("White"), 4, 4);
		o.getBoard().setPiece(new Piece("Black"), 5, 4);
		o.getBoard().setPiece(new Piece("Black"), 3, 5);
		o.getBoard().setPiece(new Piece("Black"), 4, 5);
		o.getBoard().setPiece(new Piece("Black"), 5, 5);

		assertTrue(o.checkTakeableTurn(testPlayer));
	}
	
	// Test checkWin returns false in the starting position.
	@Test
	public void testCheckWinStart() {
		Othello o = new Othello();

		assertFalse(o.checkWin());
	}
	
	// Test checkWin returns true in a normal winning situation.
	@Test
	public void testCheckWinFinish() {
		Othello o = new Othello();
		
		o.getBoard().setPiece(new Piece("Black"), 2, 4);
		o.getBoard().setPiece(new Piece("Black"), 3, 3);
		o.getBoard().setPiece(new Piece("Black"), 3, 4);
		o.getBoard().setPiece(new Piece("Black"), 3, 5);
		o.getBoard().setPiece(new Piece("Black"), 4, 2);
		o.getBoard().setPiece(new Piece("Black"), 4, 3);
		o.getBoard().setPiece(new Piece("Black"), 4, 4);
		o.getBoard().setPiece(new Piece("Black"), 4, 5);
		o.getBoard().setPiece(new Piece("Black"), 4, 6);
		o.getBoard().setPiece(new Piece("Black"), 5, 3);
		o.getBoard().setPiece(new Piece("Black"), 5, 4);
		o.getBoard().setPiece(new Piece("Black"), 5, 5);
		o.getBoard().setPiece(new Piece("Black"), 6, 4);

		assertTrue(o.checkWin());
	}
	
	// Test checkWin returns false in an unfinished game.
	@Test
	public void testCheckWinUnfinished() {
		Othello o = new Othello();
			
		o.getBoard().setPiece(new Piece("Black"), 4, 0);
		o.getBoard().setPiece(new Piece("Black"), 4, 1);
		o.getBoard().setPiece(new Piece("Black"), 4, 2);
		o.getBoard().setPiece(new Piece("Black"), 6, 2);
		o.getBoard().setPiece(new Piece("White"), 3, 3);
		o.getBoard().setPiece(new Piece("White"), 4, 3);
		o.getBoard().setPiece(new Piece("Black"), 5, 3);
		o.getBoard().setPiece(new Piece("Black"), 6, 3);
		o.getBoard().setPiece(new Piece("White"), 2, 4);
		o.getBoard().setPiece(new Piece("Black"), 3, 4);
		o.getBoard().setPiece(new Piece("Black"), 4, 4);
		o.getBoard().setPiece(new Piece("Black"), 6, 4);
		o.getBoard().setPiece(new Piece("Black"), 2, 5);
		o.getBoard().setPiece(new Piece("White"), 3, 5);
		o.getBoard().setPiece(new Piece("White"), 4, 5);
		o.getBoard().setPiece(new Piece("White"), 5, 5);
		o.getBoard().setPiece(new Piece("White"), 6, 5);
		o.getBoard().setPiece(new Piece("White"), 4, 6);

		assertFalse(o.checkWin());
	}
	
	//ChackValid
	// On set piece
	// Next to same colour
	// In correct situation
	// 
	
	// Test that placing a piece in what should be a correct place,
	// actually places it in the board
	@Test
	public void testCheckValidCorrect() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// checkValid at (4,2). Should be true, as next to top right starting piece.
		assertTrue(o.checkValid(4,2,testPlayer));
	}
	
	// Test that placing a piece in what should be an incorrect place,
	// doesn't place it on the board
	@Test
	public void testCheckValidBadMove() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// set piece at (0,2). Should not be valid, as not next to piece.
		assertFalse(o.checkValid(0,2,testPlayer));
	}

	// Test that placing a piece in a position that doesn't exist,
	// doesn't allow the program to do that.
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testCheckValidOutOfBoard() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// check valid at (1000,1000). Should not allow to check here,
		// as it doesn't exist
		o.checkValid(1000,1000,testPlayer);
	}
	
	// Test that placing a piece in a negative position,
	// doesn't allow  the program to do that.
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testCheckValidMinusNumbers() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		
		// set piece at (-1,-1). Should not place piece.
		o.checkValid(-1,-1,testPlayer);
	}
	
	// Test that placing a piece in a place where a piece,
	// already exists doesn't override it.
	@Test
	public void testCheckValidOnExistingPiece() {
		Othello o = new Othello();
		Player testPlayer = new Human();
		testPlayer.setColour("Black");
		Player testPlayerTwo = new Human();
		testPlayerTwo.setColour("White");
		
		// Set black piece at (4,2). Set white piece on top.
		// White piece should not get set
		o.setPiece(4, 2, testPlayerTwo);
		assertFalse(o.checkValid(4,2,testPlayer));
	}
}
