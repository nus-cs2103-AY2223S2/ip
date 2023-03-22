package duke;

import Command.Command;
import DukeException.DukeException;
import Task.TaskList;

/**
 * Github id: adam07018
 *
 * @author Lu Chenyu
 */
public class Duke {
    private TaskList list;
    private Parser parser;
    private Ui ui;
    private boolean isExit;

    /**
     * default constructor
     */
    public Duke() {
        list = new TaskList();
        parser = new Parser();
        ui = new Ui();
        isExit = false;
    }

    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            isExit = cmd.isExit();
            return cmd.execute(list);
        } catch (DukeException e) {
            return e.toString();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public boolean getIsExit() {
        return isExit;
    }

    public Ui getUi() {
        return ui;
    }
}
