import java.awt.FileDialog;
import javax.swing.*;

/**
 * @author Jamie I Davies, Chris Jenkins
 * @date March 25, 2014
 * @brief Class that creates the file browser
 * @detail will only allow user to select a .csv file
 */

public class FilePicker extends JFrame {


  public static void showFileBrowser() {
	  JFrame frame = new JFrame();

	  FileDialog fc = new FileDialog(frame, "Load a Game Save", FileDialog.LOAD);
	  //set default directory
	  fc.setDirectory("../SAVEDATA/");
	  //set file format
	  fc.setFile("*.csv");
	  //enable the file dialog
	  fc.setVisible(true);
	  //string to store file name
	  String fn = fc.getFile();
	  //if to catch the cancel exception
	  if (fn == null)
		  System.out.println("You cancelled the choice");
	  else
		  System.out.println("You chose " + fn);
  	}

  }


