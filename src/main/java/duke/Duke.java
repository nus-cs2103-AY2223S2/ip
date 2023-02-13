package duke;

import duke.commands.Command;
import javafx.util.Pair;

/**
 * The main class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Duke constructor.
     */
    public Duke() {
        String filePath = "tasks.txt";
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadTaskList();
        } catch (Exception e) {
            System.out.println(Ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /**
     * Shows welcome message on start.
     *
     * @return String The welcome message.
     */
    public String showWelcomeMessage() {
        return Ui.showWelcomeMessage();
    }


    /**
     * Generates a response based on user input.
     *
     * @param input The user input.
     * @return String of the response.
     */
    public Pair<String, Boolean> getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            return new Pair<>(c.execute(this.tasks, this.storage), false);
        } catch (IllegalArgumentException e) {
            return new Pair<>("Unrecognised command. Try again.", true);
        } catch (Exception e) {
            return new Pair<>(e.getMessage(), true);
        }
    }
}
