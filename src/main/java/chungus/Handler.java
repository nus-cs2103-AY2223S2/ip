package chungus;

import java.time.LocalDateTime;

@FunctionalInterface
interface Handler {
    boolean handle(TaskList tasks, Ui ui, Storage storage);

    static void reportNewTask(Task task, Ui ui, TaskList tasks) {
        ui.info("Okay, I've added this task:");
        ui.info("  %s", task);
        ui.info("Now you have %d %s.", tasks.count(), tasks.count() == 1 ? "task" : "tasks");
    }

    static void reportDeletedTask(Task task, Ui ui, TaskList tasks) {
        ui.info("Okay, I've deleted this task:");
        ui.info("  %s", task);
        ui.info("Now you have %d %s.", tasks.count(), tasks.count() == 1 ? "task" : "tasks");
    }
}

class Handlers {
    public static Handler bye() {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            ui.info("Bye!");
            return true;
        };
    }

    public static Handler list() {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            ui.info("Here are the tasks in your list:");
            tasks.forEach((task, idx) -> {
                ui.info("  %d.%s", idx + 1, task);
            });
            return false;
        };
    }

    public static Handler todo(String desc) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            Todo task = new Todo(desc);
            tasks.add(task);
            Handler.reportNewTask(task, ui, tasks);

            return false;
        };
    }

    public static Handler deadline(String desc, LocalDateTime deadline) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            Deadline task = new Deadline(desc, deadline);
            tasks.add(task);
            Handler.reportNewTask(task, ui, tasks);

            return false;
        };
    }

    public static Handler event(String desc, LocalDateTime from, LocalDateTime to) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            Event task = new Event(desc, from, to);
            tasks.add(task);
            Handler.reportNewTask(task, ui, tasks);

            return false;
        };
    }

    public static Handler mark(int idx) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            tasks.setDone(idx);

            ui.info("Okay, I've marked this task as completed:");
            ui.info("  %s", tasks.get(idx));

            return false;
        };
    }

    public static Handler unmark(int idx) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            tasks.setNotDone(idx);

            ui.info("Okay, I've marked this task as incomplete:");
            ui.info("  %s", tasks.get(idx));

            return false;
        };
    }

    public static Handler delete(int idx) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            Task task = tasks.remove(idx);
            Handler.reportDeletedTask(task, ui, tasks);

            return false;
        };
    }

    public static Handler unknown(String cmd) {
        return (TaskList tasks, Ui ui, Storage storage) -> {
            throw new ChungusException(String.format("Unknown command \"%s\"", cmd));
        };
    }
}
