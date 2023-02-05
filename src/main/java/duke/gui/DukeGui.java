package duke.gui;

import duke.command.Menu;
import duke.utilities.Parser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * The type Duke gui.
 */
public class DukeGui extends Application {
  private final Image user = new Image(Objects.requireNonNull(
          this.getClass().getResourceAsStream("/images/User.png")));
  private final Image duke = new Image(Objects.requireNonNull(
          this.getClass().getResourceAsStream("/images/duke.png")));
  /**
   * The Input.
   */
  public String input;
  /**
   * The Output.
   */
  public String output;
  /**
   * The Dialog contrainer.
   */
  VBox dialogContrainer = new VBox();
  /**
   * The User input.
   */
  TextField userInput = new TextField();

  @Override
  public void start(Stage stage) {
    ScrollPane scrollPane = new ScrollPane();

    scrollPane.setContent(dialogContrainer);

    Button sendButton = new Button("Send");
    AnchorPane mainLayout = new AnchorPane();
    mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

    //Label helloWorld = new Label("Hello World!"); // Creating a new Label control
    Scene scene = new Scene(mainLayout); // Setting the scene to be our Label
    stage.setScene(scene); // Setting the stage to show our screen
    stage.show(); // Render the stage.

    //settings
    stage.setTitle("Alpha Beast");
    stage.setResizable(false);
    stage.setMinHeight(600);
    stage.setMinWidth(400);

    mainLayout.setPrefSize(400, 600);

    scrollPane.setPrefSize(385, 535);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    scrollPane.setVvalue(1.0);
    scrollPane.setFitToWidth(true);

    dialogContrainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    userInput.setPrefWidth(325);

    sendButton.setPrefWidth(55.0);

    AnchorPane.setTopAnchor(scrollPane, 1.0);

    AnchorPane.setBottomAnchor(sendButton, 1.0);
    AnchorPane.setRightAnchor(sendButton, 1.0);

    AnchorPane.setLeftAnchor(userInput, 1.0);
    AnchorPane.setBottomAnchor(userInput, 1.0);

    dialogContrainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    welcome();
    //actions
    sendButton.setOnMouseClicked((event) -> {
      handleUserInput();
    });


    userInput.setOnAction((event) -> {
      handleUserInput();
    });
  }

  private Label getDialogLabel(String text) {
    // You will need to import `javafx.scene.control.Label`.
    Label textToAdd = new Label(text);
    textToAdd.setWrapText(true);

    return textToAdd;
  }

  @FXML
  private void handleUserInput() {
    input = userInput.getText();
    output = getResponse(input);

    dialogContrainer.getChildren().addAll(
            DialogBox.getUserDialog(input, user),
            DialogBox.getDukeDialog(output, duke)
    );
    userInput.clear();
    //close();
  }

  private void welcome() {
    output = Parser.WRONG_INPUT;

    dialogContrainer.getChildren().addAll(
            DialogBox.getDukeDialog(output, duke)
    );
    userInput.clear();
  }

  private void close() {
    Platform.exit();
    System.exit(0);
  }

  /**
   * Gets response.
   *
   * @param input the input
   * @return the response
   */
  String getResponse(String input) {
    return Menu.inOut(input);
  }

}
