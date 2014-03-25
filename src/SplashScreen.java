import javax.swing.*;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * STILL NOT FINISHED
 */

/**
 * @author Jamie I Davies
 * @date March 25, 2014
 * @brief This class creates all the swing objects for the Splash Screen of the game
 */
public class SplashScreen extends JFrame{

	ProgramController controller = new ProgramController();
	private static JFileChooser fileChooser = new JFileChooser();

    private Main m_splash;
    private Main m_options;
    private Main m_playerNames;
    private static final int SPLASH_JFRAME_WIDTH = 732;
    private static final int SPLASH_JFRAME_HEIGHT = 288;
    private static final int PLAYER_JFRAME_WIDTH = 280;
    private static final int PLAYER_JFRAME_HEIGHT = 300;
    private static final int PLAYNAMES_JFRAME_WIDTH = 280;
    private static final int PLAYNAMES_JFRAME_HEIGHT = 300;
    private static final int SPLASH_GRID_COLS = 2;
<<<<<<< HEAD
    private static final int PLAYER_GRID_COLS = 3;
    private static final int PLAYER_GRID_ROWS = 1;
    private static final int PLAYNAMES_GRID_COLS = 5;
    private static final int PLAYNAMES_GRID_ROWS = 5;
    private static String gameChoice;
=======
    private static final int PLAYER_GRID_COLS = 1;
    private static final int PLAYER_GRID_ROWS = 2;  
    private static String player2Option;
    private static Boolean player2Human = false;
    private static int difficulty = 0; // 0 for easy, 1 for hard
    private static int gameChoice;
    private static int playState; // 0 for human, 1 for easy, 2 for hard
    private static String player1name;
    private static String player2name;
    private static boolean player1blank;
    private static boolean player2blank;
>>>>>>> FETCH_HEAD

    /**
     * Set splash screen to visible
     */
    public SplashScreen(){
        m_splash = new Main();
        m_splash.setVisible(true);

        m_options = new Main();

        m_playerNames = new Main();

    }

    /**
     * method to initialise all the GUI Swing elements for the SplashScreen
     */
    public void initSplash() {
        /** 2 cols 1 row JPanel */
        JPanel panel = new JPanel(new GridLayout(1,SPLASH_GRID_COLS));

        m_splash.getContentPane().add(panel);
        ImageIcon c4ButtonIMG = new ImageIcon("../Images/C4SplashScreenImage.png");
        ImageIcon othButtonIMG = new ImageIcon("../Images/OthSplashScreenImage.png");
        JButton c4Button = new JButton("", c4ButtonIMG);
        JButton othButton = new JButton("", othButtonIMG);

        // action listener for the C4 button
        c4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

            	//call the options splash screen
                System.out.println("Play Connect 4...");
                gameChoice = "Connect 4";
                m_splash.setVisible(false);
                initPlayerOptions();
            }
        });
         // othello button action listener
            othButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //call the options splash screen
                System.out.println("Play Othello...");
                gameChoice = "Othello";
                m_splash.setVisible(false);

                initPlayerOptions();
            }
        });
        //add buttons to panel
        panel.add(c4Button);
        panel.add(othButton);
        //initialise JFrame
        m_splash.setTitle("A5 Complete Implementation : Connect 4 and Othello : Group 3 ");
        m_splash.setSize(SPLASH_JFRAME_WIDTH, SPLASH_JFRAME_HEIGHT);
        m_splash.setLocationRelativeTo(null);
        m_splash.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initPlayerOptions() {
    	m_options.setVisible(true);
    	/** 2 cols 2 rows JPanel */
        JPanel playerOptionsPanel = new JPanel(new GridLayout(PLAYER_GRID_ROWS,PLAYER_GRID_COLS));
        m_options.getContentPane().add(playerOptionsPanel);
        ImageIcon humanPlayerIMG = new ImageIcon("../Images/HumanImageWD.png");
        ImageIcon easyAIButtonIMG = new ImageIcon("../Images/AIEasyImageWD.png");
        ImageIcon hardAIButtonIMG = new ImageIcon("../Images/AIHardImageWD.png");
        JButton humanButton = new JButton("", humanPlayerIMG);
        JButton easyAIButton = new JButton("", easyAIButtonIMG);
        JButton hardAIButton = new JButton("", hardAIButtonIMG);
         // action listener for the human button
        humanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                m_options.setVisible(true);
<<<<<<< HEAD
            	/*
                 * play game here
                 */
                System.out.println("Human Players..");
                m_options.setVisible(false);
=======
                m_options.setVisible(false);
                //change p2 label on name selection and set p2human state to false
                player2Option = "Player 2 Name: ";
                player2Human = true;
                playState = 0;
>>>>>>> FETCH_HEAD
                initPlayerNaming();
            }
        });
        // action listener for the easy AI button
            easyAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
<<<<<<< HEAD
            	m_options.setVisible(true);

                System.out.println("Easy AI...");
=======
            	m_options.setVisible(false);
                //change p2 label on name selection and set p2human state to false
                player2Option = "Computer Name: ";
                player2Human = false;
                //set difficulty to easy
                difficulty = 0;
                playState = 1;
                initPlayerNaming();
>>>>>>> FETCH_HEAD
            }
        });
         // action listener for the hard AI button
            hardAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
<<<<<<< HEAD
            	m_options.setVisible(true);
            	/*
                 * game here
                 */
                System.out.println("Hard AI...");
=======
            	m_options.setVisible(false);
                //change p2 label on name selection and set p2human state to false
                player2Option = "Computer Name: ";
                player2Human = false;
              //set difficulty to hard
                difficulty = 1;
                playState = 2;
                initPlayerNaming();
            }
        });
            
         // action listener for the load button
            loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
                System.out.println("Loading game...no file found.");
>>>>>>> FETCH_HEAD
            }
        });

        //add buttons to panel
        playerOptionsPanel.add(humanButton);
        playerOptionsPanel.add(easyAIButton);
        playerOptionsPanel.add(hardAIButton);
        //initialise JFrame
        m_options.setTitle("Play Options");
        m_options.setSize(PLAYER_JFRAME_WIDTH, PLAYER_JFRAME_HEIGHT);
        m_options.setLocationRelativeTo(null);
        m_options.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initPlayerNaming() {
        /** 2 cols 2 rows JPanel */
        m_playerNames.setVisible(true);
    	JPanel playerNamesPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
        m_playerNames.getContentPane().add(playerNamesPanel);
        JButton playButton = new JButton("Play ");
        JTextField player1 = new JTextField();
        JTextField player2 = new JTextField();
        JLabel label1 = new JLabel("Player 1 Name: ");
        JLabel label2 = new JLabel("Player 2 Name: ");

         // action listener for the human button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                /*
                 * play game here
                 */
                System.out.println("Human Players..");
            }
        });
        //add buttons to panel
        c.gridx = 0;
        c.gridy = 0;
        playerNamesPanel.add(label1, c);

        c.gridx = 0;
        c.gridy = 1;
        playerNamesPanel.add(label2, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx=1.;
        c.fill=GridBagConstraints.HORIZONTAL;
        playerNamesPanel.add(player1, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx=1.;
        c.fill=GridBagConstraints.HORIZONTAL;
        playerNamesPanel.add(player2, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        playerNamesPanel.add(playButton, c);

     // action listener for the hard AI button
        playButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
<<<<<<< HEAD
        	m_options.setVisible(true);
        	/*
             * game here
             */
            System.out.println("Set text fields to name variables...");
            System.out.println("...Not Done");
            System.out.println("Set check selected game...");
            System.out.println("...Not Done");
            System.out.println("Play Games");
        }
    });
=======
        	//store entered player names
        	player1name = player1.getText();
        	player2name = player2.getText();
        	System.out.println("choice: " + gameChoice + " playstate: " + playState + " p1:" + player1name + " p2: " + player2name);
        	
        	if (player1name.equals("")) {
            	player1blank = true;
            } else if (player2name.equals("")) {
            	player2blank = true;
            } else {
            	player1blank = false;
            	player2blank = false;
            }
            
            System.out.println("p1blank?: " + player1blank + " p2blank?: " + player2blank);
        	
        	//checks the validation for the input player names
            if (player1blank == true | player2blank == true) {
            	JOptionPane.showMessageDialog(m_playerNames, "Sorry, you haven't entered valid player name input!", "Player Name Input Error!", JOptionPane.WARNING_MESSAGE);
        	} else { 
        		ProgramController controller = new ProgramController();
                controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try {
    				controller.ProgramController(gameChoice, playState, player1name, player2name);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
            
        }
        });
>>>>>>> FETCH_HEAD
        //initialise JFrame
        m_playerNames.setTitle("Set Player Names");
        m_playerNames.setSize(PLAYNAMES_JFRAME_WIDTH, PLAYNAMES_JFRAME_HEIGHT);
        m_playerNames.setLocationRelativeTo(null);
        m_playerNames.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void chooserDemo() {



    }

    public static void main(String args[]) {
        /* testing to call GUI method
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.initSplash();
        */
    	
    	
    }
}
