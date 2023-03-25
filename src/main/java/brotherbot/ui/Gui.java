package brotherbot.ui;

import brotherbot.BrotherBot;
import brotherbot.DialogBox;
import brotherbot.commands.Command;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.util.Objects.requireNonNull;

public class Gui {

    private final BrotherBot brotherBot;
    private Stage stage;
    private VBox dialogContainer;
    private TextField userInput;
    private final Image user = new Image(requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image brother = new Image(requireNonNull(this.getClass().getResourceAsStream("/images/brother.png")));
    private final Image background = new Image(requireNonNull(this.getClass().getResourceAsStream("/images/background.png")));


    public Gui(BrotherBot brotherBot) {
        this.brotherBot = brotherBot;
    }

    public void initialise(Stage stage) {
        this.stage = stage;
        stageSetup();
        stage.show();
        loadWelcome();
    }


    private void stageSetup() {
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // Stage Setup
        scrollPane.setStyle("-fx-background: #121c2b");
        BackgroundImage backgroundImage = new BackgroundImage(background,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        dialogContainer.setBackground(background);

        userInput = new TextField();
        Button sendButton = new Button("SEND");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);

        stage.setTitle("BrotherBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(700.0);

        mainLayout.setPrefSize(700.0, 600.0);

        scrollPane.setPrefSize(700, 565);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(565);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(640.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // User Interaction Setup
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.setScene(scene);
    }


    private void loadWelcome() {
        Label load = new Label(this.brotherBot.welcome());
        dialogContainer.getChildren().addAll(
                DialogBox.getBrotherDialog(load, new ImageView(brother))
        );
    }


    private void handleUserInput() {

        Label userText = new Label(userInput.getText());
        String output = brotherBot.getResponse(userInput.getText());
        Label broText = new Label(output);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBrotherDialog(broText, new ImageView(brother))
        );
        userInput.clear();
        if (Command.isExit) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }

}
