package duke.command;
import duke.*;
import duke.task.*;
import java.util.List;
public class AddCommand extends Command { 
    private CommandEnum command;
    private List<String> arg;

    public AddCommand(CommandEnum command, List<String> arg) {
        this.command = command;
        this.arg = arg;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch(this.command) {
            case TODO:
                if (arg.size() == 0) {
                    throw new DukeException("TASK MUST HAS DESCRIPSHUN MEOW");
                }
                String description = String.join(" ", arg);
                taskList.addList(new Todo(description));
                break;
            case DEADLINE:
                try{
                    deadline(taskList, ui, storage);
                } catch (IllegalArgumentException e) {
                    throw new DukeException(e);
                } catch (Exception e) {
                    throw new DukeException(e);
                }
                break;
           case EVENT: 
                try {
                    event(taskList, ui, storage);
                } catch (IllegalArgumentException e) {
                    throw new DukeException(e);
                } catch (Exception e) {
                    throw new DukeException(e);
                }
                break;
            default:
                throw new DukeException();
        }
    }
    private void deadline(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int byIndex = arg.indexOf("/by");
        List<String> commandTempArg = arg.subList(1, byIndex);
        String description = String.join(" ", commandTempArg);
        commandTempArg = arg.subList(byIndex + 1, arg.size());
        String deadline = String.join(" ", commandTempArg);
        taskList.addList(new Deadline(description, deadline));
    }
    private void event(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int fromIndex = arg.indexOf("/from");
        int toIndex = arg.indexOf("/to");
        List<String> commandTempArg = arg.subList(1, fromIndex);
        String description = String.join(" ", commandTempArg);
        List<String> f = arg.subList(fromIndex + 1, toIndex); //f is temp to pass into join func
        String fDescription = String.join(" ", f);
        List<String> t = arg.subList(toIndex + 1, arg.size()); //t is temp to pass into join func
        String tDescription = String.join(" ", commandTempArg);
        tDescription = String.join(" ", t);
        taskList.addList(new Event(description, fDescription, tDescription));
    }
}

