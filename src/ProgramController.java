/**
 * @file ProgramController.java
 * @author Jamie I Davies, Martin Hui, Cameron Steer, I.C. Skinner, J. Bailey, S. Jones
 * @date 25 Feb '14
 * @brief class that builds the board for either C4 or Othello
 * @detail ProgramController has all setup variables such and the game state and player names called into it
 *
*/

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

import java.awt.event.*;

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
		m_Constraints.gridy = getBoard().getBoardHeight() + ADD_TWO;
		m_Constraints.gridx = getBoard().getBoardWidth() / DIVIDE_BY_TWO - 1;
		m_Constraints.gridwidth = NEW_GAME_GRID_WIDTH;
		m_Container.add(m_NewGameButton, m_Constraints);
		m_NewGameButton.addActionListener(this);
	}
	ActionListener actListner2;
	/**
	 *	Create Save Game button and actionlistener
	 *	@return null
	 */
	private void setSaveButton(){
		m_SaveButton = new JButton("Save Game");
		m_Constraints.gridy = getBoard().getBoardHeight() + ADD_FOUR;
		m_Constraints.gridx = getBoard().getBoardWidth() / DIVIDE_BY_TWO - SUBTRACT_SEVEN;
		m_Constraints.gridwidth = NEW_GAME_GRID_WIDTH;
		m_Container.add(m_SaveButton, m_Constraints);
		//m_SaveButton.addActionListener(m_Handler);

		actListner2 = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Test save button");
				try{
				  c4SaveManager.saveData(getGame().getBoard());
				} catch (Exception e){
					System.out.println("Can't Save Data");
				  e.printStackTrace();
				}
			}
		};
		// action listener for the save button
    m_SaveButton.addActionListener(actListner2);



	}


	/**
	 *	Create label to display name of current player
	 *	@return null
	 */
	private void setTurnLabel(){
		m_TurnLabel = new JLabel(m_Game.getPlayer(PLAYER_ONE).getName() + "'s turn");
		System.out.println(m_Game.getPlayer(PLAYER_ONE).getName());
		m_Constraints.gridy = getBoard().getBoardHeight() + ADD_TWO;
		m_Constraints.gridx = (getBoard().getBoardWidth() / DIVIDE_BY_TWO) - SUBTRACT_FOUR;
		m_Constraints.gridwidth = TURN_GRID_WIDTH;
		m_Container.add(m_TurnLabel, m_Constraints);
	}

	/**
	 *	Create label to display time elapsed
	 *	@return null
	 */
	private void setTimerLabel(){
		m_TimerLabel = new JLabel("Time elapsed: 0s");
		m_Constraints.gridy = getBoard().getBoardHeight() + ADD_TWO;
		m_Constraints.gridx = getBoard().getBoardWidth() / DIVIDE_BY_TWO + 1;
		m_Constraints.gridwidth = TIMER_GRID_WIDTH;
		m_Container.add(m_TimerLabel, m_Constraints);
	}

	/**
	 *	Create label to display number of turns taken
	 *	@return null
	 */
	private void setTurnNumberLabel(String labelString){

		m_TurnNumberLabel = new JLabel(labelString);
		m_Constraints.gridy = getBoard().getBoardHeight() + ADD_TWO;
		m_Constraints.gridx = getBoard().getBoardWidth() / DIVIDE_BY_TWO - SUBTRACT_FOUR;
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
		this.setMinimumSize(new Dimension(CONTAINER_64 * getBoard().getBoardWidth(), CONTAINER_64 * getBoard().getBoardHeight() + CONTAINER_22));
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

	/**
	 *	Method that is performed when a user has taken their turn so the board's UI elements
	 *	are updated to whomever's turn it is
	 *	@param event - action performed event
	 *	@return null
	*/
	public void actionPerformed(ActionEvent event) {
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
	 *	Mouse event handler method that checks where user has enter into particular coordinate on Connect 4 board,
	 *	then runs the arrowPointer method with the coordinates of the move
	 *	@param e - mouse event
	 *	@return null
  */
  public void mouseEntered(MouseEvent e) {
	if(getGame().checkWin() == false){
	System.out.println("Mouse Entered");
	}
		try{
		for(int y = 0; y < getBoard().getBoardHeight(); y++){
			for(int x = 0; x < getBoard().getBoardWidth(); x++){
				    if(getIsC4() == true){
						if(e.getSource()==getLabel(x,y)){
							arrowPointer(x,0);          // 6 as always on top
						}		
					}
			}
		}
		}catch(IOException e2){
			System.out.println("IOException error @ ProgramController::mouseEntered()");
		}
    }
	
	/**
	* Animation of mouse pointing at row in Connect 4
	*	@param  x	x coordinate of mouse clicked postion
	*	@param	y   y coordinate of mouse clicked postion
	*	@return null
	*/
	public void arrowPointer(int x, int y) throws IOException{
		final BufferedImage arrow_Image = ImageIO.read(new File("../Images/Connect4arrow.png"));
		final BufferedImage blank_Image = ImageIO.read(new File("../Images/Connect4Background.png"));
		int hidden;
		for(int i = 0; i < getBoard().getBoardWidth(); i++){
			hidden = getLabel(i,0).getDisplayedMnemonic();
			if(hidden == IMAGE_SIZE_100){
				setImage(i,0,(new ImageIcon(blank_Image)));
				getLabel(i,0).setDisplayedMnemonic(IMAGE_SIZE_100);
			}
		}
		hidden = getLabel(x,y).getDisplayedMnemonic();
		if(hidden == IMAGE_SIZE_100){
			setImage(x,y,(new ImageIcon(arrow_Image)));
		}
	}

	/**
	* Animation of falling piece
	*	@param  x	x coordinate of mouse clicked postion
	*	@param	y   y coordinate of mouse clicked postion
	*	@param	piece_Image      pieces image
	*	@return null
	*/
	public void dropPiece(int x, int y, BufferedImage piece_Image) throws IOException{
		final int boardHeight = C4_BOARD_HEIGHT - 1;
		final int width = x;
		final int height = y;
		final int hidden = getLabel(x,y).getDisplayedMnemonic();
		final BufferedImage piece_Image1 = piece_Image;
		final BufferedImage blank_Image = ImageIO.read(new File("../Images/Connect4Background.png"));
		new Thread(
			new Runnable(){
				public void run(){
					if(hidden == IMAGE_SIZE_100){
						try{
							for(int i = 0; i < height; i++){
								setImage(width,i,(new ImageIcon(piece_Image1)));
								Thread.sleep(100);
								setImage(width,i,(new ImageIcon(blank_Image)));
							}
						}catch(InterruptedException e){
						}
					}
					setImage(width,height,(new ImageIcon(piece_Image1)));
				}
			}
		).start();

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
					getLabel(x,y).setDisplayedMnemonic(IMAGE_SIZE_100);
				} else if (board.getBoard()[x][y].getColour().equals(colour1)) {

					if (hidden == IMAGE_SIZE_300 || hidden == IMAGE_SIZE_500) {
						setImage(x,y,(new OthelloPieceColourChanger().flip(board.getBoard()[x][y])));
						getLabel(x,y).setDisplayedMnemonic(IMAGE_SIZE_400);
					} else {
						BufferedImage piece_Image = ImageIO.read(new File("../Images/" + colour1 + "Piece.png"));
						dropPiece(x,y,piece_Image);
						getLabel(x,y).setDisplayedMnemonic(IMAGE_SIZE_200);
					}
					} else if (board.getBoard()[x][y].getColour().equals(colour2)) {
					if (hidden == IMAGE_SIZE_200 || hidden == IMAGE_SIZE_400) {
						setImage(x,y,(new OthelloPieceColourChanger().flip(board.getBoard()[x][y])));
						getLabel(x,y).setDisplayedMnemonic(IMAGE_SIZE_500);
					} else {
						BufferedImage piece_Image = ImageIO.read(new File("../Images/" + colour2 + "Piece.png"));
						dropPiece(x,y,piece_Image);
						getLabel(x,y).setDisplayedMnemonic(IMAGE_SIZE_300);
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
		AbstractPlayer[] players = new AbstractPlayer[PLAYER_2];
		players[PLAYER_ONE] = getGame().getPlayer(PLAYER_ONE);
		players[PLAYER_TWO] = getGame().getPlayer(PLAYER_TWO);

		if(m_playerSelection == HUMAN) {
			boolean checkMoveIsValid = false;
			checkMoveIsValid = players[getTurn() % PLAYER_2].move(x, y, this);
		  if(checkMoveIsValid == true) {
				if (getGame().checkTakeableTurn(players[(getTurn() + 1) % 2]) == true) {
					/** If it's 0 (player 1) turn this will change it  to 1 (player 2) turn*/
					setTurn(getTurn() + 1);
					getTurnNumberLabel().setText("Turn: " + (getTurn() + 1));
					if((getTurn() % PLAYER_2)==0){
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
				checkMoveIsValid = players[0].move(x, y, this);
				if(checkMoveIsValid == true) {
					AIOthMoves = othHardAI.selectMove(this);
					AIOthRow = AIOthMoves[0];
					AIOthCol = AIOthMoves[1];
					checkAIMove = players[1].move(AIOthRow, AIOthCol, this);
					checkWinner(players);

				}
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
		if(getGame().getWinner() == PLAYER_1 || getGame().getWinner() == PLAYER_2){
			System.out.println("Winner: Player " + (getGame().getWinner()) );
			if(getGame().getWinner() == PLAYER_1){
				getTurnLabel().setText(m_Game.getPlayer(PLAYER_ONE).getName() + " is the winner!");
			}else{
				getTurnLabel().setText(m_Game.getPlayer(PLAYER_TWO).getName() + " is the winner!");
			}

		}else if(getGame().getWinner() == DRAW){
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
		m_Timer = new Timer(ONE_SECOND_INTERVAL, actListner); //sets timer to 1 second intervals
		System.out.println("Timer started");
		if(getGame().checkWin() == true) {
			m_Timer.stop();
		} else {
			m_Timer.start(); //timer starts
		}
	}
  
  	public void setUpGame(int gameState, int playerState, String player1Name, String player2Name) {
  		//connect 4: 1, othello: 0
  		
  	}

  	/**
	 * Method that is called when program is first run, it starts all the methods
	 *  that create the UI elements, it also accepts input from the user such as their
	 *  name and what game they'd like to play
	 * @param gameState
	 * @param playerState
	 * @param player1Name
	 * @param player2Name
	 * @throws IOException
	 */
	void ProgramController(int gameState, int playerState, String player1Name, String player2Name) throws IOException{
		
		player1 = player1Name;
  		player2 = player2Name;
  		
  		setIsC4(gameState);
  		
  		if (playerState == 0) {
  			m_playerSelection = HUMAN;
  		} else if (playerState == 1) {
  			m_playerSelection = EASY_AI;
  		} else if (playerState == 2) {
  			m_playerSelection = HARD_AI;
  		}
  		
  		setGame(player1, player2);

    /*
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
*/
    //shit load of method calls
	//setIsC4(userOption); //userOption
	//setGame(player1, player2); //player1, player2 
	setBoard();
	setContainer();
	setImages();
    setTurnLabel();
	setNewGameButton();
	setSaveButton();
	setTurnNumberLabel("Turn: 1");
	setTimerLabel();
	startTimer();

	//AI stuff
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
				othHardAI = new OthHardAI();
			}
		}
		update(getGame().getBoard(), "Black", "White");

		//no idea
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

	/* Member variable that stores game being played */
  private static AbstractGameImplementation m_Game;

  private C4SaveManager c4SaveManager;

	/** Initialisation of UI elements */
	private static BufferedImage m_Background_Image;
  private C4AndOthelloBoardStore m_Board;
  private JLabel[][] m_Image_Labels;
	private JButton m_NewGameButton;
	private JButton m_SaveButton;
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
	private OthHardAI othHardAI;
	private final int C4_BOARD_HEIGHT = 7;
  private final int ONE_SECOND_INTERVAL = 1000;
  private final int DRAW = 3;
  private final int TIMER_GRID_WIDTH = 10;
  private final int TURN_GRID_WIDTH = 8;
  private final int NEW_GAME_GRID_WIDTH = 2;
  private final int DIVIDE_BY_TWO = 2;
  private final int CONTAINER_64 = 64;
  private final int CONTAINER_22 = 22;
  private final int ADD_TWO = 2;
  private final int SUBTRACT_FOUR = 4;
  private final int SUBTRACT_SEVEN = 7;
  private final int ADD_FOUR = 4;
  private final int IMAGE_SIZE_100 = 100;
  private final int IMAGE_SIZE_200 = 200;
  private final int IMAGE_SIZE_300 = 300;
  private final int IMAGE_SIZE_400 = 400;
  private final int IMAGE_SIZE_500 = 500;
  private final int PLAYER_1 = 1;
  private final int PLAYER_2 = 2;
  private String player1;
  private String player2;
  private int userOption;
  private int gameState = 0;
  private int playerState = 0;

}
