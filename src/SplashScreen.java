import javax.swing.*;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;

/**
 * @author Jamie I Davies
 * @date March 25, 2014
 * @brief This class creates all the swing objects for the Splash Screen of the game
 */
public class SplashScreen extends JFrame{

    private Main m_splash;
    private Main m_options;
    private Main m_playerNames;
    private static final int SPLASH_JFRAME_WIDTH = 732;
    private static final int SPLASH_JFRAME_HEIGHT = 288;
    private static final int PLAYER_JFRAME_WIDTH = 360;
    private static final int PLAYER_JFRAME_HEIGHT = 330;
    private static final int PLAYNAMES_JFRAME_WIDTH = 280;
    private static final int PLAYNAMES_JFRAME_HEIGHT = 300;
    private static final int SPLASH_GRID_COLS = 2;
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
    private C4SaveManager c4SaveManager = new C4SaveManager();
    private OthSaveManager othSaveManager = new OthSaveManager();

    /**
     * Set splash screen to visible
     */
    public SplashScreen(){
        m_splash = new Main();
        m_splash.setVisible(true);
        m_options = new Main();
        m_playerNames = new Main();
        m_splash.setResizable(false);
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
                gameChoice = 0;
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
                gameChoice = 1;
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
        ImageIcon loadButtonIMG = new ImageIcon("../Images/LoadGameImage.png");
        JButton humanButton = new JButton("", humanPlayerIMG);
        JButton easyAIButton = new JButton("", easyAIButtonIMG);
        JButton hardAIButton = new JButton("", hardAIButtonIMG);
        JButton loadButton = new JButton("", loadButtonIMG);
         // action listener for the human button
        humanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                m_options.setVisible(true);
                m_options.setVisible(false);
                //change p2 label on name selection and set p2human state to false
                player2Option = "Player 2 Name: ";
                player2Human = true;
                playState = 0;
                initPlayerNaming();
            }
        });
        // action listener for the easy AI button
            easyAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                m_options.setVisible(false);
                //change p2 label on name selection and set p2human state to false
                player2Option = "Computer Name: ";
                player2Human = false;
                //set difficulty to easy
                difficulty = 0;
                playState = 1;
                initPlayerNaming();
            }
        });
         // action listener for the hard AI button
            hardAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
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
            Piece[][] newBoard = new Piece[10][7];
            try{
              c4SaveManager.loadData();
            } catch (IOException e){
                System.out.println("Can't Load Data");
              e.printStackTrace();
            }
            ProgramController controller = new ProgramController();
            controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.out.println(c4SaveManager.getLoadPlayerType2());
            if (c4SaveManager.getLoadPlayerType2().equals("Human")){
                playState = 0;
            } else if (c4SaveManager.getLoadPlayerType2().equals("Easy")) {
                playState = 1;
            } else if (c4SaveManager.getLoadPlayerType2().equals("Hard")) {
                playState = 2;
            }

            if (c4SaveManager.getLoadGameType().equals("C4")){
                gameChoice = 0;
            } else if (c4SaveManager.getLoadGameType().equals("Oth")) {
                gameChoice = 1;
            }
            player1name = c4SaveManager.getLoadName1();
            player2name = c4SaveManager.getLoadName2();

            Connect4GameLogic connect4GameLogic = c4SaveManager.getLoadGame();
            C4AndOthelloBoardStore board = connect4GameLogic.getBoard();
            newBoard = board.getBoard();

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 10; j++) {
                  System.out.println(newBoard[j][i].getColour());
                }
            }
            try {
                controller.ProgramController(gameChoice, playState, player1name, player2name, newBoard, c4SaveManager.getLoadTurn(), c4SaveManager.getLoadTime());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        });

        //add buttons to panel
        playerOptionsPanel.add(humanButton);
        playerOptionsPanel.add(easyAIButton);
        playerOptionsPanel.add(hardAIButton);
        playerOptionsPanel.add(loadButton);
        //initialise JFrame
        m_options.setTitle("Play Options");
        m_options.setSize(PLAYER_JFRAME_WIDTH, PLAYER_JFRAME_HEIGHT);
        m_options.setLocationRelativeTo(null);
        m_options.setDefaultCloseOperation(EXIT_ON_CLOSE);
        m_options.setResizable(false);
    }

    public void initPlayerNaming() {
        /** 2 cols 2 rows JPanel */
        m_playerNames.setVisible(true);
        JPanel playerNamesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        m_playerNames.getContentPane().add(playerNamesPanel);
        JButton playButton = new JButton("Play ");
        final JTextField player1 = new JTextField();
        final JTextField player2 = new JTextField();
        JLabel label1 = new JLabel("Player 1 Name: ");
        JLabel label2 = new JLabel(player2Option);

        //create various grid references for the layout
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


      //adjusts state of player2 label depending on play option
        if (player2Human == false) {
            player2.setEnabled(false);
            player2.setText(randomNameGen(difficulty));
        } else {
            player2.setEnabled(true);
        }

     // action listener for the hard AI button
        playButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
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
        //initialise JFrame
        m_playerNames.setTitle("Set Player Names");
        m_playerNames.setSize(PLAYNAMES_JFRAME_WIDTH, PLAYNAMES_JFRAME_HEIGHT);
        m_playerNames.setLocationRelativeTo(null);
        m_playerNames.setDefaultCloseOperation(EXIT_ON_CLOSE);
        m_playerNames.setResizable(false);

    }

    /**
     * method to get a random name for either the easy or hard computer player
     * @return
     */
    public String randomNameGen(int difficulty) {
        ArrayList<String> easyNames = new ArrayList();
        ArrayList<String> hardNames = new ArrayList();
        // add elements to easy list
        easyNames.add("Cameron");
        easyNames.add("Jamie");
        //add elements to hard list
        hardNames.add("Chris");
        hardNames.add("Curtis aka Impaler");
        hardNames.add("Martin");

        //create two random generators
        Random easyRandomGen = new Random();Random hardRandomGen = new Random();

        //set index to the random element to get from arraylist
        int easyIndex = easyRandomGen.nextInt(easyNames.size());
        int hardIndex = hardRandomGen.nextInt(hardNames.size());
        //get the value from the arraylist
        String easyName = easyNames.get(easyIndex);
        String hardName = hardNames.get(hardIndex);
        //if to determine if the game is being played in easy or hard ai mode
        String playerName;
        if (difficulty == 0 ) {
            playerName = easyName;
        } else {
            playerName = hardName;
        }

        return playerName;
    }

    public static void main(String args[]) {
        /* testing to call GUI method
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.initSplash();
        */


    }
}
