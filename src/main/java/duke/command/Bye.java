package duke.command;

import duke.Duke;
import duke.task.TaskList;

public class Bye extends Commands {
    public Bye(String str) {
    }
    @Override
    public void execute(TaskList list) {
        Duke.offBot = true;
        System.out.println("Duke.Command.Bye. Hope to see you again soon!");
        return;
    }
}
