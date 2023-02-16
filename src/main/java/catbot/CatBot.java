package catbot;

import java.util.ArrayList;

import catbot.commands.Command;
import catbot.commands.SaveCommand;
import catbot.parser.Parser;
import catbot.storage.Storage;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;


/**
 * Entry point of the CatBot application.
 * Initiates all the required components and begins interaction with the user.
 */
public class CatBot {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private String logo =
            "Welcome from: \n"
            + " ____ ____ ____ ____ ____ ____\n"
            + "||C |||a |||t |||B |||o |||t ||\n"
            + "||__|||__|||__|||__|||__|||__||\n"
            + "|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|\n";

    /**
     * Initialises a new CatBot instance and loads from the save file.
     * @param saveFile is the path to the save file for persistent task storage.
     */
    public CatBot(String saveFile) {
        ui = new Ui();
        try {
            storage = new Storage(saveFile);
            tasks = new TaskList(storage.load());
        } catch (CatBotException e) {
            logo += "\n Error loading from save file. A new task list will be initiated for this session.";
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public String getLogo() {
        return logo;
    }

    /**
     * Get CatBot's response for a given input command.
     * @param input is the command the user input
     * @return CatBot's output
     */
    public Text getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getNextOutput();
        } catch (CatBotException e) {
            return ui.getError(e.getMessage());
        }
    }

    /**
     * Saves data to the file when the program is closed.
     */
    public void close() {
        try {
            new SaveCommand().execute(tasks, ui, storage);
        } catch (CatBotException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "There was an error while saving:\n" + e.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

}
