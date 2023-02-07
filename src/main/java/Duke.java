import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.visuals.DialogBox;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * Duke Class: Serves as entry point of the program, or JAR file.
 *
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image muse = new Image(this.getClass().getResourceAsStream("/images/muse.jfif"));

    @Override
    public void start(Stage stage) throws IOException {
        try {
            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);

            userInput = new TextField();
            sendButton = new Button("Send");

            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

            scene = new Scene(mainLayout);

            stage.setScene(scene); //We are setting up our stage to show screen.
            stage.show(); //We are rendering the stage.

            //More visual customization beyond this point

            //These 4 lines are for stage customization.
            stage.setTitle("Muse");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            mainLayout.setPrefSize(400.0, 600.0);

            scrollPane.setPrefSize(385, 535);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            scrollPane.setVvalue(1.0);
            scrollPane.setFitToWidth(true);

            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            userInput.setPrefWidth(325.0);
            sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(scrollPane, 1.0);
            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);
            AnchorPane.setLeftAnchor(userInput, 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);

            //this has to be said at the start
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(new Label(Ui.doGreeting()), new ImageView(muse))
            );

            Scanner sc = new Scanner(System.in);
            String textDir = System.getProperty("user.dir") + "/duke.txt";
            File file = new File(textDir);
            TaskList tasks = new TaskList();
            PrintWriter pw = new PrintWriter(new FileWriter(textDir, true));

            Storage.loadData(textDir, file, tasks);

            sendButton.setOnMouseClicked((event) -> {
                if (userInput.getText().equals("bye")) {
                    try {
                        handleClose(pw, textDir, tasks);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                Parser.handleInputs(dialogContainer, tasks, userInput, user, muse);
                userInput.clear();
            });

            userInput.setOnAction((event) -> {
                if (userInput.getText().equals("bye")) {
                    try {
                        handleClose(pw, textDir, tasks);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                Parser.handleInputs(dialogContainer, tasks, userInput, user, muse);
                userInput.clear();
            });

            dialogContainer.heightProperty().addListener((observable -> {
                scrollPane.setVvalue(1.0);
            }));


            //these have to be done when the application ends (closes?)
            Storage.saveData(pw, textDir, tasks);
            Ui.doFarewell();

        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    private void handleClose(PrintWriter pw, String textDir, TaskList tasks) throws IOException {
        Storage.saveData(pw, textDir, tasks);
        Ui.doFarewell();
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
    }

}

