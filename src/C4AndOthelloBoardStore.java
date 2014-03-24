/**
 * \file Board.java
 *
 * \author X. Chen
 *
 * \date 22 Feb '14
 *
 * \brief Stores information about a Piece object.
 *
 * Stores a piece object in a specific board position by having 2 identical
 * 2D arrays where one stores an integer which corresponds to the other having
 * a piece at the same index location.
 *
 */
public class C4AndOthelloBoardStore {

    /** Method to get the 2D array (board) of pieces */
    public Piece[][] getBoard() {
        return m_Pieces;
    }

    /**
     * Returns the heiht of the board
     * @return the m_BoardHeight
     */
    public int getBoardHeight(){
        return m_BoardHeight;
    }

    /** Method to set the boards height */
    public void setBoardHeight(int boardHeight){
        m_BoardHeight = boardHeight;
    }

    /**
     * Returns the width of the board
     * @return the m_BoardWidth
     */
    public int getBoardWidth(){
        return m_BoardWidth;
    }

    /** Method to set the boards width */
    public void setBoardWidth(int boardWidth){
        m_BoardWidth = boardWidth;
    }


    /**
     *  Method to "setBoard" which instantiate both 2D arrays
     *  Initializes board for given board size values
     *  @param set the the Width of the board
     *  @param set the the height of the board
     */
    public void setBoard(int boardWidth, int boardHeight) {
        setBoardHeight(boardHeight);
        setBoardWidth(boardWidth);
        m_Board = new int[boardWidth][boardHeight];
        m_Pieces = new Piece[boardWidth][boardHeight];
        for(int j = 0; j < boardHeight; j++){
            for(int i = 0; i < boardWidth; i++){
                m_Pieces[i][j] = new Piece(" ");
            }
        }
    }

    /**
     * Method to set a piece in both arrays
     * initializes piece for given position values and colour
     * @param set the the colour of the piece
     * @param set the the row number of the piece
     * @param set the the column number of the piece
     */
    public void setPiece(Piece piece, int column, int row) {
        System.out.println("Board::setPiece()");
        if(m_Board[column][row] != HAS_PIECE_OBJECT){
            m_Board[column][row] = HAS_PIECE_OBJECT;
            m_Pieces[column][row] = piece;
        }
    }

    /**
     *  Method to check if a board has a piece object in it i.e. is not empty
     */
    public boolean isEmpty(int column, int row) {
        return m_Board[column][row] != HAS_PIECE_OBJECT;
    }

    /**
     * Member variables to store both 2D arrays and the boards height and width
     */
    private int[][] m_Board;
    private Piece[][] m_Pieces;
    private int m_BoardWidth;
    private int m_BoardHeight;

    /** Symbolic constant */
    private final int HAS_PIECE_OBJECT = 1;
}
