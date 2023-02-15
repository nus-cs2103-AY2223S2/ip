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
     * Construct the response to user's command.
     * @param input The user's command.
     * @return The string representation of the response to user's command.
     */
    //@@author se-education.org
    //Reused from https://se-education.org/guides/tutorials/javaFxPart3.html
    //with minor modifications
    public String getResponse(String input) {
        String output;
        try {
            Parser.createCommand(input);
            output = Parser.execute(this.list);
            if(isBotOff) {
                //@@author Paul S
                //Reused from https://stackoverflow.com/questions/15747277/
                //how-to-make-java-program-exit-after-a-couple-of-seconds
                Executors.newSingleThreadScheduledExecutor().schedule(() ->
                        System.exit(0), 250, TimeUnit.MILLISECONDS);
            }
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }
}
