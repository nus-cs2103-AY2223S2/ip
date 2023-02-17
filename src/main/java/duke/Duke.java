package duke;

import duke.task.TaskList;

import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;



public class Duke {
    private FileManager fileManager;
    private TaskList taskList;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/wojak.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/monkaS.jpg"));

    public Duke() {

    }
    public Duke(String filePath) {
        this.fileManager = new FileManager(filePath);
        this.taskList = this.fileManager.getFile();
        this.parser = new Parser(this.fileManager, this.taskList);
    }

    /**
     * Returns void.
     * <p>
     * Runs the Pandora bot
     */
    public void run() {
        String greetings = "Heyyo, Pandora at your service \n"
                + "What can I do for you?";
        //System.out.println(greetings);

        //Input objects
        Scanner userInput = new Scanner(System.in);;
        String userMessage = "dummyInput";

        while (!userMessage.equals("bye")) {
            userMessage = userInput.nextLine();
            System.out.println(parser.parse(userMessage));
        }
    }

    public static void main(String[] args) {
        new Duke("data\\pandora.txt").run();
    }

    public String getResponse(String userInput) {
        return this.parser.parse(userInput);
    }
}
