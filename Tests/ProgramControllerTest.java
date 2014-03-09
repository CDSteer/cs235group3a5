/** 
 * \file ProgramControllerTest.java 
 *  
 * \author S. Jones & I.C. Skinner 
 *  
 * \date 26 Feb '14 
 *  
 * \brief Unit test class for the ProgramController class. 
 *  
 */
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class ProgramControllerTest {

	@Test
	public void testUpdateColour1Exception() {
		/**  
         * Runs program and sets player one's piece to an image that doesn't exist, 
         * if IOException is caught then test is success  
         */
		ProgramController prgTest = new ProgramController();
		try {
			prgTest.ProgramController();
		} catch (IOException e1) {

		}
		Piece testPiece = new Piece("Blue");
		prgTest.getGame().getBoard().setPiece(testPiece, 0, 0);
		try{
			prgTest.update(prgTest.getGame().getBoard(), "Blue", "Green");
		}catch(IOException e){
			System.out.println("Success");
		}
	}
	
	@Test
	public void testUpdateColour2Exception() {
		/**  
         * Runs program and sets player two's piece to an image that doesn't exist, 
         * if IOException is caught then test is success  
         */
		ProgramController prgTest = new ProgramController();
		try {
			prgTest.ProgramController();
		} catch (IOException e1) {
			
		}
		Piece testPiece = new Piece("Orange");
		prgTest.getGame().getBoard().setPiece(testPiece, 0, 0);
		try{
			prgTest.update(prgTest.getGame().getBoard(), "Blue", "Orange");
		}catch(IOException e){
			System.out.println("Success");
		}
	}
	
	@Test
	public void testUpdateColour1Valid() {
		/**  
         * Runs program and sets player one's piece to an image that does exist, 
         * if update method is called then test is a success 
         */
		ProgramController prgTest = new ProgramController();
		try {
			prgTest.ProgramController();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Piece testPiece = new Piece("Red");
		prgTest.getGame().getBoard().setPiece(testPiece, 0, 0);
		try{
			prgTest.update(prgTest.getGame().getBoard(), "Red", "Blue");
			System.out.println("Success");
		}catch(IOException e){
			System.out.println("Fail");
		}
	}
	
	@Test
	public void testUpdateColour2Valid() {
		/**  
         * Runs program and sets player two's piece to an image that does exist, 
         * if update method is called then test is a success 
         */
		ProgramController prgTest = new ProgramController();
		try {
			prgTest.ProgramController();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Piece testPiece = new Piece("Yellow");
		prgTest.getGame().getBoard().setPiece(testPiece, 0, 0);
		try{
			prgTest.update(prgTest.getGame().getBoard(), "Blue", "Yellow");
			System.out.println("Success");
		}catch(IOException e){
			System.out.println("Fail");
		}
	}
	
	
	
}
