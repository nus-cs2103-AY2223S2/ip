package duke;

import duke.commands.Command;

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
     * Generate response based on user input.
     *
     * @param input The user input.
     * @return String of the response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            return c.execute(this.tasks, this.storage);
        } catch (IllegalArgumentException e) {
            return "Unrecognised command. Try again.";
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
