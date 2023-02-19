package duke.ui;
import duke.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

/**
 * Creates a JavaFx based UI class to create the interface.
 */
public class DukeUi extends Application {
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser = new Parser();
    private static Ui ui = new Ui();
    static {
        try {
            storage = new Storage();
            taskList = storage.readFromFile();
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));



    public static void main(String[] args) {
        Application.launch(DukeUi.class, args);
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#4568DC")),
                                new Stop(1, Color.web("#B06AB3"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                ),
                new BackgroundFill(
                        new ImagePattern(
                                new Image("https://edencoding.com/resources/wp-content/uploads/2021/02/Stars_128.png"),
                                0, 0, 128, 128, false
                        ), CornerRadii.EMPTY, Insets.EMPTY
                ),
                new BackgroundFill(
                        new RadialGradient(
                                0, 0, 0.5, 0.5, 0.5, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#FFFFFF33")),
                                new Stop(1, Color.web("#00000033"))),
                        CornerRadii.EMPTY, Insets.EMPTY
                )
        ));
        scrollPane.setContent(dialogContainer);


        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });

        getDialogs(DialogBox.getUserDialog(new Label(new Ui().printWelcomeMessage()),
                new ImageView(duke)));

    }

    private void getDialogs(DialogBox... dialogBoxes) {
        dialogContainer.getChildren().addAll(dialogBoxes);
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() throws DukeException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        getDialogs(DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        if (userInput.getText().toLowerCase().strip().equals("bye")) {
            storage.writeToFile(taskList);
            Platform.exit();
        }
        userInput.clear();
    }
    /**
     * Returns response from NoDuKo.
     * @param input user input
     * @return response from NoDuKo
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            command.assign(taskList, ui);
            String output = command.execute();
            return "NoDuKo Says: " + output;
        } catch (DukeException e) {
            return "NoDuKo Says: " + e.getMessage();
        }
    }
}

