package chad.frontend;
//import javafx.fxml.FXML;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.control.Label;

//public class Main extends Application {
//    @FXML
//    private ScrollPane scrollPane;
//    @FXML
//    private VBox dialogContainer;
//    @FXML
//    private TextField userInput;
//    @FXML
//    private Button sendButton;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//
//    @Override
//    public void start(Stage stage) throws Exception {
//
//
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600);
//        stage.setMinWidth(400);
//
//        mainLayout.setPrefSize(400, 600);
//
//        scrollPane.setPrefSize(385, 535);scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1));
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//
//
//        Scene scene = new Scene(mainLayout);
//        stage.setScene(scene);
//        stage.show();
//
//
//    }
//
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    @FXML
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        if (userText.getText().equals("")) {
//            userInput.clear();
//            return;
//        }
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//            DialogBox.getUserDialog(userText, new ImageView(user)),
//            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
//
//    private String getResponse(String input) {
//        //text returned from UI.java
//        return "Duke heard: " + input;
//    }
//
//    public static void main(String[] args) {
//        Application.launch(Main.class, args);
//    }
//}

import java.io.IOException;

import chad.backend.Chad;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Chad chad;

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            this.chad = new Chad();
            fxmlLoader.<MainWindow>getController().setChad(chad);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
