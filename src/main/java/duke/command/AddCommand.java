package duke.command;
import java.util.List;

import duke.*;
import duke.task.*;
public class AddCommand extends Command {
    private CommandEnum command;
    private List<String> arg;

    public AddCommand(CommandEnum command, List<String> arg) {
        this.command = command;
        this.arg = arg;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch(this.command) {
        case TODO:
            if (arg.size() == 0) {
                assert arg.size() > 0;
                throw new DukeException("TASK MUST HAS DESCRIPSHUN MEOW");
            }
            String description = String.join(" ", arg);
            return taskList.addList(new Todo(description));
        case DEADLINE:
            try {
                return deadline(taskList, ui, storage);
            } catch (IllegalArgumentException e) {
                throw new DukeException(e);
            } catch (Exception e) {
                throw new DukeException(e);
            }
        case EVENT:
            try {
                return event(taskList, ui, storage);
            } catch (IllegalArgumentException e) {
                throw new DukeException(e);
            } catch (Exception e) {
                throw new DukeException(e);
            }
        default:
            return "idk what you are saying";
        }
    }
    private String deadline(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int byIndex = arg.indexOf("/by");
        List<String> commandTempArg = arg.subList(1, byIndex);
        String description = String.join(" ", commandTempArg);
        commandTempArg = arg.subList(byIndex + 1, arg.size());
        String deadline = String.join(" ", commandTempArg);
        return taskList.addList(new Deadline(description, deadline));
    }
    private String event(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int fromIndex = arg.indexOf("/from");
        int toIndex = arg.indexOf("/to");
        List<String> commandTempArg = arg.subList(1, fromIndex);
        String description = String.join(" ", commandTempArg);
        List<String> f = arg.subList(fromIndex + 1, toIndex); //f is temp to pass into join func
        String fDescription = String.join(" ", f);
        List<String> t = arg.subList(toIndex + 1, arg.size()); //t is temp to pass into join func
        String tDescription = String.join(" ", commandTempArg);
        tDescription = String.join(" ", t);
        return taskList.addList(new Event(description, fDescription, tDescription));
    }
}

