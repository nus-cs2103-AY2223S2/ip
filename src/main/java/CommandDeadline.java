import java.io.IOException;

public class CommandDeadline extends Command{
    @Override
    public void handle() throws DukeException {
        try {
            String deadlineSentence = Duke.userScan.nextLine();
            String deadlineName = deadlineSentence.substring(0, deadlineSentence.indexOf(" /by"));
            // ERROR: deadline description is blank.
            if (deadlineName.strip().length()==0) {
                throw new DukeException(Duke.ui.formatLogicError("deadline description cannot be blank."));
            }
            String deadlineDate = deadlineSentence.substring(deadlineSentence.indexOf(" /by")+5);
            // ERROR: deadline date is blank.
            if (deadlineDate.strip().length()==0) {
                throw new DukeException(Duke.ui.formatLogicError("deadline date cannot be blank."));
            }
            Task deadlineToAdd = new Deadline(deadlineName, deadlineDate);
            Duke.taskList.add(deadlineToAdd);
            Duke.ui.print("Task added:\n " + deadlineToAdd + "\n" + "There are now " + Duke.taskList.size() +
                    " task(s) in your list.");
            Duke.dukeSave.saveTaskList(Duke.taskList);
        }
        catch (IOException err) {
            throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
        }
        // ERROR: deadline format is anything other than [ deadline /by <insert deadline> ]
        catch (StringIndexOutOfBoundsException err) {
            throw new DukeException(Duke.ui.formatCommandError("deadline",
                    "deadline <insert description> " +
                            "/by <insert deadline>"));
        }
    }
}
