package book;

import java.nio.file.Path;
import java.nio.file.Paths;

import book.command.Command;
import book.exception.BookException;

/**
 * Main class of {@code Book}.
 */
public class Book {
    /** {@code Storage} associated with {@code Book}. */
    private Storage storage;
    /** {@code TaskList} associated with {@code Book}. */
    private TaskList list;
    /** {@code Ui} associated with {@code Book}. */
    private Ui ui;

    /**
     * Initializes a {@code Book} with the given {@code Path} to the {@code Book} save, if no save
     * is found, initializes an empty {@code Book}.
     * @param filePath {@code Path} for locating the saved {@code Book}.
     */
    public Book(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(this.storage.load());
        } catch (BookException exception) {
            ui.showError(exception.getMessage());
            this.list = new TaskList();
        }
    }

    /**
     * Runs the {@code Book}.
     */
    public void run() {
        this.ui.showWelcome();
        this.ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parse(ui.readCommand());
                command.execute(this.storage, this.list, this.ui);
                isExit = command.isExit();
            } catch (BookException exception) {
                this.ui.showError(exception.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Book(Paths.get("save", "book.txt")).run();
    }
}
