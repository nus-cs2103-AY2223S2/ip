package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;
import duke.DukeException;

public class List extends Command {
    public List() {
    }

    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (lst.size() == 0) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! You did not add anything in the list!");
            }

            String response = "";
            response += "Roarrrrrrrrrrrrrrr! Task list shown below!\n";
            for (int i = 1; i <= lst.size(); ++i) {
                response += i + "." + lst.get(i-1).toString() + "\n";
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
