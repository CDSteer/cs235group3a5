import lib.opencsv.CSVReader;
import lib.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class C4SaveManager{

  private static boolean test = true;
  private String m_FileName;
  private CSVWriter m_Writer;
  private CSVReader m_CSVReader;
  private List<String[]> m_Data;
  private String[][] m_LoadBoard = new String[BOARD_ROWS][BOARD_COLS];
  private int m_option;

  public String[][] getLoadBoard(){
    return m_LoadBoard;
  }

  public boolean saveData(String[][] board) throws IOException{
    nameFile(SAVE);
    m_FileName = PATH+ m_FileName +FILETYPE;
    m_Writer = new CSVWriter(new FileWriter(m_FileName));
    m_Data = new ArrayList<String[]>();
    for (int i = 0; i < BOARD_ROWS; i++) {
      for (int j = 0; j < BOARD_COLS; j++) {
        m_Data.add(new String[] {String.valueOf(i), String.valueOf(j), board[i][j]});
      }
    }
    m_Writer.writeAll(m_Data);
    m_Writer.close();
    return true;
  }

  public boolean loadData() throws IOException{
    nameFile(LOAD);
    m_FileName = PATH + m_FileName + FILETYPE;
    if (fileFound()){
      readGrid();
    } else {
      return false;
    }
    return true;
  }

  private boolean readGrid() throws IOException{
    String[] row = null;
    while((row = m_CSVReader.readNext()) != null) {
      m_LoadBoard[Integer.parseInt(row[0])][Integer.parseInt(row[1])] = row[2];
    }
    m_CSVReader.close();
    return true;
  }

  private boolean fileFound(){
    try{
      m_CSVReader = new CSVReader(new FileReader(m_FileName));
    } catch (FileNotFoundException e){
      System.out.println("Input file not found.");
      return false;
    }
    return true;
  }

  private boolean nameFile(String op) throws IOException{
    try{
      m_FileName = JOptionPane.showInputDialog("Enter "+ op +" Name");
    } catch (Exception e){
      e.printStackTrace();
    }
    return true;
  }

  public static void main(String[] args) throws IOException{
    String[][] board = new String[BOARD_ROWS][BOARD_COLS];
    String[][] newBoard = new String[BOARD_ROWS][BOARD_COLS];
    C4SaveManager c4SaveManager = new C4SaveManager();

    for (int i = 0; i < BOARD_ROWS; i++) {
      for (int j = 0; j < BOARD_COLS; j++) {
        board[i][j] = "yellow";
      }
    }

    c4SaveManager.saveData(board);
    if (c4SaveManager.loadData()) {
      newBoard = c4SaveManager.getLoadBoard();
      if (test){
        for (int i = 0; i < BOARD_ROWS; i++) {
          for (int j = 0; j < BOARD_COLS; j++) {
            System.out.println(newBoard[i][j]);
          }
        }
      }
    } else {
      System.out.println("Returning to menu......");
    }
  }
  private static final String PATH = "../SAVEDATA/";
  private static final String FILETYPE = ".csv";
  private static final int BOARD_ROWS = 8;
  private static final int BOARD_COLS = 8;
  private static final String LOAD = "load";
  private static final String SAVE = "save";
}
