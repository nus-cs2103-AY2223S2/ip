package jarvis;

import jarvis.exception.CommandParseException;
import jarvis.exception.MissingParameterException;
import jarvis.task.Task;
import jarvis.task.TaskFilter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     * @param task Task to add.
     * @return List of response lines.
     */
    public List<String> addTask(Task task) {
        this.tasks.add(task);
        return List.of(
                String.format("Got it! I've added task %d to the list.", this.tasks.size()),
                "\t" + task
        );
    }

    public List<String> deleteTask(Command command) throws MissingParameterException {
        int index = -1;
        try {
            index = Integer.parseInt(command.getBody());
        } catch (NumberFormatException ignored) {}

        return this.deleteTask(index);
    }

    public List<String> deleteTask(int index) throws MissingParameterException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new MissingParameterException(
                    "Invalid index",
                    String.format("Please provide an index from %d to %d.", 1, this.tasks.size())
            );
        }
        Task task = this.tasks.remove(index - 1);
        return List.of(String.format("Got it, I've removed task %d.", index), "\t" + task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as done or undone, based on the command.
     * @param command Mark/unmark command.
     * @return List of response lines.
     */
    public List<String> setTaskDone(Command command) throws CommandParseException, MissingParameterException {
        int index = -1;
        try {
            index = Integer.parseInt(command.getBody());
        } catch (NumberFormatException ignored) {}

        if (command.hasAction(Command.Action.MARK_DONE)) {
            return this.setTaskDone(index, true);
        } else if (command.hasAction(Command.Action.MARK_UNDONE)) {
            return this.setTaskDone(index, false);
        } else {
            throw new CommandParseException("Provided command is not a MARK/UNMARK command");
        }
    }

    /**
     * Marks a task as done or undone as given.
     * @param index 1-based index of the task.
     * @param isDone Whether the task is marked as done.
     * @return List of response lines.
     */
    public List<String> setTaskDone(int index, boolean isDone) throws MissingParameterException {
        if (this.tasks.isEmpty()) {
            return List.of("There are no tasks to mark, please add a task first.");
        }

        if (index <= 0 || index > this.tasks.size()) {
            throw new MissingParameterException(
                    "Unable to parse index",
                    String.format("Please provide an index from %d to %d.", 1, this.tasks.size())
            );
        }

        Task task = this.tasks.get(index - 1);
        task.setDone(isDone);
        return List.of(
                String.format("Got it! I've marked task %d as %s.", index, isDone ? "done" : "undone"),
                "\t" + task
        );
    }

    /**
     * Returns a list of response lines to display the list.
     * @return List of response lines.
     */
    public List<String> getTasksForPrint() {
        return this.getTasksForPrint(this.tasks);
    }

    public List<String> getTasksForPrint(TaskFilter filter) {
        List<Task> filteredTasks = this.tasks
                .stream()
                .filter(task -> task.satisfies(filter))
                .collect(Collectors.toList());
        return getTasksForPrint(filteredTasks);
    }

    private List<String> getTasksForPrint(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return List.of("No tasks, you're good for the day!");
        }

        List<String> res = new LinkedList<>();
        res.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            res.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        return res;
    }
}
