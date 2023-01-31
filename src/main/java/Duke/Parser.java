package Duke;

import Exceptions.CommandNotFoundException;
import Duke.Storage.Storage;

public class Parser {
    private TaskList t;
    private Storage storage;

    public Parser(TaskList tasks, Storage storage) {
        this.t = tasks;
        this.storage = storage;
    }

    public void parse(String input) throws CommandNotFoundException {
        assert (input != null) : "The command cannot be null.";
        String[] splitCommand = input.split(" ");
        String command = splitCommand[0].toUpperCase();
        String splitDescription;
        switch (command) {
        case "LIST":
            t.list();
            break;
        case "MARK":
            t.mark(Integer.parseInt(splitCommand[1]));
            break;
        case "UNMARK":
            t.unmark(Integer.parseInt(splitCommand[1]));
            break;
        case "DELETE":
            t.delete(Integer.parseInt(splitCommand[1]));
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
        default:
            throw new CommandNotFoundException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
