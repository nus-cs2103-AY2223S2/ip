package sebastian.main;

import sebastian.command.Command;
import sebastian.exceptions.CannotLoadDataException;
import sebastian.exceptions.SebastianException;

/**
 * Class representing the task manager, Sebastian
 */
public class Sebastian {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isActive;

    /**
     * Constructor
     */
    public Sebastian() {
        ui = new Ui();
        storage = new Storage();
    }

    /**
     * Generates the starting message of a session
     * @return the starting message
     */
    public String getStartingMessage() {
        try {
            isActive = true;
            tasks = storage.formTaskListFromData();
            return ui.getGreeting();
        } catch (CannotLoadDataException e) {
            tasks = new TaskList();
            return ui.getError(e.getMessage()) + "\n" + ui.getGreeting();
        }
    }

    /**
     * Produces a response to the user command
     * @param input user command
     * @return response to the user command
     */
    public String getResponse(String input) {
        if (isActive) {
            try {
                Command c = Parser.parse(input);
                String res = c.execute(tasks, ui, storage);
                isActive = !c.isExit();
                return res;
            } catch (SebastianException e) {
                return ui.getError(e.getMessage());
            }
        } else {
            return "Sebastian is offline...";
        }
    }
}
