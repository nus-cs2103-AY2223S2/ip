package io;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Graphical User Interface
 */
public class Gui extends Application implements Ui {
    private static final double WIN_WIDTH = 400.0;
    private static final double WIN_HEIGHT = 600.0;
    private static final double USER_INP_HEIGHT = 35.0;
    private static final double DIALOGUE_HEIGHT = WIN_HEIGHT - USER_INP_HEIGHT - 30.0;
    private static final double DIALOGUE_WIDTH = WIN_WIDTH;
    private static final double BUTTON_HEIGHT = USER_INP_HEIGHT; 
    private static final double BUTTON_WIDTH = 55.0;
    private static final double USER_INP_WIDTH = WIN_WIDTH - BUTTON_WIDTH;
    private static final double OFFSET = 1.0;
    private static final String TITLE = "D";
    private static final boolean IS_RESIZABLE = false;
    private static final String BUTTON_CONTENT = ">>>";

    private static Gui guiInstance;


    private ScrollPane scrollPane;
    private VBox dialogueContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    
    @Override
    public void start(Stage stage) {
        // Setup
        this.mainLayout = new AnchorPane();
        this.scrollPane = new ScrollPane();
        this.dialogueContainer = new VBox();
        this.userInput = new TextField();
        this.sendButton = new Button(BUTTON_CONTENT);
    
        this.scrollPane.setContent(this.dialogueContainer);
        
        guiInstance = this;
        
        
        // Window formatting
        stage.setTitle(TITLE);
        stage.setResizable(IS_RESIZABLE);
        stage.setHeight(WIN_HEIGHT);
        stage.setWidth(WIN_WIDTH);
        mainLayout.setPrefSize(WIN_WIDTH, WIN_HEIGHT);
        
        // Scrollpane formatting
        scrollPane.setPrefHeight(DIALOGUE_HEIGHT);
        scrollPane.setPrefWidth(DIALOGUE_WIDTH);
        scrollPane.setVvalue(OFFSET);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        // User Input Box formatting
        userInput.setPrefHeight(USER_INP_HEIGHT);
        userInput.setPrefWidth(USER_INP_WIDTH);
        sendButton.setPrefHeight(BUTTON_HEIGHT);
        sendButton.setPrefWidth(BUTTON_WIDTH);
        
        // Putting all together
        AnchorPane.setTopAnchor(scrollPane, OFFSET);
        AnchorPane.setBottomAnchor(userInput, OFFSET);
        AnchorPane.setLeftAnchor(userInput, OFFSET);
        AnchorPane.setBottomAnchor(sendButton, OFFSET);
        AnchorPane.setRightAnchor(sendButton, OFFSET);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        
        this.scene = new Scene(this.mainLayout);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public Ui launch() {
        Application.launch(Gui.class);
        return guiInstance;
    }

    @Override
    public void showReply(String reply) {

    }

    @Override
    public String getInput() {
        return "";
    }
}
