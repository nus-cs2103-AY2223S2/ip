package Duke;

import Duke.Storage.Storage;
import Duke.Tasks.Task;

import Duke.Exceptions.CommandNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private TaskList t;
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
    public String parse(String input) throws CommandNotFoundException, IOException {
        assert (input != null) : "The Duke.command cannot be null.";
        String[] splitCommand = input.split(" ");
        String command = splitCommand[0].toUpperCase();
        String splitDescription;
        String response;
        switch (command) {
        case "LIST":
            response = t.list();
            break;
        case "MARK":
            response = t.mark(Integer.parseInt(splitCommand[1]));
            break;
        case "UNMARK":
            response = t.unmark(Integer.parseInt(splitCommand[1]));
            break;
        case "DELETE":
            response = t.delete(Integer.parseInt(splitCommand[1]));
            break;
        case "TODO":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            t.addTodo(splitDescription);
            break;
        case "DEADLINE":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            t.addDeadline(splitDescription);
            break;
        case "EVENT":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            t.addEvent(splitDescription);
            break;
        case "FIND":
            splitDescription = input.split(" ", 2).length == 2
                    ? input.split(" ", 2)[1]
                    : "";
            ArrayList<Task> filteredTasks = t.findRelevantTasks(splitDescription);
            System.out.println(filteredTasks);
            break;
        default:
            throw new CommandNotFoundException("I'm sorry, but I don't know what that means :-(");
        }
//        storage.storeTasks();
        return response;
    }
}
