/**
 * \file Human.java
 * 
 * \author G.D Godfrey
 * 
 * \date 27 Feb '14
 * 
 * \brief Unit test class for the Human class.
 * 
 */
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
 
public class HumanTest {
 
	@Test
	/* Test shows moving piece to position (1,1) at start of game is a valid move. */
	public void test1Connect4() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(0);
		
		boolean validMove = testPlayer.move(1, 1, PC);
		assertEquals(true, validMove);
	}	
	
	@Test
	public void test2Connect4() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(0);
		
		boolean validMove = testPlayer.move(4, 5, PC);
		assertEquals(true, validMove);
	}

	
	/** Test shows a move that is not on board is not a valid move */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test3Connect4() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(0);
		
		boolean validMove = testPlayer.move(12, 12, PC); 
		/* Does not need (assertEquals(false, validMove);) since ArrayIndexOutOfBoundsException is expected. */
	}
	
	/* Test shows a piece can not be placed on another piece*/
	@Test
	public void test4Connect4(){
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * If a row is full then any move made is an invalid move.
		 */
		Piece testRedPiece = new Piece("Red");
		Piece testYellowPiece = new Piece("Yellow");
		
		
		for(int row = 0; row < PC.getGame().getBoard().getBoardHeight(); row++){
			if(row % 2 == 0){
				PC.getGame().getBoard().setPiece(testRedPiece, 0, row);
			}else{
				PC.getGame().getBoard().setPiece(testYellowPiece, 0, row);
			}
		}
		
		boolean validMove = PC.getGame().getPlayer(1).move(0, 0, PC);
		assertEquals(false, validMove);
	}
	
	/* Test a Piece drops at the bottom of the column */
	@Test 
	public void test5Connect4(){
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Piece testRedPiece = new Piece("Red");
		Piece testYellowPiece = new Piece("Yellow");
		
		
		for(int col = 0; col < PC.getGame().getBoard().getBoardHeight(); col++){
			if(col % 2 == 0){
				PC.getGame().getBoard().setPiece(testRedPiece, 0, col);
			}else{
				PC.getGame().getBoard().setPiece(testYellowPiece, 0, col);
			}
		}
		
		boolean validMove = PC.getGame().getPlayer(1).move(3, 6, PC);
		assertEquals(true, validMove);
	}
	
	/** Shows invalid move at start of game. */
	@Test
	public void Test1Othelo() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(0);
		
		boolean validMove = testPlayer.move(3, 0, PC);
		assertEquals(false, validMove);
	}
	
	/** Shows invalid move at start of game. */
	@Test
	public void Test2Othello() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(1);
		
		boolean validMove = testPlayer.move(2, 2, PC);
		assertEquals(false, validMove);
	}
	
	/** Shows valid move at start of game. */
	@Test
	public void Test3Othello() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(1);
		
		boolean validMove = testPlayer.move(4, 2, PC);
		assertEquals(true, validMove);
	}
	
	/** Shows valid move at start of game. */
	@Test
	public void Test4Othello() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(1);
		boolean validMove = testPlayer.move(2, 4, PC);
		assertEquals(true, validMove);
	}
	
	/* Test to ensure player cannot place piece outside the board */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void Test5Othello() {
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player testPlayer = PC.getGame().getPlayer(1);
		
		boolean validMove = testPlayer.move(12, 15, PC);
		/* Does not need (assertEquals(true, validMove);) since ArrayIndexOutOfBoundsException is expected. */
	}
	
	/* Test shows invalid move */
	@Test
	public void Test6othello(){
		ProgramController PC = new ProgramController();
		try {
			PC.ProgramController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * If a row is full then any move made is an invalid move.
		 */
		Piece testBlackPiece = new Piece("Black");
		Piece testWhitePiece = new Piece("White");
		
		
		for(int row = 0; row < PC.getGame().getBoard().getBoardHeight(); row++){
			if(row % 2 == 0){
				PC.getGame().getBoard().setPiece(testBlackPiece, 0, row);
			}else{
				PC.getGame().getBoard().setPiece(testWhitePiece, 0, row);
			}
		}
		
		boolean validMove = PC.getGame().getPlayer(1).move(2, 2, PC);
		assertEquals(false, validMove);
	}
 }