package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.TaskList;
import duke.ui.Ui;

public class HandleList {
    public HandleList() {
    }

    public static String performList(String input, TaskList tasklist, Ui ui) throws WrongFormatException {
        boolean correctFormat = input.equals("list");

        if (! correctFormat) {
            throw new WrongFormatException("list");
        }

        return ui.showList(tasklist.printList());

    }


}
