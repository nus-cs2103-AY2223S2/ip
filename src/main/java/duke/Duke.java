package duke;

import duke.exception.DukeException;
import duke.task.TaskList;


/**
 * Defines the main logic of the Duke bot.
 */
public class Duke {
    private TaskList list;
    private static boolean isBotOff = false;
    private Storage storage;
    public static boolean isIsBotOff = false;


    Duke() {
        this.storage = new Storage("tasks.ser");
        assert storage != null: "New storage object was created";
        this.list = new TaskList(storage);
    }

    public String loadStorage() {
        return storage.loadTasks(this.list);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    //Adapted from JavaFX tutorial
    public String getResponse(String input) {
        String output;
        try {
            Parser.createCommand(input);
            output = Parser.execute(this.list);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }
}
