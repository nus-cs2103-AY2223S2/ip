import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
/**
 * The main class of the software engineering project Duke.
 * This class is responsible for coordinating the work of the user interface, storage and parser classes.
 *
 * @author owen-yap
 */
public class Duke {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object which takes a file path and initializes the storage, user interface and parser.
     *
     * @param filePath the file path where tasks will be stored.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser(storage, ui);
    }


    /**
     * The main method of the Duke class which initializes the program and runs it.
     */
    public void run() {
        this.ui.printWelcomeMsg();
        boolean hasNext = true;
        while (hasNext) {
            String input = this.ui.readInput();
            hasNext = this.parser.parse(input);
        }
    }
    /**
     * The entry point of the Duke program.
     *
     * @param args cli arguments.
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
