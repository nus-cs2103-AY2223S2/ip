import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import src.main.c4po.BotException;
import src.main.c4po.Ui;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private C4PO c4po;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ash.png"));
    private Image c4poImage = new Image(this.getClass().getResourceAsStream("/images/c4pocirc.png"));


    public MainWindow() {

    }


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setC4PO(C4PO d) {
        c4po = d;
        addDialog(Ui.showIntroduction(true));
        addDialog(c4po.introduction);
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing c4po's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = c4po.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getC4poDialog(response, c4poImage)
            );
        } catch (BotException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getC4poDialog(e.getMessage(), c4poImage)
            );
        }

        userInput.clear();
    }

    /**
     * Creates dialog boxes. Clears the user input after processing.
     */
    @FXML
    private void addDialog(String response) {

        dialogContainer.getChildren().addAll(
                DialogBox.getC4poDialog(response, c4poImage)
        );

        userInput.clear();
    }
}