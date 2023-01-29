package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class Duke {

    private static final String FILE_PATH = "./data/duke.txt";
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the bot.
     */
    public Duke() {
        this.ui = new Ui("Tyrone");
        this.storage = new Storage(FILE_DIRECTORY, FILE_NAME, ui);
        this.tasks = new TaskList(storage.load());
    }



    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Task must be referenced by its index.");
            } catch (IllegalArgumentException e) {
                ui.showError("Invalid duke command.");
            } catch (DateTimeParseException e) {
                ui.showError("Wrong date format given.");
            }
        }

        storage.save(tasks.getList());
        ui.terminate();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }
}
