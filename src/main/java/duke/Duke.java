package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.List;

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
    private String listTasksCommand() {
        return String.format("Here are the tasks in your list:\n%s", this.taskApplication.getAllTaskString());
    }

    private String markCommand(List<String> tokens) throws DukeException {
        int index = Integer.parseInt(tokens.get(1)) - 1;
        this.taskApplication.markTask(index);
        return String.format("Nice! I've marked this task as done:\n%s", this.taskApplication.getTask(index));
    }

    private String unmarkCommand(List<String> tokens) throws DukeException {
        int index = Integer.parseInt(tokens.get(1)) - 1;
        this.taskApplication.unmarkTask(index);
        return String.format("OK, I've marked this task as not done yet:\n%s", this.taskApplication.getTask(index));
    }

    private String todoCommand(List<String> tokens) {
        Task newTask = new ToDo(tokens.get(1));
        this.taskApplication.addTask(newTask);
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, this.taskApplication.getNoOfTasks()
        );
    }

    private String deadlineCommand(List<String> tokens) {
        Task newTask = new Deadline(tokens.get(1), tokens.get(2));
        this.taskApplication.addTask(newTask);
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, this.taskApplication.getNoOfTasks()
        );
    }

    private String eventCommand(List<String> tokens) {
        Task newTask = new Event(tokens.get(1), tokens.get(2), tokens.get(3));
        this.taskApplication.addTask(newTask);
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, this.taskApplication.getNoOfTasks()
        );
    }

    private String deleteCommand(List<String> tokens) throws DukeException {
        int index = Integer.parseInt(tokens.get(1)) - 1;
        Task t = this.taskApplication.popTask(index);
        return String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        );
    }
    private String findCommand(List<String> tokens) {
        String keyword = tokens.get(1);
        List<Task> tasks = this.taskApplication.getTaskByKeyword(keyword);
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (Task s: tasks) {
            result.append(String.format("%d.%s\n", i, s));
            i++;
        }
        String output = result.toString().replaceAll("\\n$", "");
        return String.format("Here are the matching tasks in your list:\n%s", output);
    }

    private String byeCommand() {
        this.taskApplication.close();
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
                throw new DukeUnknownCommandException("Unknown command");
        }
    }
    public boolean getIsExit() {
        return this.isExit;
    }
    public String getResponse(String input) {
        try {
            return this.parseCommand(input);
        } catch (DukeException e) {
            // Any duke.exceptions.DukeException is non-fatal, so we just print them
            return e.getMessage();
        }
    }
}
