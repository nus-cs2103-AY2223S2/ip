package duke;

import exception.DukeException;
import storage.Storage;
import ui.StringParser;
import ui.Ui;
import tasks.TaskList;
import java.util.Scanner;
import command.Command;

/**
 * Represents the duke.Duke chatbot
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private StringParser sp;

    /**
     * Constructor for duke.Duke object
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        sp = new StringParser();
        try {
            taskList = new TaskList();
        } catch (DukeException e) {
            ui.errMsg(e.getMessage());
        }
    }

    public String getResponse(String input){
        try{
            Command c = sp.parse(input);
            return c.execute(taskList, storage, ui);
        } catch(DukeException e){
            return e.getMessage();
        }
    }
}
