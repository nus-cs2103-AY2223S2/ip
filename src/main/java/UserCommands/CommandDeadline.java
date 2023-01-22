package UserCommands;

import Features.DukeException;
import Features.TaskList;
import Features.Ui;
import Tasks.Deadline;
import Tasks.Task;

import java.util.Scanner;

public class CommandDeadline extends Command{
    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        Ui ui = new Ui();
        try {
            String deadlineSentence = userScan.nextLine();
            String deadlineName = deadlineSentence.substring(0, deadlineSentence.indexOf(" /by"));
            // ERROR: deadline description is blank.
            if (deadlineName.strip().length()==0) {
                throw new DukeException(ui.formatLogicError("deadline description cannot be blank."));
            }
            String deadlineDate = deadlineSentence.substring(deadlineSentence.indexOf(" /by")+5);
            // ERROR: deadline date is blank.
            if (deadlineDate.strip().length()==0) {
                throw new DukeException(ui.formatLogicError("deadline date cannot be blank."));
            }
            Task deadlineToAdd = new Deadline(deadlineName, deadlineDate);
            taskList.add(deadlineToAdd);
            ui.print("Task added:\n " + deadlineToAdd + "\n" + "There are now " + taskList.size() +
                    " task(s) in your list.");
            return taskList;
        }

        // ERROR: deadline format is anything other than [ deadline /by <insert deadline> ]
        catch (StringIndexOutOfBoundsException err) {
            throw new DukeException(ui.formatCommandError("deadline",
                    "deadline <insert description> " +
                            "/by <insert deadline>"));
        }
    }
}
