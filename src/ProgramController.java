/**
 * @file ProgramController.java
 *
 * @author I.C. Skinner & J. Bailey & S. Jones
 *
 * @date 25 Feb '14
 *
 * @brief This is the GUI for both games.
 *
*/

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ProgramController extends JFrame implements MouseListener, ActionListener{

	/** Method to get the GameImplementation member variable m_Game
	 * @return m_game -game being played
	 */
	public AbstractGameImplementation getGame(){
       return m_Game;
   }

	/** Getter method to get the variable m_Time
	 * @return m_Time -int that stores time elapsed
	 */
	public int getTime() {
		return m_Time;
	}

	/** Getter method to get the variable m_Turn
	 * @return m_Turn -int that stores who's turn it is
	 */
	public int getTurn() {
		return m_Turn;
	}

	/** Getter method to get the object m_Board
	 * @return m_Board -object from board class
	 */
	public C4AndOthelloBoardStore getBoard(){
		return m_Board;
	}

	/** Method to get the boolean m_IsC4.
	 * @return m_isC4 -boolean variable that stores which game's being played
	 */
	private boolean getIsC4(){
		return m_IsC4;
	}

	/**
	 *	Sets connect4 option to be true
	 *	@param userOption -int value given by dialog frame at start of the program
	*/
	private void setIsC4(int userOption){
		if(userOption == 0){
			m_IsC4 = true;
		}else if(userOption == 1){
			m_IsC4 = false;
    }else{
      System.out.println("Program exited");
			System.exit(1);
    }
	}


	/**
	 *	Method to set the GameImplementation member variable m_Game that also
	 *	calls setBGImage.
	 *	@param player1 -stores name of player 1
	 *	@param player2 -stores name of player 2
	*/
	private void setGame(String player1, String player2){
		File background_File;
		if(getIsC4() == true){
			m_Game = new Connect4GameLogic();
			background_File = new File("../Images/Connect4Background.png");
		}else{
			m_Game = new OthelloGameLogic();
			background_File = new File("../Images/OthelloBackground.png");
		}
		try{
			setBGImage(background_File);
		}catch(IOException e){
			System.out.println("IOException error @ ProgramController::setGame()");
		}
		getGame().getPlayer(PLAYER_ONE).setName(player1);
		getGame().getPlayer(PLAYER_TWO).setName(player2);

	}


	/**
	 *	Create New game button
	 *	@return null
	 */
	private void setNewGameButton(){
		m_NewGameButton = new JButton("New Game");
		m_Constraints.gridy = getBoard().getBoardHeight() + 2;
		m_Constraints.gridx = getBoard().getBoardWidth() / 2 - 1;
		m_Constraints.gridwidth = 2;
		m_Container.add(m_NewGameButton, m_Constraints);
		m_NewGameButton.addActionListener(this);
	}

	/**
	 *	Create label to display name of current player
	 *	@return null
	 */
	private void setTurnLabel(){
		m_TurnLabel = new JLabel(m_Game.getPlayer(PLAYER_ONE).getName() + "'s turn");
		System.out.println(m_Game.getPlayer(PLAYER_ONE).getName());
		m_Constraints.gridy = getBoard().getBoardHeight() + 1;
		m_Constraints.gridx = (getBoard().getBoardWidth() / 2) - 4;
		m_Constraints.gridwidth = 8;
		m_Container.add(m_TurnLabel, m_Constraints);
	}

	/**
	 *	Create label to display time elapsed
	 *	@return null
	 */
	private void setTimerLabel(){
		m_TimerLabel = new JLabel("Time elapsed: 0s");
		m_Constraints.gridy = getBoard().getBoardHeight() + 2;
		m_Constraints.gridx = getBoard().getBoardWidth() / 2 + 1;
		m_Constraints.gridwidth = 10;
		m_Container.add(m_TimerLabel, m_Constraints);
	}

	/**
	 *	Create label to display number of turns taken
	 *	@return null
	 */
	private void setTurnNumberLabel(String labelString){

		m_TurnNumberLabel = new JLabel(labelString);
		m_Constraints.gridy = getBoard().getBoardHeight() + 2;
		m_Constraints.gridx = getBoard().getBoardWidth() / 2 - 4;
		m_Constraints.gridwidth = 2;
		m_Container.add(m_TurnNumberLabel, m_Constraints);
	}
	/**
	 *	Create container to hold UI elements
	 *	@return null
	 */
	private void setContainer(){
		m_Container = getContentPane();
    m_Container.setLayout(new GridBagLayout());
		m_Constraints = new GridBagConstraints();
		this.setMinimumSize(new Dimension(64 * getBoard().getBoardWidth(), 64 * getBoard().getBoardHeight() + 22));
	}

	/** Getter method to return timer label value
	 * 	@return m_TimerLabel -JLabel object
	 */
	private JLabel getTimerLabel(){
		return m_TimerLabel;
	}

	/** Getter method to return turn label value
	 * 	@return m_TurnLabel -JLabel object
	 */
	private JLabel getTurnLabel(){
		return m_TurnLabel;
	}

	/**
	 *	Setter method to set current time elapsed value
	 * 	@param time -sets time variable to value given by timer
	 *	@return null
	 */
	private void setTime(int time) {
		m_Time = time;
	}

	/** Setter method to set current who's turn it currently is
	 * 	@param turnNumber -int that stores number of taken turns
	 *	@return null
	 */
	private void setTurn(int turnNumber) {
		m_Turn = turnNumber;
	}

	/** Getter method that returns label object
	 *  @return m_TimerLabel -returns value for getTunNumberLabel
	 */
	private JLabel getTurnNumberLabel(){
		return m_TurnNumberLabel;
	}

	/** Setter method that sets the board using the board and game classes
	*	@return null
	*/
	private void setBoard(){
		m_Board = getGame().getBoard();
	}

	/** Getter method that returns the background image of the board
	 *  @return m_Background_Image -file extension of background image
	 */
	private BufferedImage getBGImage(){
		return m_Background_Image;
	}

	/** Setter method that sets a image file as the background of the board
	 *  @param background_File -file extension of image file
	 *	@return null
	 */
	private void setBGImage(File background_File) throws IOException{
		try{
            m_Background_Image = ImageIO.read(background_File);
        }catch(IOException e){
            System.out.println("IOException Error @ ProgramController::setBGImage()");
        }
	}

	/** setter method that draws the labels that are used as the squares on the board
	 * 	@param x -coordinates of label grid that user has clicked on
	 * 	@param y
	 *	@return null
	 */
	private void setLabels(int x, int y) {

		m_Image_Labels = new JLabel[x][y];
		for(int i = 0; i < y; i++){
			for(int j = 0; j<x; j++){
				m_Image_Labels[j][i] = new JLabel();
		       	m_Image_Labels[j][i].addMouseListener(this);
		    }
		}
	}

	/** Getter method that returns value of square user has clicked on
	 * 	@param x -coordinates of label grid that user has clicked on
	 * 	@param y
	 *  @return m_Image_Labels -specific label from the 2D array of labels
	 */
	private JLabel getLabel(int x, int y) {
		return m_Image_Labels[x][y];
	}

	/** Setter method that sets image where user has clicked
	 *  @param x -coordinates of label grid that user has clicked on
	 *  @param y
	 *  @param image -image to be used as background of label
	 */
	private void setImage(int x, int y, ImageIcon image) {
		m_Image_Labels[x][y].setIcon(image);
	}

	/** Setter method that sets an image for every square on the board
	*	@return null
	*/
	private void setImages(){
		setLabels(getBoard().getBoardWidth(),getBoard().getBoardHeight());

		for(int y = 0; y<getBoard().getBoardHeight(); y++){
			for(int x = 0; x<getBoard().getBoardWidth(); x++){
				m_Constraints.gridx = x;
				m_Constraints.gridy = y;
				m_Constraints.gridwidth = 1;
				m_Container.add(getLabel(x,y), m_Constraints);
		    }
		}
	}


	/** Setter method that gets label from array
	 *  @return m_Image_Labels -an array of JLabels
	 */
	public JLabel[][] getLabels(){
		return m_Image_Labels;
	}

	public void actionPerformed(ActionEvent event) {
	/**
	 *	Method that is performed when a user has taken their turn so the board's UI elements
	 *	are updated to whomever's turn it is
	 *	@param event - action performed event
	 *	@return null
	*/
		String colour1 = getGame().getPlayer(PLAYER_ONE).getColour();
		String colour2 = getGame().getPlayer(PLAYER_TWO).getColour();
		getTurnLabel().setText(getGame().getPlayer(PLAYER_ONE).getName() + "'s turn");
		setGame(getGame().getPlayer(PLAYER_ONE).getName(), getGame().getPlayer(PLAYER_TWO).getName());

		setTime(0);

		try {
			update(getGame().getBoard(), colour1, colour2);
		} catch (IOException e) {
			System.out.println("IOException error @ ProgramController::acionPerformed()");
		}

		setTurn(0); //Reset turn to 0.
		getTurnNumberLabel().setText("Turn: 1");
	}

	/**
	 *	Mouse event handler method that checks where user has clicked on board,
	 *	then runs the attemptMove method with the coordinates of the move
	 *	@param e - mouse event
	 *	@return null
	*/
  public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
  	if(getGame().checkWin() == false){
      for(int y = 0; y<getBoard().getBoardHeight(); y++){
        for(int x = 0; x<getBoard().getBoardWidth(); x++){
          if(getIsC4() == false){
						if(e.getSource()==getLabel(x,y)){
							attemptMove(x,y);
						}
					}else{
						if(e.getSource()==getLabel(x,0)){
							attemptMove(x,0); //y value doesn't matter
							y = getBoard().getBoardHeight();
						}
					}
				}
			}
        }else{
			try{
				displayWinner();
			}catch(IOException e2){
				System.out.println("IOException error @ ProgramController::mouseClicked()");
			}

    }

  }

  /**
   *	Method that updates the board with new moves that have been taken
   *	@param board
   *	@param colour1
   *	@param colour2
   *	@return null
  */
  public void update(C4AndOthelloBoardStore board, String colour1, String colour2) throws IOException{
    System.out.println("ProgramController::update()");
    int boardHeight = board.getBoard()[0].length;
    int boardWidth = board.getBoard().length;

    for(int y = 0; y<boardHeight; y++){
      for(int x = 0; x<boardWidth; x++){
				int hidden = getLabel(x,y).getDisplayedMnemonic();
					if(board.isEmpty(x,y) == true) {
            setImage(x,y,(new ImageIcon(getBGImage())));
						getLabel(x,y).setDisplayedMnemonic(100);
          } else if (board.getBoard()[x][y].getColour().equals(colour1)) {

						if (hidden == 300 || hidden == 500) {
	            setImage(x,y,(new OthelloPieceColourChanger().flip(board.getBoard()[x][y])));
							getLabel(x,y).setDisplayedMnemonic(400);
						} else {
							BufferedImage piece_Image = ImageIO.read(new File("../Images/" + colour1 + "Piece.png"));
							setImage(x,y,(new ImageIcon(piece_Image)));
							getLabel(x,y).setDisplayedMnemonic(200);
						}

	        } else if (board.getBoard()[x][y].getColour().equals(colour2)) {
						if (hidden == 200 || hidden == 400) {
	            setImage(x,y,(new OthelloPieceColourChanger().flip(board.getBoard()[x][y])));
							getLabel(x,y).setDisplayedMnemonic(500);
						} else {
							BufferedImage piece_Image = ImageIO.read(new File("../Images/" + colour2 + "Piece.png"));
							setImage(x,y,(new ImageIcon(piece_Image)));
							getLabel(x,y).setDisplayedMnemonic(300);
						}
	        }else{
	            //else what??
	        }
  		}
		}
  }

   /**
	 *	Method that checks if the square user has clicked on is a valid move, if there are no
	 *	more possible turns then the game is over and displayWinner method is called.
	 *	@param x
	 *	@param y
	 *	@return null
	 */
	private void attemptMove(int x, int y) {

		int AIC4Col = 0;
		int[] AIOthMoves;
		int AIOthRow = 0;
		int AIOthCol = 0;
		AbstractPlayer[] players = new AbstractPlayer[2];
		players[PLAYER_ONE] = getGame().getPlayer(PLAYER_ONE);
		players[PLAYER_TWO] = getGame().getPlayer(PLAYER_TWO);

		if(m_playerSelection == HUMAN) {
			boolean checkMoveIsValid = false;
			checkMoveIsValid = players[getTurn() % 2].move(x, y, this);
		  if(checkMoveIsValid == true) {
				if (getGame().checkTakeableTurn(players[(getTurn() + 1) % 2]) == true) {
					/** If it's 0 (player 1) turn this will change it  to 1 (player 2) turn*/
					setTurn(getTurn() + 1);
					getTurnNumberLabel().setText("Turn: " + (getTurn() + 1));
					if((getTurn() % 2)==0){
						System.out.println(getGame().getPlayer(PLAYER_ONE).getName());
						getTurnLabel().setText(getGame().getPlayer(PLAYER_ONE).getName() + "'s turn");
					} else {
						System.out.println(getGame().getPlayer(PLAYER_TWO).getName());
						getTurnLabel().setText(getGame().getPlayer(PLAYER_TWO).getName() + "'s turn");
					}

				} else if (getGame().checkTakeableTurn(players[0]) == false && getGame().checkTakeableTurn(players[1]) == false) {
					try{
						displayWinner();
					}catch(IOException e){
						System.out.println("IOException error @ ProgramController::attemptMove()");
					}
				}
			}
		} else if (m_playerSelection == EASY_AI) {
			boolean checkAIMove = false;
			boolean checkMoveIsValid = false;
			if(getIsC4() == true) {
				checkMoveIsValid = players[0].move(x, y, this);

				AIC4Col = c4EasyAI.selectCol(this);
				checkAIMove = players[1].move(AIC4Col, C4_BOARD_HEIGHT, this);
				checkWinner(players);

			} else {
				checkMoveIsValid = players[0].move(x, y, this);
				if(checkMoveIsValid == true) {
					AIOthMoves = othEasyAI.selectMove(this);
					AIOthRow = AIOthMoves[0];
					AIOthCol = AIOthMoves[1];
					checkAIMove = players[1].move(AIOthRow, AIOthCol, this);
					checkWinner(players);

				}
			}
		} else if (m_playerSelection == HARD_AI) {
			boolean checkAIMove = false;
			boolean checkMoveIsValid = false;
			if(getIsC4() == true) {
				checkMoveIsValid = players[0].move(x, y, this);
				AIC4Col = c4HardAI.selectCol(this);
				checkAIMove = players[1].move(AIC4Col, C4_BOARD_HEIGHT, this);
				checkWinner(players);
			} else {
				System.out.println("Error: No HARD AI for Othello");
			}
		}
	}
	/**
	*	Method that check who has won the game
	*
	*	@return null
	*/
	private void checkWinner(AbstractPlayer[] players) {

		if (getGame().checkTakeableTurn(players[0]) == false && getGame().checkTakeableTurn(players[1]) == false) {
			try{
				displayWinner();
			}catch(IOException e){
				System.out.println("IOException error @ ProgramController::attemptMove()");
			}
		}
	}

	private void displayWinner() throws IOException{

	/**
	 *	Method that checks who has won the game, displays the winner's name to a label
	 *	Also highlights winning combination of pieces if playing connect4
	 *	@return null
	 */
		if(getGame().getWinner() == 1 || getGame().getWinner() == 2){
			System.out.println("Winner: Player " + (getGame().getWinner()) );
			if(getGame().getWinner() == 1){
				getTurnLabel().setText(m_Game.getPlayer(PLAYER_ONE).getName() + " is the winner!");
			}else{
				getTurnLabel().setText(m_Game.getPlayer(PLAYER_TWO).getName() + " is the winner!");
			}

		}else if(getGame().getWinner() == 3){
			System.out.println("Draw!");
			getTurnLabel().setText("It's a draw!");
		}else{}

		Connect4WinStateHighlighter highlight = new Connect4WinStateHighlighter();

		if(getIsC4() == true){
			highlight.Connect4WinStateHighlighter(getGame().getWinningi(), getGame().getWinningj(), getLabels(), getGame());
		}else{}
	}

  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}

  /**
   *	Timer method that starts a timer in intervals of 1 second, and is run whenever the game is
   *	declared to be running. current time elapsed is then set to a label and updated every second.
   *	@return null
   */
  private void startTimer() {
		ActionListener actListner = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(getGame().checkWin() == false) { //checks whether game is still running. if so timer can start
					setTime(getTime() + 1);
					getTimerLabel().setText("Time elapsed: " + getTime() + "s");
				}
			}
		};
		m_Timer = new Timer(1000, actListner); //sets timer to 1 second intervals
		System.out.println("Timer started");
		if(getGame().checkWin() == true) {
			m_Timer.stop();
		} else {
			m_Timer.start(); //timer starts
		}
	}

	/**
	 *	Method that is called when program is first run, it starts all the methods
	 *  that create the UI elements, it also accepts input from the user such as their
	 *  name and what game they'd like to play
	 *	@return null
	 */
	void ProgramController() throws IOException{

    Object[] player_types = {"Human", "Easy AI", "Hard AI"};
		Object[] options = {"Connect 4", "Othello"};
		m_playerSelection = JOptionPane.showOptionDialog(this,
        		"Select an Opponent Type",
        		"Opponent Selection",
        		JOptionPane.YES_NO_CANCEL_OPTION,
        		JOptionPane.QUESTION_MESSAGE,
        		null,
        		player_types, player_types[0]);

    String player1 = JOptionPane.showInputDialog("Enter player 1's name"); //accepts user's name before game starts
    String player2 = JOptionPane.showInputDialog("Enter player 2's name");

    int userOption = JOptionPane.showOptionDialog(this,
                                             "Would you like to play Connect 4 "
                                             + "or Othello", "Pick a game",
                                             JOptionPane.YES_NO_CANCEL_OPTION,
                                             JOptionPane.QUESTION_MESSAGE,
                                             null,
                                             options,
                                             options[1]);
		setIsC4(userOption);
	  setGame(player1, player2);
		setBoard();
		setContainer();
		setImages();
    setTurnLabel();
		setNewGameButton();
		setTurnNumberLabel("Turn: 1");

		setTimerLabel();

		startTimer();
		if(m_playerSelection == EASY_AI) {
			if(this.getIsC4() == true) {
				c4EasyAI = new C4EasyAI();
			} else {
				othEasyAI = new OthEasyAI();
			}
		} else if(m_playerSelection == HARD_AI) {
			if(this.getIsC4() == true) {
				c4HardAI = new C4HardAI();
			} else {
				System.out.println("HARD AI is not implemented for Othello");
			}
		}
		update(getGame().getBoard(), "Black", "White");

		pack();
	  setLocationRelativeTo(null);
		setVisible(true);
    }


	/* Symbolic constants */
  private final int PLAYER_ONE = 0;
	private final int PLAYER_TWO = 1;

	/* Member variables that store player info */
	private int m_Turn = 0;
	private String m_Player1, m_Player2;

	/* Timer initialisation and member variables */
	private Timer m_Timer;
	private static int m_Time;

	/* Member variable that stores which game is being played, if false then othello is being played */
  private static boolean m_IsC4;

	/* Member variable that stores game being palayed */
  private static AbstractGameImplementation m_Game;


	/** Initialisation of UI elements */
	private static BufferedImage m_Background_Image;
  private C4AndOthelloBoardStore m_Board;
  private JLabel[][] m_Image_Labels;
	private JButton m_NewGameButton;
	private JLabel m_TurnLabel;
	private JLabel m_TimerLabel;
	private JLabel m_TurnNumberLabel;
  private Container m_Container;
	private GridBagConstraints m_Constraints;
	private int m_playerSelection;
	private final int HUMAN = 0;
	private final int EASY_AI = 1;
	private final int HARD_AI = 2;
	private C4EasyAI c4EasyAI;
	private C4HardAI c4HardAI;
	private OthEasyAI othEasyAI;
	// private OthHardAI (Not implemented

	private final int C4_BOARD_HEIGHT = 7;
}