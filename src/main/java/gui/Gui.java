package gui;

import duke.Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.stream.Collectors;


public class Gui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private javafx.scene.control.TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duncan = new Image(this.getClass().getResourceAsStream("/images/Basketball.jpg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Football.jpg"));

    private Duke duke = new Duke("./data/duke.DukeList.ser", "./data/duke.DukeArchive.ser", this);


    @Override
    public void start(Stage primaryStage) throws Exception {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput,sendButton);

        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        //2
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.getChildren().addAll(
                new DialogBox(new Label("Waddup the name's Duncan. Sorry but Duke couldn't make it, had a pretty bad stomach-ache."), new ImageView(duncan)),
                new DialogBox(new Label("So what do you need bro?"), new ImageView(duncan))
        );
    }

    private void handleUserInput() {
        String inputText = userInput.getText();
        Label userText = new Label(inputText);

        List<String> labelList = getResponse(inputText);

        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user))
        );

        dialogContainer.getChildren().addAll(labelList.stream()
                .map((string -> new DialogBox(new Label(string), new ImageView(duncan))))
                .collect(Collectors.toList()));
        userInput.clear();

    }

    private List<String> getResponse(String input) {
        return duke.run(input);
    }

    public void close() {
        Platform.exit();
    }
}
