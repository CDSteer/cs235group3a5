import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame{
  public static void main(String[] args) throws IOException{
    ProgramController controller = new ProgramController();
    controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    controller.ProgramController();
  }
}