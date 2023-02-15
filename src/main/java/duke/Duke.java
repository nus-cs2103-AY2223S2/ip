package duke;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Defines the main logic of the Duke bot.
 */
public class Duke {
    public static boolean isBotOff = false;
    private TaskList list;
    private Storage storage;

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
            if(isBotOff) {
                //Credits: Copied from https://stackoverflow.com/questions/15747277/
                // how-to-make-java-program-exit-after-a-couple-of-seconds
                Executors.newSingleThreadScheduledExecutor().schedule(() ->
                        System.exit(0), 250, TimeUnit.MILLISECONDS);
            }
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }
}
