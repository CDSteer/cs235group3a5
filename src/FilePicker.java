import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Jamie I Davies, Chris Jenkins
 * @date March 25, 2014
 * @brief Class that creates the file browser
 * @detail will only allow user to select a .csv file
 */

public class FilePicker {
   
  public static void showFileBrowser() {
	  JFileChooser fileopen = new JFileChooser();
	  //accept csv files only  
	  FileFilter filter = new FileNameExtensionFilter("csv files", "csv");
	  fileopen.setFileFilter(filter);
	  fileopen.addChoosableFileFilter(filter);

	  //set values of dialog, add title of dialog and button  
	  int ret = fileopen.showDialog(null, "Load Game");

	    //load here... currently just reads in a file
	    if (ret == JFileChooser.APPROVE_OPTION) {
	      File file = fileopen.getSelectedFile();
	      System.out.println("PATH: " + file);
	    }
  }
}