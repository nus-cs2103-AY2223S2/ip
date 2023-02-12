package rick.command;

import java.util.ArrayList;

import rick.TaskList;
import rick.Ui;
import rick.exceptions.RickException;
import rick.lambdas.CheckedTaskListManipulator;


/**
 * Represents a Command that manipulates multiple tasks.
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
 */
public class MultiManipulateCommand extends Command {
    private String userInterfaceMessage;
    private CheckedTaskListManipulator<Integer, String> commandFunction;
    private final ArrayList<Integer> indexes;

    /**
     * Constructs an instance of this command and populates it with the
     * necessary values.
     *
     * @param indexes The storage indexes to manipulate.
     * @param cmd The command to execute over the provided indexes.
     */
    public MultiManipulateCommand(ArrayList<Integer> indexes, String cmd) {
        this.indexes = indexes;
        switch (cmd) {
        case "delete":
            this.commandFunction = (TaskList ts, Integer index) -> ts.basicDelete(index);
            this.userInterfaceMessage = "Got it. I've deleted these %s tasks:";
            break;
        case "mark":
            this.commandFunction = (TaskList ts, Integer index) -> ts.basicManipulate(index, true);
            this.userInterfaceMessage = "Got it. I've marked these %s tasks as done:";
            break;
        case "unmark":
            this.commandFunction = (TaskList ts, Integer index) -> ts.basicManipulate(index, false);
            this.userInterfaceMessage = "Got it. I've marked these %s tasks as not completed:";
            break;
        default:
            assert false;
        }
    }

    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     * @return The UI to output from executing the command.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<String> tasks = new ArrayList<>();
        for (Integer index: this.indexes) {
            try {
                String task = this.commandFunction.apply(ts, index);
                tasks.add(task);
            } catch (RickException e) {
                errors.add(e.getMessage());
            }
        }
        if (tasks.size() > 0) {
            output.add(String.format(this.userInterfaceMessage, tasks.size()));
            output.addAll(tasks);
        } else {
            output.add("Sorry. No tasks were successfully modified.");
        }
        if (errors.size() > 0) {
            output.add(" ");
            output.add(String.format("These %s error(s) occurred:", errors.size()));
            output.addAll(errors.subList(0, Math.min(5, errors.size()))); //limit if range is large
        }
        return ui.section(output.toArray(String[]::new));
    }
}
