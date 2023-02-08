import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.visuals.DialogBox;
import duke.visuals.GuiCustomiser;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import duke.visuals.GuiCustomiser;
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

            //this block does the initialization of needed components and helpers,
            //to load saved data, and to initialize visual components.
            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            userInput = new TextField();
            sendButton = new Button("Send");
            AnchorPane mainLayout = new AnchorPane();
            Scanner sc = new Scanner(System.in);
            String textDir = System.getProperty("user.dir") + "/duke.txt";
            File file = new File(textDir);
            TaskList tasks = new TaskList();
            PrintWriter pw = new PrintWriter(new FileWriter(textDir, true));
            Storage.loadData(textDir, file, tasks);

            
            scrollPane.setContent(dialogContainer);
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

            //initializing of scene
            scene = new Scene(mainLayout);
            stage.setScene(scene);
            stage.show();

            //using GuiCustomiser to change dimensions and look of components.
            GuiCustomiser.setMuseStage(stage);
            GuiCustomiser.boxDimensionChange(mainLayout, userInput, sendButton);
            GuiCustomiser.setMuseScrollPaneVisuals(scrollPane);
            GuiCustomiser.setMuseAnchorPaneVisuals(mainLayout, scrollPane, sendButton, userInput);

            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
            //this has to be said at the start
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(new Label(Ui.doGreeting()), new ImageView(muse))
            );

            sendButton.setOnMouseClicked((event) -> {
                if (userInput.getText().equals("bye")) {
                    try {
                        handleClose(pw, textDir, tasks);
                        stage.close();
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
                        stage.close();
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


            //these have to be done when the application ends
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

