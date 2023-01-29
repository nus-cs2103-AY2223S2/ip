package duke.command;

import duke.tasklist.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList) {
        System.out.println("Bye. Have a nice day!");
    }

}
