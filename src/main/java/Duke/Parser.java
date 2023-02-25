package Duke;

import Duke.command.*;
import Duke.Exceptions.CommandNotFoundException;
import Duke.Exceptions.InvalidIndexException;
import Duke.Storage.Storage;
import Duke.Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private static TaskList t;
    private Storage storage;

    /**
     * Constructor of the parser class.
     *
     * @param tasks The task list that is stored inside the file.
     * @param storage
     */
    public Parser(TaskList tasks, Storage storage) {
        this.t = tasks;
        this.storage = storage;
    }

    /**
     * Parses the input by the users to corresponding commands
     *
     * @param input The Duke.command inserted by the users.
     * @throws CommandNotFoundException
     */
    public static Command parse(String input) throws CommandNotFoundException, IOException {
        //Implement assert feature into the code
        assert (input != null) : "The Duke.command cannot be null.";
        String[] splitCommand = input.split(" ");
        String command = splitCommand[0].toUpperCase();
        String splitDescription;
        Command cmd;
        switch (command) {
        case "BYE":
            cmd = new ExitCommand();
            break;
        case "LIST":
            t.list();
            cmd = new ListCommand();
            break;
        case "MARK":
            try {
                cmd = new MarkCommand(Integer.parseInt(splitCommand[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
            break;
        case "UNMARK":
            try {
                cmd = new UnmarkCommand(Integer.parseInt(splitCommand[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
            break;
        case "DELETE":
            try {
                int index = Integer.parseInt(input.substring(7));
                cmd = new DeleteCommand(index);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
            break;
        case "TODO":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            t.addTodo(splitDescription);
            cmd = new AddTodoCommand(splitDescription);
            break;
        case "DEADLINE":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            t.addDeadline(splitDescription);
            cmd = new AddDeadlineCommand(splitDescription);
            break;
        case "EVENT":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            t.addEvent(splitDescription);
            cmd = new AddEventCommand(splitDescription);
            break;
        case "FIND":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            ArrayList<Task> filteredTasks = t.findRelevantTasks(splitDescription);
            cmd = new FindCommand(filteredTasks.toString());
            break;
        case "REMIND":
            String tasksDueSoon = t.findTaskDueSoon();
            cmd = new ReminderCommand(tasksDueSoon);
            break;
        default:
            throw new CommandNotFoundException("I'm sorry, but I don't know what that means :-(");
        }
        return cmd;
    }
}
