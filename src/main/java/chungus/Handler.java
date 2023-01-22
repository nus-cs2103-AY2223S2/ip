package chungus;

import java.time.LocalDateTime;

/**
 * A handler represents something which is used to handle a user command. Also
 * contains some static utility methods which handler implementations can use.
 */
@FunctionalInterface
interface Handler {
    boolean handle(TaskList tasks, Ui ui, Storage storage);

    /**
     * Reports a new task. Just a convenience method.
     * 
     * @param task  The new task.
     * @param ui    A Ui instance to use.
     * @param tasks The current full list of tasks.
     */
    static void reportNewTask(Task task, Ui ui, TaskList tasks) {
        ui.info("Okay, I've added this task:");
        ui.info("  %s", task);
        ui.info("Now you have %d %s.", tasks.count(), tasks.count() == 1 ? "task" : "tasks");
    }

    /**
     * Reports a deleted task. Just a convenience method.
     * 
     * @param task  The new task.
     * @param ui    A Ui instance to use.
     * @param tasks The current full list of tasks.
     */
    static void reportDeletedTask(Task task, Ui ui, TaskList tasks) {
        ui.info("Okay, I've deleted this task:");
        ui.info("  %s", task);
        ui.info("Now you have %d %s.", tasks.count(), tasks.count() == 1 ? "task" : "tasks");
    }
}

/**
 * A logical grouping of handlers used by Chungus.
 */
class Handlers {
    /**
     * Returns a handler for the exiting the app.
     * 
     * @return A handler for exiting the app.
     */
    public static Handler bye() {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            ui.info("Bye!");
            return true;
        };
    }

    /**
     * Returns a handleer to list tasks.
     * 
     * @return A handler for listing tasks.
     */
    public static Handler list() {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            ui.info("Here are the tasks in your list:");
            tasks.forEach((task, idx) -> {
                ui.info("  %d.%s", idx + 1, task);
            });
            return false;
        };
    }

    /**
     * Returns a handler to create a new Todo.
     * 
     * @param desc The description of the new todo.
     * @return A handler which creates a new Todo with the given description.
     */
    public static Handler todo(String desc) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            Todo task = new Todo(desc);
            tasks.add(task);
            Handler.reportNewTask(task, ui, tasks);

            return false;
        };
    }

    /**
     * Returns a handler to create a new task with deadline.
     * 
     * @param desc     Description for the task.
     * @param deadline Deadline for the new task.
     * @return A handler which creates a new task with the given description and
     *         deadline.
     */
    public static Handler deadline(String desc, LocalDateTime deadline) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            Deadline task = new Deadline(desc, deadline);
            tasks.add(task);
            Handler.reportNewTask(task, ui, tasks);

            return false;
        };
    }

    /**
     * Returns a handler to create a new event.
     * 
     * @param desc Description for the event.
     * @param from When the event starts.
     * @param to   When the event ends.
     * @return A handler which creates a new event with the given description and
     *         dates.
     */
    public static Handler event(String desc, LocalDateTime from, LocalDateTime to) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            Event task = new Event(desc, from, to);
            tasks.add(task);
            Handler.reportNewTask(task, ui, tasks);

            return false;
        };
    }

    /**
     * Returns a handler to mark a task as complete.
     * 
     * @param idx The current index of the task (0-based).
     * @return A handler to mark the specified task as complete.
     */
    public static Handler mark(int idx) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            tasks.setDone(idx);

            ui.info("Okay, I've marked this task as completed:");
            ui.info("  %s", tasks.get(idx));

            return false;
        };
    }

    /**
     * Returns a handler to mark a task as incomplete.
     * 
     * @param idx The current index of the task (0-based).
     * @return A handler to mark the specified task as incomplete.
     */
    public static Handler unmark(int idx) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            tasks.setNotDone(idx);

            ui.info("Okay, I've marked this task as incomplete:");
            ui.info("  %s", tasks.get(idx));

            return false;
        };
    }

    /**
     * Returns a handler to delete a task.
     *
     * @param idx The current index of the task (0-based).
     * @return A handler to delete the specified task.
     */
    public static Handler delete(int idx) {

        return (TaskList tasks, Ui ui, Storage storage) -> {
            Task task = tasks.remove(idx);
            Handler.reportDeletedTask(task, ui, tasks);

            return false;
        };
    }

    /**
     * Returns a handler for unknown commands.
     * 
     * @param cmd The raw unknown command.
     * @return A handler to respond to the unknown command.
     */
    public static Handler unknown(String cmd) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            throw new ChungusException(String.format("Unknown command \"%s\"", cmd));
        };
    }
}
