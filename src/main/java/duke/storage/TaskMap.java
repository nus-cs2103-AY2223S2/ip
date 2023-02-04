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
        switch (s) {
        case "T":
            return new ToDo();
        case "E":
            return new Event();
        case "D":
            return new Deadline();
        }
        return new Task();
    }
}
