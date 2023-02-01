package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;

public class Mark extends Command {
    private String cmdLine;

    public Mark(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (cmdLine.length() <= 5) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you want to mark any task or not?");
            int i = Integer.parseInt(cmdLine.substring(5));
            lst.get(i - 1).mark();
            String response = "";
            response += "Good! You finished that! I marked that as done. Roarrrrrrrrrrrrrr!\n";
            response += "  " + lst.get(i - 1).toString() + "\n";
            storage.save(lst);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Roarrrrrrrrrrrrrrrrrrrr! I cannot identify that task as it is not an integer!";
        } catch (IndexOutOfBoundsException e) {
            return "Roarrrrrrrrrrrrrrrrrrrr! You did not add that many tasks in the list!";
        }
    }
}
