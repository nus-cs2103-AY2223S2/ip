package duke.command;

import java.time.LocalDateTime;

import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a command which adds a task into the task list.
 * This includes command "todo", "deadline" and "event".
 */
public class AddCommand extends Command {
    protected String name;
    protected LocalDateTime by;
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Class Constructor.
     * @param commandName Input command name.
     * @param name Input task name.
     * @param by Due date for a Deadline task.
     * @param from From date for an Event task.
     * @param to To date for an Event task.
     */
    public AddCommand(String commandName, String name, LocalDateTime by, LocalDateTime from, LocalDateTime to) {
        super(commandName);
        this.name = name;
        switch (this.commandName) {
        case "deadline": {
            this.by = by;
            break;
        }
        case "event": {
            this.from = from;
            this.to = to;
            break;
        }
        default: {
            break;
        }
        }
    }

    /**
     * Executes the command to add task into the task list.
     * @param tasks Current task list.
     * @param ui Ui to show messages.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        String result = "";
        if (this.commandName.equals("todo")) {
            Todo t = new Todo(this.name);
            result = result + ui.addTodo(t) + "\n";
            tasks.add(t);
        } else if (this.commandName.equals("deadline")) {
            Deadline d = new Deadline(this.name, this.by);
            result = result + ui.addDeadline(d) + "\n";
            tasks.add(d);
        } else if (this.commandName.equals("event")) {
            Event e = new Event(this.name, this.from, this.to);
            result = result + ui.addEvent(e) + "\n";
            tasks.add(e);
        }
        result = result + ui.showCurrentTaskNo(tasks) + "\n";
        return result;
    }
}
