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
    private Ui ui;
    private boolean isExit;
    /**
     * Constructs an instance of the main application.
     */
    public Duke() {
        this.taskApplication = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        this.isExit = false;
    }
    private String listTasksCommand() {
        return String.format("Here are the tasks in your list:\n%s", this.taskApplication.getAllTaskString());
    }

    private String markCommand(int index) throws DukeException {
        this.taskApplication.markTask(index);
        return String.format("Nice! I've marked this task as done:\n%s", this.taskApplication.getTask(index));
    }

    private String unmarkCommand(int index) throws DukeException {
        this.taskApplication.unmarkTask(index);
        return String.format("OK, I've marked this task as not done yet:\n%s",
                this.taskApplication.getTask(index));
    }

    private String todoCommand(String title) {
        Task t = new ToDo(title);
        this.taskApplication.addTask(t);
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        );
    }

    private String deadlineCommand(String title, String deadline) {
        Task t;
        t = new Deadline(title, deadline);
        this.taskApplication.addTask(t);
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        );
    }

    private String eventCommand(String title, String start, String end) {
        Task t = new Event(title, start, end);
        this.taskApplication.addTask(t);
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        );
    }

    private String deleteCommand(int index) throws DukeException {
        Task t = this.taskApplication.popTask(index);
        return String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        );
    }
    private String findCommand(String keyword) {
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
                return this.markCommand(Integer.parseInt(tokens.get(1)) - 1);
            case "unmark":
                return this.unmarkCommand(Integer.parseInt(tokens.get(1)) - 1);
            case "todo":
                return this.todoCommand(tokens.get(1));
            case "deadline":
                return this.deadlineCommand(tokens.get(1), tokens.get(2));
            case "event":
                return this.eventCommand(tokens.get(1), tokens.get(2), tokens.get(3));
            case "delete":
                return this.deleteCommand(Integer.parseInt(tokens.get(1)) - 1);
            case "find":
                return this.findCommand(tokens.get(1));
            case "bye":
                return this.byeCommand();
            default:
                throw new DukeUnknownCommandException("Unknown command");
        }
    }

    /**
     * Main loop for Duke program.
     */
    public void start() {
        this.ui.printStart();
        while (this.ui.hasNextCommand()) {
            String command = this.ui.readCommand();
            if (command.equals("bye")) {
                this.ui.printEnd();
                this.byeCommand();
                break;
            }
            try {
                this.parseCommand(command);
            } catch (DukeException e) {
                // Any duke.exceptions.DukeException is non-fatal, so we just print them
                this.ui.printBlock(e.getMessage());
            }
        }
    }

    /**
     * Main entry point of the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
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
