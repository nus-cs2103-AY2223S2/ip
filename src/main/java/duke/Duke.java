package duke;

import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Main entry point of the program.
 */
public class Duke {

    private TaskList taskApplication;
    private Parser parser;
    private boolean isExit;
    /**
     * Constructs an instance of the main application.
     */
    public Duke() {
        this.taskApplication = new TaskList();
        this.parser = new Parser();
        this.isExit = false;
    }

    private String getTasksAsString(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (Task s: tasks) {
            result.append(String.format("%d. %s\n", i, s));
            i++;
        }
        return result.toString().replaceAll("\\n$", "");
    }

    private String listTasksCommand() {
        return String.format("Here are the tasks in your list:\n%s", this.taskApplication.getAllTaskString());
    }

    private String markCommand(List<String> tokens) throws DukeException {
        assert tokens.size() == 2;
        int index = Integer.parseInt(tokens.get(1)) - 1;
        this.taskApplication.markTask(index);
        this.taskApplication.save();
        return String.format("Nice! I've marked this task as done:\n%s", this.taskApplication.getTask(index));
    }

    private String unmarkCommand(List<String> tokens) throws DukeException {
        assert tokens.size() == 2;
        int index = Integer.parseInt(tokens.get(1)) - 1;
        this.taskApplication.unmarkTask(index);
        this.taskApplication.save();
        return String.format("OK! I've marked this task as not done yet:\n%s", this.taskApplication.getTask(index));
    }

    private String todoCommand(List<String> tokens) {
        assert tokens.size() == 2;
        Task newTask = new ToDo(tokens.get(1));
        this.taskApplication.addTask(newTask);
        this.taskApplication.save();
        return String.format(
                "OK! I've added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, this.taskApplication.getNoOfTasks()
        );
    }

    private String deadlineCommand(List<String> tokens) {
        assert tokens.size() == 3;
        Task newTask = new Deadline(tokens.get(1), tokens.get(2));
        this.taskApplication.addTask(newTask);
        this.taskApplication.save();
        return String.format(
                "OK! I've added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, this.taskApplication.getNoOfTasks()
        );
    }

    private String eventCommand(List<String> tokens) {
        assert tokens.size() == 4;
        Task newTask = new Event(tokens.get(1), tokens.get(2), tokens.get(3));
        this.taskApplication.addTask(newTask);
        this.taskApplication.save();
        return String.format(
                "OK! I've added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, this.taskApplication.getNoOfTasks()
        );
    }

    private String deleteCommand(List<String> tokens) throws DukeException {
        assert tokens.size() > 1;
        List<Integer> indexes = tokens
                .stream()
                .skip(1)
                .map(s -> Integer.parseInt(s) - 1)
                .collect(Collectors.toList());
        List<Task> tasks = this.taskApplication.popMultipleTasks(indexes);
        this.taskApplication.save();
        return String.format("OK! I've removed this task:\n%s\nNow you have %d tasks in the list.",
                getTasksAsString(tasks), this.taskApplication.getNoOfTasks());
    }

    private String findCommand(List<String> tokens) {
        assert tokens.size() == 2;
        String keyword = tokens.get(1);
        List<Task> tasks = this.taskApplication.getTaskByKeyword(keyword);
        return String.format("Here are the tasks that you want:\n%s",
                getTasksAsString(tasks));
    }

    private String byeCommand() {
        this.taskApplication.save();
        this.isExit = true;
        return "Bye. Hope to see you again soon!";
    }
    private String parseCommand(String command) throws DukeException {
        List<String> tokens = this.parser.parseCommand(command);
        switch(tokens.get(0)) {
        case "list":
            return this.listTasksCommand();
        case "mark":
            return this.markCommand(tokens);
        case "unmark":
            return this.unmarkCommand(tokens);
        case "todo":
            return this.todoCommand(tokens);
        case "deadline":
            return this.deadlineCommand(tokens);
        case "event":
            return this.eventCommand(tokens);
        case "delete":
            return this.deleteCommand(tokens);
        case "find":
            return this.findCommand(tokens);
        case "bye":
            return this.byeCommand();
        default:
            throw new DukeUnknownCommandException("I have no idea what you just said.");
        }
    }
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Executes the given command and returns the output of that command.
     * @param input user command.
     * @return Output of user command.
     */
    public String getResponse(String input) {
        try {
            return this.parseCommand(input);
        } catch (DukeException e) {
            // Any duke.exceptions.DukeException is non-fatal, so we just print them
            return e.getMessage();
        }
    }
}
