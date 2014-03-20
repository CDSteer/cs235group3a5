/**
 * @file Connect4.java
 *
 * @author I.C. Skinner
 *
 * @date 18 Feb '14
 * 
 * @see GameImplementation.java
 *
 * @brief This class controls the Connect4 logic.
 *
 *	This class controls the 'flow' of the game, specific to Connect4 such as 
 *	checking if there are 4 of the same coloured pieces in a vertical, 
 *	horizontal or diagonal line and setting the winning player accordingly.
 */	
public class Connect4 extends GameImplementation{

	/** 
	 *	A 'get' (access) method for the winning move.
	 *	@return	m_Winning_Move	Integer representing a winning connect4 move.
	 */
	public int getWinningMove(){
		return m_Winning_Move;
	}

	/** 
	 *	A setter method for the winning move. 
	 *	@param	winningMove		Integer representing a winning connect4 move.
	 *	@return null
	 */
	private void setWinningMove(int winningMove){
		m_Winning_Move = winningMove;
	}
	
	/** 
	 *	A 'get' (access) method for the winning column (i) position 
	 * 	@return	m_Winning_i	Row position of a winning piece. 
	 */
	public int getWinningi(){
		return m_Winning_i;
	}
	
    /** 
	 *	A setter method for the winning column (i) position 
	 *  @param i			Row position of a winning piece.
	 *	@return null
	 */
	private void setWinningi(int i){
		m_Winning_i = i;
	}
	
	/** 
	 *	A 'get' (access) method for the winning row (j) position 
	 *	@return	j	Column position of a winning piece.
	 */
	public int getWinningj(){
		return m_Winning_j;
	}
	
	/** 
	 *	A setter method for the winning row (j) position 
	 *	@param j			Column position of a winning piece.
	 *	@return null
	 */
	private void setWinningj(int j){
		m_Winning_j = j;
	}

	/** 
	 *	A 'get' (access) method to get the piece at coordinates (column,row).
	 *	@return	Piece	Existing or new Piece if position is not empty or empty.
	 */
	public Piece getPiece(int column, int row) { 
		if (getBoard().isEmpty(column,row) == true) {
			return new Piece("");
		} else {
			return getBoard().getBoard()[column][row];
		}
	}
	
	/** 
	 *	A setter method which sets a piece object at a position. 
	 *	@param	column	Column position on a board.
	 *	@param	row		Row Position on a board.
	 *	@param	player	Player object which is the player setting a piece.
	 *	@return null
	 */
	public void setPiece(int column, int row, Player player){
        System.out.println("Connect4::setPiece()");
        int rowIndex = BOARD_HEIGHT-1;
        boolean columnNotFull = true;
        while(columnNotFull){
			
			/* Column is full, don't set piece. (Shouldn't be reachable) */
            if(rowIndex < TOP_ROW){
                columnNotFull = false;
				
			/* Set the piece at board position [column][rowIndex]*/	
            }else if(getBoard().isEmpty(column,rowIndex) == true){
                String playerPiece;
                if(player.getColour().equals(PLAYER_ONE_PIECE_COLOUR)){
                    playerPiece = PLAYER_ONE_PIECE_COLOUR;
				}else{
                    playerPiece = PLAYER_TWO_PIECE_COLOUR;
				}
                (getBoard()).setPiece(new Piece(playerPiece), column, rowIndex);
                columnNotFull = false;
				
			/* Do nothing which should never be achieved */
            }else{}
            rowIndex--;
        }
    }
	
	/** 
	 *	A setter method which sets the winner games winner. 
	 *	@param	p	Piece object used by the user.
	 *	@return null
	 */
	public void setWinner(Piece p) { 
		if (getPlayer(PLAYER_ONE).getColour().equals(p.getColour())) {
			super.setWinner(PLAYER_ONE_PIECE);
		} else if (getPlayer(PLAYER_TWO).getColour().equals(p.getColour())) {
			super.setWinner(PLAYER_TWO_PIECE);
		}
	}
	
	/**
	 *	Connect 4 constructor 
	 */
	public Connect4(){
	
		/* Set the board given the symbolic constants */
        this.setBoard(BOARD_WIDTH, BOARD_HEIGHT);
		
		/* Set player 1 piece colour to "Red" */ 
        getPlayer(PLAYER_ONE).setColour(PLAYER_ONE_PIECE_COLOUR);
		
		/* Set player 2 piece colour to "Yellow" */
        getPlayer(PLAYER_TWO).setColour(PLAYER_TWO_PIECE_COLOUR);
    }
	
	/**	
	 *	CheckTakeableTurn is a method used primarily for Othello however this 
		override is necessary so that the moment the player enters their 
		winning piece the game is done and doesn't require an additional click
		to display winner.
	 *	@param	player	Player object which is the player to take a turn.
	 *	@return boolean	Returns true or false depending on conditions.
	 */
	public boolean checkTakeableTurn(Player player) {
		if(checkWin() == true){
			return false;
		}else{
			return true;
		}
	}
    
	
	/** 
	 * 	checkValid method checks to see if the players attempted move is valid.
	 *	@param	column	Column position of the attempted played piece.
	 *	@param	row		Row position of the attempted played piece.
	 *	@param	player	Player object which is the player attempting a move.
	 *	@return	boolean	Returns true of false based depending on conditions.
	 */
	public boolean checkValid(int column, int row, Player player){
        System.out.println("Connect4::checkValid()");
		
		/* if the top of the column is not empty then the move is invalid */
        if((getBoard()).isEmpty(column,TOP_ROW) == false){
			System.out.println("Invalid move");
            return false;
        }else{
			
            return true;
        }
    }
	
	/** 
	 * 	checkWin method reads the data on the board and determines if there's a winner 
		in 1 of 4 ways:
			-- Horizontal connect 4
			-- Vertical connect 4
			-- Diagonal right-facing connect 4
			-- Diagonal left-facing connect 4
	 *	@return	boolean	Returns true or false if there is a winner or not.
	 */
    public boolean checkWin(){
        int j;
        int i;
        
        for(j = 0; j<(BOARD_HEIGHT); j++){
            for(i = 0; i<(BOARD_WIDTH-3); i++){ /* horizontal win */
                if(getPiece(i,j).getColour() == getPiece(i+1,j).getColour() &&
                   getPiece(i,j).getColour() == getPiece(i+2,j).getColour() && 
                   getPiece(i,j).getColour() == getPiece(i+3,j).getColour() && 
                   getBoard().isEmpty(i,j) == false){  
					setWinner(getPiece(i,j));
					setWinningi(i);
					setWinningj(j);
					setWinningMove(HORIZONTAL_WIN);
					return true;
                }
			}
		}
       
		for(i = 0; i<(BOARD_WIDTH); i++){ /* vertical win */
			for(j = 0; j<(BOARD_HEIGHT-3); j++){
				if(getPiece(i,j).getColour() == getPiece(i,j+1).getColour() && 
                   getPiece(i,j).getColour() == getPiece(i,j+2).getColour() && 
                   getPiece(i,j).getColour() == getPiece(i,j+3).getColour() && 
                   getBoard().isEmpty(i,j) == false){
					setWinner(getPiece(i,j));
					setWinningi(i);
					setWinningj(j);
					setWinningMove(VERTICAL_WIN);
					return true;
				}
			}
		}
       
		for(j = 0; j<(BOARD_HEIGHT-3); j++){ /* diagonal right facing win */
			for(i = 0; i<(BOARD_WIDTH-3); i++){
				if(getPiece(i,j).getColour() == getPiece(i+1,j+1).getColour() && 
				   getPiece(i,j).getColour() == getPiece(i+2,j+2).getColour() && 
				   getPiece(i,j).getColour() == getPiece(i+3,j+3).getColour() &&
                   getBoard().isEmpty(i,j) == false){ 
					setWinner(getPiece(i,j));
					setWinningi(i);
					setWinningj(j);
					setWinningMove(RIGHT_DIAGONAL_WIN);
					return true;
				}
			}
		}
       
		for(j = 0; j<(BOARD_HEIGHT-3); j++){ /* diagonal left facing win */
			for(i = 3; i<(BOARD_WIDTH); i++){
				if(getPiece(i,j).getColour() == getPiece(i-1,j+1).getColour() && 
				   getPiece(i,j).getColour() == getPiece(i-2,j+2).getColour() && 
                   getPiece(i,j).getColour() == getPiece(i-3,j+3).getColour() && 
                   getBoard().isEmpty(i,j) == false){
					setWinner(getPiece(i,j));
					setWinningi(i);
					setWinningj(j);
					setWinningMove(LEFT_DIAGONAL_WIN);
					return true;
				}
			}
		}
       
		boolean boardFull = isBoardFull(BOARD_WIDTH, BOARD_HEIGHT);
		if(boardFull == true){
			super.setWinner(DRAW); /* draw if board is full */
		}else{} 
		return boardFull;
	}
 
    /*
    public static void main(String[] args){
		// Testing
    }
    */
	
	/** Member variables to store the winning move and position */
	private int m_Winning_Move;
	private int m_Winning_i;
	private int m_Winning_j;
	
	/** Symbolic constants */
	private final int DRAW = 3;
	
	private final int TOP_ROW = 0;
	
	private final int PLAYER_ONE = 0;
	private final int PLAYER_TWO = 1;
	
	private final int HORIZONTAL_WIN = 0;
	private final int VERTICAL_WIN = 1;
	private final int RIGHT_DIAGONAL_WIN = 2;
	private final int LEFT_DIAGONAL_WIN = 3;
	
	private final int PLAYER_ONE_PIECE = 1;
	private final String PLAYER_ONE_PIECE_COLOUR = "Red";
	private final int PLAYER_TWO_PIECE = 2;
	private final String PLAYER_TWO_PIECE_COLOUR = "Yellow";
	
    private final int BOARD_HEIGHT = 7;
    private final int BOARD_WIDTH = 10;
}
