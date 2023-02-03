package book.gui;

import book.Book;
import book.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that handles the GUI for user interaction.
 */
public class Gui extends Application {
    /** Width of the GUI. */
    protected static final int GUI_WIDTH = 435;
    /** Height of the GUI. */
    protected static final int GUI_HEIGHT = 630;
    /** Width of the margin. */
    protected static final int MARGIN_WIDTH = 105;
    /** Standard height of one line. */
    protected static final int LINE_SPACING = 24;
    /** Standard inset. */
    protected static final double EDGE_INSETS = 1;
    /** The {@code Image} resource for Book's icon. */
    private final Image bookIcon =
            new Image(this.getClass().getResourceAsStream("/images/Book_Icon.png"));
    /** The {@code Image} resource for a user icon. */
    private final Image userIcon =
            new Image(this.getClass().getResourceAsStream("/images/User_Icon.png"));
    /** The {@code Book} instance associated with the GUI. */
    private Book book;
    /** The {@code VBox} container instance associated with the GUI. */
    private VBox dialogContainer;
    /** The {@code ScrollPane} instance associated with the GUI. */
    private ScrollPane scrollPane;
    /** The {@code TextField} instance for input associated with the GUI. */
    private TextField inputField;
    /** The {@code Button} instance for sending inputs associated with the GUI. */
    private Button sendButton;
    /** The {@code AnchorPane} instance associated with the GUI for layout management. */
    private AnchorPane layout;
    /** The {@code Scene} instance associated with the GUI. */
    private Scene scene;

    /**
     * Constructor that initializes a {@code Gui} instance with its own {@code Book} instance.
     */
    public Gui() {
        this.book = new Book();
    }

    /**
     * Overrides the {@code start} method for JavaFx.
     * @param stage the primary stage for this application, onto which the application scene can be
     *         set. Applications may create other stages, if needed, but they will not be primary
     *         stages.
     */
    @Override
    public void start(Stage stage) {
        assert this.book != null : "GUI should have an associated Book";
        setStage(stage);
        setDialogContainer();
        setScrollPane();
        setInputField();
        setSendButton();
        setLayout();
        this.scene = new Scene(this.layout);
        stage.setScene(scene);
        this.dialogContainer.getChildren().add(
                new DialogBox(new ImageView(bookIcon), new Label(Ui.showWelcome())));
        stage.show();
    }

    /**
     * Handles the setup of the {@code Stage} associated with the {@code Gui}.
     * @param stage the {@code Stage} to be set up.
     */
    private void setStage(Stage stage) {
        stage.setTitle("Book");
        stage.setResizable(false);
        stage.setMinHeight(GUI_HEIGHT);
        stage.setMinWidth(GUI_WIDTH);
    }

    /**
     * Handles the setup of the {@code VBox} container associated with the {@code Gui}.
     */
    private void setDialogContainer() {
        this.dialogContainer = new VBox();
        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogContainer.setSpacing(-EDGE_INSETS);
        this.dialogContainer.heightProperty().addListener((observable -> {
            scrollPane.setVvalue(1.0);
        }));
    }

    /**
     * Handles the setup of the {@code ScrollPane} associated with the {@code Gui}.
     */
    private void setScrollPane() {
        this.scrollPane = new ScrollPane();
        this.scrollPane.setPrefSize(GUI_WIDTH - (2 * EDGE_INSETS),
                GUI_HEIGHT - LINE_SPACING - (2 * EDGE_INSETS));
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setContent(this.dialogContainer);
        this.scrollPane.setFitToWidth(true);
    }

    /**
     * Handles the setup of the {@code TextField} for inputs associated with the {@code Gui}.
     */
    private void setInputField() {
        this.inputField = new TextField();
        this.inputField.setPrefSize(GUI_WIDTH - (2 * EDGE_INSETS) - MARGIN_WIDTH,
                LINE_SPACING);
        this.inputField.setOnAction((event -> {
            handleUserInput();
        }));
    }

    /**
     * Handles the setup of the {@code Button} for sending inputs associated with the {@code Gui}.
     */
    private void setSendButton() {
        this.sendButton = new Button("Send");
        this.sendButton.setPrefSize(MARGIN_WIDTH, LINE_SPACING);
        this.sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    }

    /**
     * Handles the setup of the {@code AnchorPane} for layout management associated with the
     *         {@code Gui}.
     */
    private void setLayout() {
        this.layout = new AnchorPane(this.scrollPane, this.inputField, this.sendButton);
        AnchorPane.setTopAnchor(scrollPane, EDGE_INSETS);
        AnchorPane.setLeftAnchor(scrollPane, -5 * EDGE_INSETS);
        AnchorPane.setBottomAnchor(inputField, EDGE_INSETS);
        AnchorPane.setLeftAnchor(inputField, EDGE_INSETS);
        AnchorPane.setBottomAnchor(sendButton, EDGE_INSETS);
        AnchorPane.setRightAnchor(sendButton, EDGE_INSETS);
        this.layout.setPrefSize(GUI_WIDTH, GUI_HEIGHT);
    }

    /**
     * Handles the passing of {@code String} user input for parsing and inserting the returned
     * elements into a {@code DialogBox} for display in the {@code Gui}.
     */
    private void handleUserInput() {
        Label userText = new Label(inputField.getText());
        Label bookText = new Label(book.parseAndReturn(inputField.getText()));
        this.dialogContainer.getChildren().addAll(
                new DialogBox(new ImageView(userIcon), userText),
                new DialogBox(new ImageView(bookIcon), bookText)
        );
        inputField.clear();
    }
}
