package duke.core;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.DoAfter;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Core is the central class that processes and executes all commands.
 */
public class Core {
    private TaskList tasks;
    private Ui ui;
    private boolean isOnline;

    /**
     * Initialises a Duke application
     */
    public Core() {
        tasks = new TaskList();
        ui = new Ui();
        isOnline = true;
    }

    private void handleCommand(String cmd) throws DukeException {
        assert isOnline : "Commands to be handled only when online";
        if (cmd.matches("^bye$")) {
            ui.bye();
            isOnline = false;
        } else if (cmd.matches("^list$")) {
            ui.listTasks(tasks);
        } else if (cmd.matches("^mark [0-9]*$")) {
            int index = Integer.parseInt(cmd.substring(5)) - 1;
            Task task = tasks.mark(index);
            ui.markTask(task);
        } else if (cmd.matches("^unmark [0-9]*$")) {
            int index = Integer.parseInt(cmd.substring(7)) - 1;
            Task task = tasks.unmark(index);
            ui.unmarkTask(task);
        } else if (cmd.matches("^todo .*$")) {
            String taskName = cmd.substring(5);
            Task task = new Todo(taskName, false);
            tasks.add(task);
            ui.addTask(task, tasks.length());
        } else if (cmd.matches("^deadline .* /by .*$")) {
            int byStart = cmd.indexOf("/by");
            String taskName = cmd.substring(9, byStart - 1);
            String by = cmd.substring(byStart + 4);
            Task task = tasks.add(new Deadline(taskName, false, by));
            ui.addTask(task, tasks.length());
        } else if (cmd.matches("^event .* /from .* /to .*$")) {
            int byStart = cmd.indexOf("/from");
            int toStart = cmd.indexOf("/to");
            String taskName = cmd.substring(6, byStart - 1);
            String by = cmd.substring(byStart + 6, toStart - 1);
            String to = cmd.substring(toStart + 4);
            Task task = tasks.add(new Event(taskName, false, by, to));
            ui.addTask(task, tasks.length());
        } else if (cmd.matches("^delete [0-9]*$")) {
            int index = Integer.parseInt(cmd.substring(7)) - 1;
            Task task = tasks.delete(index);
            ui.deleteTask(task, tasks.length());
        } else if (cmd.matches("^after .* /after .*$")) {
            int afterStart = cmd.indexOf("/after");
            String taskName = cmd.substring(6, afterStart - 1);
            String after = cmd.substring(afterStart + 7);
            Task task = tasks.add(new DoAfter(taskName, false, after));
            ui.addTask(task, tasks.length());
        } else if (cmd.matches("^find .*$")) {
            String pattern = cmd.substring(5);
            TaskList filteredTasks = tasks.matches(pattern);
            ui.listMatchingTasks(filteredTasks);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public String setup() {
        assert tasks != null : "tasks have been initialised";
        assert ui != null : "ui has been initialised";
        try {
            tasks.load();
            ui.introduce();
        } catch (Exception e) {
            ui.error(e);
        }
        return ui.flush();
    }

    /**
     * Returns the output generated processing a command {@code cmd}
     *
     * @param cmd Command to be processed.
     * @return Output generated from processing a command.
     */
    public String respond(String cmd) {
        if (isOnline) {
            try {
                handleCommand(cmd);
            } catch (DukeException e) {
                ui.error(e);
            }
        } else {
            ui.respond("The session has ended, you may close the window.");
        }
        return ui.flush();
    }
}
