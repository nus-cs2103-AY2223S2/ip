package duke.util;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility used by Duke to manage tasks.
 *
 * @see Duke
 * @see Task
 * @see Storage
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage = new Storage();

    /**
     * Constructs a new TaskList with the given tasks.
     *
     * @param tasks the tasks to be managed
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        storage.save(tasks);
    }

    /**
     * Constructs a new TaskList with no tasks and stores it in the given file.
     *
     * @param fileName the name of the file to be used to store tasks.
     * @throws IOException            if the file cannot be found or created.
     * @throws ClassNotFoundException if the file cannot be deserialized.
     */
    public TaskList(String fileName) throws IOException, ClassNotFoundException, ClassCastException {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load(fileName);
        } catch (FileNotFoundException e) {
            storage.save(tasks, fileName);
        }
        this.tasks = tasks;
    }

    /**
     * Constructs a new TaskList with no tasks and stores it in the default location.
     *
     * @throws IOException            if the file cannot be found or created.
     * @throws ClassNotFoundException if the file cannot be deserialized.
     * @see Storage
     */
    public TaskList() throws IOException, ClassNotFoundException, ClassCastException {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            storage.save(tasks);
        }
        this.tasks = tasks;
    }

    /**
     * Returns a string representation of the tasks in the list.
     *
     * @return  a string representation of the tasks in the list.
     * @see     Ui#print(String)
     */
    public Queue<String> stringify() {
        if (this.tasks.size() == 0) {
            return new LinkedList<>(Collections.singletonList("No tasks found."));
        }
        Queue<String> header = new LinkedList<>(Collections.singletonList("tasks: "));
        Queue<String> outputs = IntStream.range(0, this.tasks.size())
                .mapToObj(i -> "\t" + addOrdinal(i, this.tasks.get(i)))
                .collect(Collectors.toCollection(LinkedList::new));
        header.addAll(outputs);
        return header;
    }

    /**
     * Adds a new {@link Task} to the list.
     *
     * @param  task the task to be added.
     * @return a string representation of the task that was added.
     */
    public String add(Task task) {
        this.tasks.add(task);
        this.save();
        return "added: " + task;
    }

    public String addTodo(String description) {
        Task newTask = new ToDo (Objects.requireNonNull(description));
        return this.add(newTask);
    }

    public String addDeadline(String description, String by) {
        Task newTask = new Deadline(Objects.requireNonNull(description),
                Objects.requireNonNull(by));
        return this.add(newTask);
    }

    public String addEvent(String description, String from, String to) {
        Task newTask = new Event(Objects.requireNonNull(description),
                Objects.requireNonNull(from),
                Objects.requireNonNull(to));
        return this.add(newTask);
    }

    /**
     * Marks (toggle) the task(s) at the given index(es).
     *
     * @param indStr the index(es) of the task(s) to be marked as done.
     * @param isDone the new status of the task(s). Null to toggle.
     * @return a string representation of the task(s) that were (un)marked.
     */
    public Queue<String> toggleMark(Queue<String> indStr, Boolean isDone) {
        if (indStr == null || indStr.size() == 0) {
            return new LinkedList<>();
        }
        List<Integer> indexes = new ArrayList<>();
        IntConsumer mark = ind -> {
            if (isDone != null) {
                this.tasks.get(ind).mark(isDone);
            } else {
                this.tasks.get(ind).mark();
            }
            indexes.add(ind);
        };
        this.consume(indStr, mark);
        this.save();
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task task = this.tasks.get(indexes.get(0));
            return new LinkedList<>(Collections.singletonList(
                    (task.isDone() ? "marked: " : "unmarked: ")
                            + addOrdinal(indexes.get(0), task)
            ));
        } else {
            Queue<String> header = new LinkedList<>(Collections.singletonList("marked: "));
            Queue<String> outputs = indexes.stream()
                    .map(i -> "\t" + addOrdinal(i, this.tasks.get(i)))
                    .collect(Collectors.toCollection(LinkedList::new));
            header.addAll(outputs);
            return header;
        }
    }

    /**
     * Deletes the task(s) at the given index(es).
     *
     * @param indStr the index(es) of the task(s) to be deleted.
     * @return a string representation of the task(s) that were deleted.
     */
    public Queue<String> delete(Queue<String> indStr) {
        List<Integer> indexes = new ArrayList<>();
        IntConsumer delete = ind -> {
            if (this.tasks.size() <= ind) {
                throw new IllegalArgumentException("Task not found: " + ind);
            }
            indexes.add(ind);
        };
        this.consume(indStr, delete);
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task rmTask = this.tasks.remove((int) indexes.get(0));
            this.save();
            return new LinkedList<>(Collections.singletonList(
                    "deleted: " + addOrdinal(indexes.get(0), rmTask)
            ));
        } else {
            indexes.sort(Collections.reverseOrder());
            List<String> outputs = new ArrayList<>();
            for (int i : indexes) {
                outputs.add("\t" + addOrdinal(i, this.tasks.remove(i)));
            }
            this.save();
            Collections.reverse(outputs);
            outputs.add(0, "deleted:");
            return new LinkedList<>(outputs);
        }
    }

    private void consume(Queue<String> arguments, IntConsumer consumer) {
        try {
            arguments.stream().mapToInt(Integer::parseInt).map(i -> --i).forEach(consumer);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

    /**
     * Performs a search for partial matches on task descriptions.
     *
     * @param argument the search term.
     * @return a string representation of the tasks that matched the search term.
     */
    public Queue<String> find(String argument) {
        String keyword = argument.toLowerCase();
        List<Integer> matchIndexes = IntStream.range(0, this.tasks.size())
                .parallel()
                .filter(i -> this.tasks.get(i).getDesc().toLowerCase().contains(keyword))
                .boxed().collect(Collectors.toList());
        if (matchIndexes.size() < 1) {
            return new LinkedList<>(Collections.singletonList("No matches found."));
        } else {
            String countStr = String.format("%d match%s found:", matchIndexes.size(), matchIndexes.size() > 1 ? "es" : "");
            Queue<String> header = new LinkedList<>(Collections.singletonList(countStr));
            Queue<String> outputs = matchIndexes.stream().map(i -> "\t" + addOrdinal(i, this.tasks.get(i))).collect(Collectors.toCollection(LinkedList::new));
            header.addAll(outputs);
            return header;
        }
    }

    private void save() {
        storage.save(this.tasks);
    }

    /**
     * Returns a string representation of the task with the given index.
     * Used for printing in ordered lists.
     *
     * @param index the index of the task.
     * @param task  the task.
     * @return a string representation of the task with the given index.
     */
    public String addOrdinal(int index, Task task) {
        assert index >= 0 && task != null;
        return (index + 1) + ". " + task;
    }
}