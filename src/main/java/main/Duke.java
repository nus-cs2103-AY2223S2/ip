package main;

import command.Command;

/**
 * Runs the application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String filePath = "tasks.txt";

    /**
     * Constructs Duke task manager.
     */
    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Takes in user input and runs task manager until exit is called.
     */
    public String getResponse(String input) {
        String str;
        try {
            ui.startOfInput();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.outputError(e.getMessage());
        } finally {
            str = ui.endOfInput();
        }
        return str;
    }
}

/**
 * @Override
 *     public void start(Stage stage) {
 *
 *         scrollPane = new ScrollPane();
 *         dialogContainer = new VBox();
 *         scrollPane.setContent(dialogContainer);
 *         userInput = new TextField();
 *         sendButton = new Button("test");
 *         AnchorPane mainLayout = new AnchorPane();
 *         mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
 *         scene = new Scene(mainLayout);
 *         stage.setScene(scene);
 *         stage.show();
 *         stage.setTitle("Duke");
 *         stage.setResizable(false);
 *         stage.setMinHeight(600.0);
 *         stage.setMinWidth(400.0);
 *         mainLayout.setPrefSize(400.0, 600.0); //？
 *         scrollPane.setPrefSize(385, 535);
 *         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
 *         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
 *         scrollPane.setVvalue(1.0);
 *         scrollPane.setFitToWidth(true); //？
 *         dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE); //？
 *         userInput.setPrefWidth(325.0);
 *         sendButton.setPrefWidth(55.0);
 *         AnchorPane.setTopAnchor(scrollPane, 1.0);
 *         AnchorPane.setBottomAnchor(sendButton, 1.0);
 *         AnchorPane.setRightAnchor(sendButton, 1.0);
 *         AnchorPane.setLeftAnchor(userInput , 1.0);
 *         AnchorPane.setBottomAnchor(userInput, 1.0);
 *         sendButton.setOnMouseClicked((event) -> {
 *             handleUserInput();
 *         });
 *
 *         userInput.setOnAction((event) -> {
 *             handleUserInput();
 *         });
 *         dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
 *     }
 *
 *
 *     private void handleUserInput() {
 *         dialogContainer.getChildren().addAll(
 *                 DialogBox.getUserDialog(userInput.getText(), user),
 *                 DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
 *         );
 *         userInput.clear();
 *     }
 */
