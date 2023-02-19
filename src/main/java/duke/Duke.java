package duke;
import duke.dukeexceptions.DukeException;
import duke.functions.Parser;
import duke.functions.Storage;
import duke.task.TaskList;

/**
 * Class that runs the application.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;


    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Constructor of a Duke application.
     *
     * @param filePath path of the file which stores Tasks.
     */
    public Duke(String filePath) {
        storage =  new Storage(filePath);
        taskList = new TaskList(storage);
    }


    /**
     * Runs the application based on the given input read.
     */
    public String run(String s) {
        String result = null;
        try {
            String reply = Parser.understandInput(s, s.split(" "), taskList, storage);
            result = reply;
        } catch (DukeException e) {
            result = e.getMessage();
        } finally {
            return result;
        }
    }

}
