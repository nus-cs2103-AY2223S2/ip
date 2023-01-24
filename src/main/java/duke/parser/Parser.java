package duke.parser;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
import duke.task.TaskType;
import duke.tasklist.TaskList;

import java.util.Arrays;

public class Parser {
    private final TaskList tasks;
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public String parseAndExecute(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        String fn = inputArray[0];
        String info = "";
        if (inputArray.length > 1) {
            info = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
        }
        switch(fn) {
            case "list":
                return tasks.list();
            case "mark":
                return tasks.mark(info.strip());
            case "unmark":
                return tasks.unMark(info.strip());
            case "delete":
                return tasks.delete(info.strip());
            case "todo":
                return tasks.add(TaskType.ToDos, info.strip());
            case "deadline":
                return tasks.add(TaskType.Deadlines, info.strip());
            case "event":
                return tasks.add(TaskType.Events, info.strip());
            case "bye":
                return "\t Bye. Hope to see you again soon!";
            default:
                throw new DukeUnknownCommandException();
        }
    }
}
