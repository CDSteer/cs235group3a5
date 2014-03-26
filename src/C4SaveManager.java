import lib.opencsv.CSVReader;
import lib.opencsv.CSVWriter;


import java.awt.FileDialog;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.io.*;
import javax.swing.*;

/**TODO:
* - If empty saves file as null
* - Overwrites save if same name
* -
*/

/**
 *
 * @file C4SaveManager.java
 * @author Cameron Steer
 * @brief Saves and loads a connect 4 games
 * @detail Reads in a CSV file in order to load a game state,
 * or writes to a CSV file in order to save the games state.
 *
 */
public class C4SaveManager {

  private static boolean test = true;
  private String m_FileName;
  private CSVWriter m_Writer;
  private CSVReader m_CSVReader;
  private List<String[]> m_Data;
  private Piece[][] m_LoadBoard;
  private int m_option;
  private Connect4GameLogic m_Connect4GameLogic = new Connect4GameLogic();

  /**
   * Returns the board thats loaded from the CSV file
   *
   * @return m_LoadBoard
   */
  public Connect4GameLogic getLoadGame(){
    return m_Connect4GameLogic;
  }

  /**
   * Writes the current sate of the board to a new CSV file, named by the user
   *
   * @param the current games board state
   * @return boolean
   */
  public boolean saveData(C4AndOthelloBoardStore board) throws IOException{
    System.out.println("Saving....");
    nameFile(SAVE);
    m_FileName = PATH+ m_FileName +FILETYPE;
    m_Writer = new CSVWriter(new FileWriter(m_FileName));
    m_Data = new ArrayList<String[]>();
    m_LoadBoard = board.getBoard();
    for (int i = 0; i < BOARD_ROWS; i++) {
      for (int j = 0; j < BOARD_COLS; j++) {
        m_Data.add(new String[] { String.valueOf(j), String.valueOf(i), m_LoadBoard[j][i].getColour()});
      }
    }

    m_Writer.writeAll(m_Data);
    m_Writer.close();
    return true;
  }

  /**
   * Called the save file sected by the user, if sucessful the data is loaded else false is retured
   *
   * @return boolean
   */
  public boolean loadData() throws IOException{
    //nameFile(LOAD);
    showFileBrowser();
    System.out.println(m_FileName);
    m_FileName = PATH+ m_FileName;
    System.out.println(m_FileName);

    if (fileFound()){
      readGrid();
    } else {
      return false;
    }
    return true;
  }

  /**
   * Reads the data from the selected file and adds it to the temp 2d array for baord
   *
   * @return boolean
   */
  private boolean readGrid() throws IOException{
    String[] row = null;
    while((row = m_CSVReader.readNext()) != null) {
      AbstractPlayer player = new Human();
      player.setColour(row[2]);
      m_Connect4GameLogic.setPiece(Integer.parseInt(row[0]), Integer.parseInt(row[1]), player);

    }
    m_CSVReader.close();
    return true;
  }

  // private boolean fileChooser(){
  private void showFileBrowser() {
    JFrame frame = new JFrame();
    FileDialog fc = new FileDialog(frame, "Load a Game Save", FileDialog.LOAD);
    //set default directory
    fc.setDirectory("../SAVEDATA/");
    //set file format
    fc.setFile("*.csv");
    //enable the file dialog
    fc.setVisible(true);
    //string to store file name
    m_FileName = fc.getFile();
    //if to catch the cancel exception
    if (m_FileName == null)
      System.out.println("You cancelled the choice");
    else
      System.out.println("You chose " + m_FileName);
  }

  /**
   * Checks if the slected file exists
   *
   * @return boolean
   */
  private boolean fileFound(){
    try{
      m_CSVReader = new CSVReader(new FileReader(m_FileName));
    } catch (FileNotFoundException e){
      System.out.println("Input file not found.");
      return false;
    }
    return true;
  }

  /**
   * Uses a input box to get a name of a file from the user
   *
   * @return boolean
   */
  private boolean nameFile(String op) throws IOException{
    try{
      m_FileName = JOptionPane.showInputDialog("Enter "+ op +" Name");
    } catch (Exception e){
      e.printStackTrace();
    }
    return true;
  }

  public static void main(String[] args) throws IOException{
    Piece[][] newBoard = new Piece[BOARD_ROWS][BOARD_COLS];
    Connect4GameLogic connect4GameLogic = new Connect4GameLogic();
    C4SaveManager c4SaveManager = new C4SaveManager();
    C4AndOthelloBoardStore board = new C4AndOthelloBoardStore();


    for (int i = 0; i < BOARD_ROWS; i++) {
      for (int j = 0; j < BOARD_COLS; j++) {
        if (i == 5){
          connect4GameLogic.setPiece(j, i, connect4GameLogic.getPlayer(0));
        }
        connect4GameLogic.setPiece(j, i, connect4GameLogic.getPlayer(1));
      }
    }

    c4SaveManager.saveData(connect4GameLogic.getBoard());
    if (c4SaveManager.loadData()) {
      connect4GameLogic = c4SaveManager.getLoadGame();
      if (test){
        board = connect4GameLogic.getBoard();
        newBoard = board.getBoard();
        for (int i = 0; i < BOARD_ROWS; i++) {
          for (int j = 0; j < BOARD_COLS; j++) {
            System.out.println(newBoard[j][i].getColour());
          }
        }
      }
    } else {
      System.out.println("Returning to menu......");
    }
  }
  private static final String PATH = "../SAVEDATA/";
  private static final String FILETYPE = ".csv";
  private static final int BOARD_ROWS = 7;
  private static final int BOARD_COLS = 10;
  private static final String LOAD = "load";
  private static final String SAVE = "save";
}