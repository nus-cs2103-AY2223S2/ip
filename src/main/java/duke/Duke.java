package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {
    public static final String LOGO = "_____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    private TaskList tasks;
    private Ui ui;
    private boolean isListening;

    public Duke() {
        tasks = new TaskList();
        ui = new Ui();
        try {
            tasks.load();
        } catch (Exception e) {
            ui.error(e);
        }
    }

    public void handleCommand(String cmd) throws DukeException {
        if (cmd.matches("^bye$")) {
            ui.bye();
            isListening = false;
        } else if (cmd.matches("^list$")) {
            ui.listTasks(tasks);
        } else if (cmd.matches("^mark [0-9]*$")) {
            int index = Integer.parseInt(cmd.substring(5)) - 1;
            Task task = tasks.mark(index);
            ui.markTask(task);
        } else if (cmd.matches("^unmark [0-9]*$")) {
            int index = Integer.parseInt(cmd.substring(5)) - 1;
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
        } else if (cmd.matches("^find .*$")) {
            String pattern = cmd.substring(5);
            TaskList filteredTasks = tasks.matches(pattern);
            ui.listMatchingTasks(filteredTasks);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");    
        }
    }

    public void start() {
        ui.introduce(LOGO);
        isListening = true;
        while(isListening) {
            String cmd = ui.ask();
            try {
                handleCommand(cmd);
            } catch (DukeException e) {
                ui.error(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
