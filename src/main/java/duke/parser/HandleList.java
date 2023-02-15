package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.TaskList;

public class HandleList {
    public HandleList() {
    }

    public static String performList(String input, TaskList tasklist) throws WrongFormatException {
        boolean correctFormat = input.equals("list");

        if (! correctFormat) {
            throw new WrongFormatException("list");
        }

        return tasklist.printList();

    }


}
