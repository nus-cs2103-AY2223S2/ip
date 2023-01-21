import java.nio.file.Path;
import java.nio.file.Paths;

public class Book {
    private Storage storage;
    private TaskList list;
    private Ui ui;

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
