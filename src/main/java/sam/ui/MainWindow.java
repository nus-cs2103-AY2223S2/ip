package sam.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
  @FXML
  private ScrollPane scrollPane;
  
  @FXML
  private VBox dialogContainer;
  
  @FXML
  private TextField userInput;
  
  @FXML
  private Button sendButton;
  
  // private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
  // private Image ariiImage = new Image(this.getClass().getResourceAsStream("/images/Arii.png"));

}
