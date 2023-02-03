package chungus;

import java.time.LocalDateTime;

/**
 * A handler represents something which is used to handle a user command. Also
 * contains some static utility methods which handler implementations can use.
 */
@FunctionalInterface
interface Handler {
    /**
     * Executes logic to handle some command.
     * 
     * @param tasks   The list of tasks to work with.
     * @param ui      The Ui instance to use.
     * @param storage The storage instance to use.
     * @return Whether the program should exit.
     */
    public boolean handle(TaskList tasks, NonBlockingUi ui, Storage storage);

    /**
     * Reports a new task. Just a convenience method.
     * 
     * @param task  The new task.
     * @param ui    A Ui instance to use.
     * @param tasks The current full list of tasks.
     */
    public static void reportNewTask(Task task, NonBlockingUi ui, TaskList tasks) {
        ui.info("Okay, I've added this task:\n  %s\nNow you have %d task(s).", task, tasks.count());
    }

    /**
     * Reports a deleted task. Just a convenience method.
     * 
     * @param task  The new task.
     * @param ui    A Ui instance to use.
     * @param tasks The current full list of tasks.
     */
    public static void reportDeletedTask(Task task, NonBlockingUi ui, TaskList tasks) {
        ui.info("Okay, I've deleted this task:\n  %s\nNow you have %d task(s).", task, tasks.count());
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
    public static Handler getHandlerBye() {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
            ui.info("Bye!");
            return true;
        };
    }

    /**
     * Returns a handleer to list tasks.
     * 
     * @return A handler for listing tasks.
     */
    public static Handler getHandlerList() {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
            ui.info("Here are the tasks in your list:\n%s", tasks);
            // Handler.printTasksIndented(tasks, ui);
            return false;
        };
    }

    /**
     * Returns a handler to create a new Todo.
     * 
     * @param desc The description of the new todo.
     * @return A handler which creates a new Todo with the given description.
     */
    public static Handler getHandlerTodo(String desc) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
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
    public static Handler getHandlerDeadline(String desc, LocalDateTime deadline) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
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
    public static Handler getHandlerEvent(String desc, LocalDateTime from, LocalDateTime to) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
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
    public static Handler getHandlerMark(int idx) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
            tasks.setDone(idx);

            ui.info("Okay, I've marked this task as completed:\n  %s", tasks.get(idx));

            return false;
        };
    }

    /**
     * Returns a handler to mark a task as incomplete.
     * 
     * @param idx The current index of the task (0-based).
     * @return A handler to mark the specified task as incomplete.
     */
    public static Handler getHandlerUnmark(int idx) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
            tasks.setNotDone(idx);

            ui.info("Okay, I've marked this task as incomplete:\n  %s", tasks.get(idx));

            return false;
        };
    }

    /**
     * Returns a handler to delete a task.
     *
     * @param idx The current index of the task (0-based).
     * @return A handler to delete the specified task.
     */
    public static Handler getHandlerDelete(int idx) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
            Task task = tasks.remove(idx);
            Handler.reportDeletedTask(task, ui, tasks);

            return false;
        };
    }

    /**
     * Returns a handler for finding tasks by description.
     * 
     * @param searchTerm The term(s) to search for.
     * @return A handler for finding tasks.
     */
    public static Handler getHandlerFind(String searchTerm) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
            TaskList filtered = tasks.filter(task -> task.desc().contains(searchTerm));
            if (filtered.count() == 0) {
                ui.info("No task matching the term \"%s\" found.", searchTerm);
            } else {
                ui.info("Here are your task(s) containing the term \"%s\":\n%s", searchTerm, filtered);
            }
            return false;
        };
    }

    /**
     * Returns a handler for unknown commands.
     * 
     * @param cmd The raw unknown command.
     * @return A handler to respond to the unknown command.
     */
    public static Handler getHandlerUnknown(String cmd) {
        return (TaskList tasks, NonBlockingUi ui, Storage storage) -> {
            throw new ChungusException(String.format("Unknown command \"%s\"", cmd));
        };
    }
}
