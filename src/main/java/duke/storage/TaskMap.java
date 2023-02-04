package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskMap {
    /**
     * Assists in loading from file
     */

    /**
     * Method to assist in interpreting lines from the save file. If line starts with particular letter, corresponding tasks will be created
     *
     * @param s Particular letter
     * @return Corresponding object
     */
    static public Task get(String s) {
        Task t = null;
        switch (s) {
        case "T":
            t = new ToDo();
            break;
        case "E":
            t = new Event();
            break;
        case "D":
            t = new Deadline();
            break;
        }
        assert t == null : "Task not loaded properly";
        if (t == null) {
            t = new Task();
        }
        return t;
    }
}
