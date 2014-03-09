/**
 * \file Human.java
 *
 * \author G.D. Damabel 
 *
 * \date 24 Feb '14
 *
 * \brief Gets and returns Player move.
 *
 * The Human class is intended to create two players, checks if player move is valid and then 
 * pass the information to the program controller class to set the piece on the game board, 
 * update the game board and checks if that is a winning move.
 */

import java.io.*;

public class Human extends Player{
	/**
	*  Method move is used to make a move by a player at position (x,y).	
	*  \param  	x	Column position on a Board to move Piece.
	*  \param 	y	Row position on a Board to move Piece.
	*  \param  	PC  ProgramController used to get required information.
	*  \return Boolean
	*/
    public boolean move(int x, int y, ProgramController PC){
        Boolean validMove = false;
		
		/* checks if move is valid. if not valid return and do nothing. */
        validMove = (PC.getGame()).checkValid(x, y, this);
        if (validMove == false) {
            return validMove;
        }
        /* Set piece if move is valid */
        PC.getGame().setPiece(x, y, this);
        try {
			/* 
				Move is valid call update with with type of game board and colour
				of piece. 
		   */
             PC.update(((PC.getGame().getBoard())), 
                        PC.getGame().getPlayer(PLAYER_ONE).getColour(), 
                        PC.getGame().getPlayer(PLAYER_TWO).getColour()); 
        } catch (IOException ex) {}
        /* Return true if the player move is valid. */
        return validMove;
    }
	
	/* Symbolic constants */
	
	/* First player at array position one. */
	private final int PLAYER_ONE = 0; 
	/* Second player at array position two. */
	private final int PLAYER_TWO = 1;
}