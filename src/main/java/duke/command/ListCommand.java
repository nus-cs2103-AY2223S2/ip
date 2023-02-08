package duke.command;

import duke.Duke;

public class ListCommand extends Command {
    @Override
    public ReturnCode execute(Duke duke) {
        if (duke.taskList.size() == 0) {
            duke.ui.println("Your list is empty.");
            return ReturnCode.FAILURE;

        } else if (duke.taskList.size() > 0) {
            duke.ui.println("You have the following task(s):");
            for (int i = 0; i < duke.taskList.size(); i++) {
                duke.ui.println("\t" + (i + 1) + ". " + duke.taskList.get(i));
            }
            return ReturnCode.SUCCESS;

        } else {
            assert false : "taskList has negative number of tasks!!";
            return ReturnCode.FATAL;
        }

    }
}
