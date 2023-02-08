import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import leo.parser.Parser;
import leo.storage.Storage;
import leo.task.LeoTaskException;
import leo.task.TaskList;
import leo.ui.Ui;

/**
 * Main class for Leo.
 */
public class Leo extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Parser parser = new Parser();
    private TaskList taskList;
    private Ui ui;
    public static void main(String[] args) throws LeoTaskException {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        new Leo().start();
    }

    /**
     * Starts the Leo program.
     * @throws LeoTaskException
     */
    public void start() throws LeoTaskException {
        //Get saved state
        ui.greetUser();
        String[] request = parser.parseRequest(); // reads in command fed by user
        readFile();

        if (taskList == null) {
            taskList = new TaskList();
        }
        
        while (!request[0].equals("bye")) {
            taskList.processRequest(request);
            request = parser.parseRequest();
        }
        
        Storage.writeObjectToFile(taskList);
        ui.printExit();
        parser.close();
    }

    public Leo() {
        ui = new Ui();
        readFile();
    }


    public String getResponse(String input){
        String[] request = parser.parseRequest(input);
        String response = "";
        if (taskList == null) {
            taskList = new TaskList();
        }
        try {
            response = taskList.processRequestGUI(request);
            if (response.equals("It was nice talking, see you soon!\n")) {
                Storage.writeObjectToFile(taskList);
                parser.close();
            }
        } catch (LeoTaskException e) {
            response = e.getMessage();
        }
        return response;
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setTitle("Leo");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Reads the saved state of the task list from the file.
     */

    private void readFile() {
        try {
            FileInputStream fileIn = new FileInputStream("taskList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            taskList = (TaskList) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException i) {
            taskList = new TaskList();
            Storage.writeObjectToFile(taskList);
            return;
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("TaskList class not found");
            c.printStackTrace();
            return;
        }
    }
}