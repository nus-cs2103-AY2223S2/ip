package duke;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

import duke.command.Command;
import duke.component.DialogBox;

public class Duke extends Application {

    public void run() {
        Storage storage = new Storage();
        TaskList list = storage.readTaskList();
        UI.greet();
        do {
            try {
                String input = UI.readCommand();
                Command cmd = Parser.parseCommand(input);
                cmd.execute(list);
                if (cmd.isExit()) {
                    break;
                }
            } catch (DukeRuntimeException ex) {
                UI.echoError(ex);
            }
        } while (true);
        storage.writeTaskList(list);
    }

    @Override
    public void start(Stage stage) {
        System.out.println(System.getProperty("user.dir"));
        InputStream in = null;
        try {
            in = Files.newInputStream(Path.of("src", "main", "resources", "images", "Reimu_1.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Cannot find file ??");
            System.exit(69);
        }
        Image reimu98 = new Image(in);
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send!");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        Scene scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setMinWidth(200);
        stage.setMinHeight(300);
        stage.setScene(scene);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Runnable handleUserInput = () -> {
            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(userInput.getText());
            DialogBox userDialog = new DialogBox(userText, new ImageView(reimu98));
            DialogBox dukeDialog = new DialogBox(dukeText, new ImageView(reimu98));
            dukeDialog.flip();
            dialogContainer.getChildren().addAll(userDialog, dukeDialog);
            userInput.clear();
        };

        sendButton.setOnMouseClicked(event -> {
            handleUserInput.run();
        });

        userInput.setOnAction(event -> {
            handleUserInput.run();
        });

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1));
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Quit...");
    }
}
