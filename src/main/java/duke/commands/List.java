package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;
import duke.DukeException;

public class List extends Command {
    public List() {
    }

    public void operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if(lst.size()==0) throw new DukeException("Roarrrrrrrrrrrrrrrrr! You did not add anything in the list!");
            System.out.println("Roarrrrrrrrrrrrrrr! Task list shown below!");
            for (int i = 1; i <= lst.size(); ++i) {
                System.out.println(i + "." + lst.get(i-1).toString());
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
