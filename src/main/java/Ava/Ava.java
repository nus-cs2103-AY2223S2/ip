package Ava;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import Ava.commands.AvaCommand;
import Ava.exceptions.AvaException;

import java.util.Scanner;



public class Ava extends Application {
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/AvaImage.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private boolean running = true;

    public static enum TASK_TYPE {
        TODO,
        DEADLINE,
        EVENT
    };
    private static TaskList tasks = new TaskList();
    private static Storage store = new Storage();
    private static Parser parser = new Parser();

    public Ava() {

//        Ui.displayIntro();
//        Scanner myObj = new Scanner(System.in);
//        boolean running = true;
//
//        try {
//            this.tasks.retreiveStorage(store);
//        } catch (AvaException e){
//            // Trouble Retrieving the storage
//            Ui.displayOutput(e.getMessage());
//            running = false;
//        }
//
//        while (running) {
//            try {
//                Ui.ask();
//                String input = myObj.nextLine();
//                AvaCommand c = Ava.parser.parse(input);
//                running = c.run(Ava.tasks,Ava.store);
//                if (running) {
//                    String output = c.output(Ui.getFormatSpace());
//                    Ui.displayOutput(output);
//                }
//            } catch (AvaException e) {
//                Ui.displayOutput(e.getMessage());
//            }
//        }
//        Ui.displayExit();
    }

    @Override
    public void start(Stage stage) {
        /**Abstract Out into Classes
         */
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Label introText = new Label(Ui.getIntro());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(introText, new ImageView(duke))
                );
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
        //step 2
        stage.setTitle("Ava");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);


        mainLayout.setPrefSize(600.0, 800.0);

        scrollPane.setPrefSize(400, 535);
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        //Step 3
            sendButton.setOnMouseClicked((event) -> {
                if (!running) {
                    stage.close();
                }
                this.handleUserInput(userInput, dialogContainer);
            });

            userInput.setOnAction((event) -> {
                if(!running) {
                    stage.close();
                }
                this.handleUserInput(userInput, dialogContainer);
            });


        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


    }


    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        String output = "";
        try {
            AvaCommand c = Ava.parser.parse(input);
            this.running = c.run(Ava.tasks,Ava.store);
            output = c.output(Ui.getFormatSpace());
        } catch (AvaException e) {
            output = e.getMessage();
        }
        return output;
    }




    public static void main(String[] args) {
        Ava a = new Ava();
    }
}
