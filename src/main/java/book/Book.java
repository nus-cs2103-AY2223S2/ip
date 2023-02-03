package book;

import java.nio.file.Path;
import java.nio.file.Paths;

import book.command.Command;
import book.exception.BookException;
import book.gui.Gui;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * Main class of {@code Book}.
 */
public class Book {
    /** Default {@code Path} to the save. */
    private static final Path DEFAULT_PATH = Paths.get("save", "book.txt");
    /** {@code Storage} associated with {@code Book}. */
    private Storage storage;
    /** {@code TaskList} associated with {@code Book}. */
    private TaskList list;
    /** {@code Ui} associated with {@code Book}. */
    private Ui ui;

    /**
     * Initializes a {@code Book} with a fixed {@code Path} to the {@code Book} save, if no save
     * is found, initializes an empty {@code Book}.
     */
    public Book() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_PATH);
        try {
            this.list = new TaskList(this.storage.load());
        } catch (BookException exception) {
            ui.showError(exception.getMessage());
            this.list = new TaskList();
        }
    }

    /**
     * Passes any {@code String userInput} from the {@code Gui} to {@code Parser}, then executes
     * the returned {@code Command} and returns the resulting {@code String}.
     *
     * @param userInput {@code String userInput} from the {@code Gui}.
     * @return {@code String} to be displayed on the {@code Gui}.
     */
    public String parseAndReturn(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            String commandOutput = command.execute(this.storage, this.list, this.ui);
            if (command.isExit()) {
                Platform.exit();
            }
            return commandOutput;
        } catch (BookException exception) {
            return this.ui.showError(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
