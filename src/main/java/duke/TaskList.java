package duke;

import duke.task.*;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;
    private final Storage storage = new Storage();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        storage.save(tasks);
    }

    public TaskList(String filename) throws IOException, ClassNotFoundException, ClassCastException {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load(filename);
        } catch (FileNotFoundException e) {
            storage.save(tasks, filename);
        }
        this.tasks = tasks;
    }

    public TaskList() throws IOException, ClassNotFoundException, ClassCastException {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            storage.save(tasks);
        }
        this.tasks = tasks;
    }

    public String[] stringify() {
        if (this.tasks.size() == 0) return new String[]{"No tasks found."};
        String[] outputs = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            outputs[i] = this.tasks.get(i).toString(i+1);
        }
        return outputs;
    }

    public String[] add(String[] argument) {
        Task newTask;
        switch (argument[0]) {
            case "/todo":
                newTask = new ToDo(argument[1]);
                break;
            case "/deadline":
                String[] deadlineArgs = Parser.parseArgs(argument[1], new String[]{" /by "});
                newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                break;
            case "/event":
                String[] eventArgs = Parser.parseArgs(argument[1], new String[]{" /from ", " /to "});
                newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type: " + argument[0]);
        }
        this.tasks.add(newTask);
        this.save();
        return new String[]{"added: " + newTask};
    }

    public String[] mark(String[] strings) {
        List<Integer> indexes = new ArrayList<>();
        Consumer<Integer> mark = ind -> {
            this.tasks.get(ind).mark();
            indexes.add(ind);
        };
        this.find(strings, mark);
        this.save();
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task task = this.tasks.get(indexes.get(0));
            return new String[]{(task.isDone ? "marked: " : "unmarked: ") + task.toString(indexes.get(0) + 1)};
        } else {
            List<String> outputs = indexes.stream()
                    .map(i -> "\t" + this.tasks.get(i).toString(i+1))
                    .collect(Collectors.toList());
            outputs.add(0, "marked:");
            return outputs.toArray(new String[0]);
        }
    }

    public String[] delete(String[] strings) {
        List<Integer> indexes = new ArrayList<>();
        Consumer<Integer> delete = ind -> {
            if (this.tasks.size() <= ind) {
                throw new IllegalArgumentException("Task not found: " + ind);
            }
            indexes.add(ind);
        };
        this.find(strings, delete);
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task rmTask = this.tasks.remove((int) indexes.get(0));
            this.save();
            return new String[]{"deleted: " + rmTask.toString(indexes.get(0) + 1)};
        } else {
            indexes.sort(Collections.reverseOrder());
            List<String> outputs = new ArrayList<>();
            for (int i : indexes) {
                outputs.add("\t" + this.tasks.remove(i).toString(i+1));
            }
            this.save();
            Collections.reverse(outputs);
            outputs.add(0, "deleted:");
            return outputs.toArray(new String[0]);
        }
    }

    private void find(String[] argument, Consumer<Integer> consumer) {
        try{
            String[] inds = argument[0].split("\\s");
            for (String s : inds) {
                int ind = Integer.parseInt(s) - 1;
                consumer.accept(ind);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

    public void save() {
        storage.save(this.tasks);
    }
}