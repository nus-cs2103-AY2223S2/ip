package duke;

import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * MainApplication is the main application of the Duke program. It serves as a
 * translation layer between the command line and model classes.
 */
public class MainApplication {
    private TaskList taskApplication;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs an instance of the main application.
     */
    public MainApplication() {
        this.taskApplication = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
    }
    private void listTasksCommand() {
        this.ui.printBlock(String.format("Here are the tasks in your list:\n%s",
                this.taskApplication.getAllTaskString()));
    }

    private void markCommand(int index) throws DukeException {
        this.taskApplication.markTask(index);
        this.ui.printBlock(String.format("Nice! I've marked this task as done:\n%s",
                this.taskApplication.getTask(index)));
    }

    private void unmarkCommand(int index) throws DukeException {
        this.taskApplication.unmarkTask(index);
        this.ui.printBlock(String.format("OK, I've marked this task as not done yet:\n%s",
                this.taskApplication.getTask(index)));
    }

    private void todoCommand(String title) {
        Task t = new ToDo(title);
        this.taskApplication.addTask(t);
        this.ui.printBlock(String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        ));
    }

    private void deadlineCommand(String title, String deadline) {
        Task t;
        t = new Deadline(title, deadline);
        this.taskApplication.addTask(t);
        this.ui.printBlock(String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        ));
    }

    private void eventCommand(String title, String start, String end) {
        Task t = new Event(title, start, end);
        this.taskApplication.addTask(t);
        this.ui.printBlock(String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        ));
    }

    private void deleteCommand(int index) throws DukeException {
        Task t = this.taskApplication.popTask(index);
        this.ui.printBlock(String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                t, this.taskApplication.getNoOfTasks()
        ));
    }
    private void findCommand(String keyword) {
        List<Task> tasks = this.taskApplication.getTaskByKeyword(keyword);
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (Task s: tasks) {
            result.append(String.format("%d.%s\n", i, s));
            i++;
        }
        String output = result.toString().replaceAll("\\n$", "");
        this.ui.printBlock(String.format(
                "Here are the matching tasks in your list:\n%s", output
        ));
    }

    private void byeCommand() {
        this.taskApplication.close();
    }
    private void parseCommand(String command) throws DukeException {
        List<String> tokens = this.parser.parseCommand(command);
        switch(tokens.get(0)) {
        case "list":
            this.listTasksCommand();
            break;
        case "mark":
            this.markCommand(Integer.parseInt(tokens.get(1)) - 1);
            break;
        case "unmark":
            this.unmarkCommand(Integer.parseInt(tokens.get(1)) - 1);
            break;
        case "todo":
            this.todoCommand(tokens.get(1));
            break;
        case "deadline":
            this.deadlineCommand(tokens.get(1), tokens.get(2));
            break;
        case "event":
            this.eventCommand(tokens.get(1), tokens.get(2), tokens.get(3));
            break;
        case "delete":
            this.deleteCommand(Integer.parseInt(tokens.get(1)) - 1);
            break;
        case "find":
            this.findCommand(tokens.get(1));
            break;
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
}
