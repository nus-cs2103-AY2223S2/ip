package task;

import command.Command;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    public TaskList(String filePath) {
        tasks = new ArrayList<>();
    }

    public Task execute(Command command) {
        Task task = null;
        switch (command.getName()) {
        case TODO:
            task = new Todo(
                    command.getArgumentValue(Command.Argument.TODO));
            tasks.add(task);
            break;
        case DEADLINE:
            task = new Deadline(
                    command.getArgumentValue(Command.Argument.DEADLINE),
                    command.getArgumentValue(Command.Argument.BY));
            tasks.add(task);
            break;
        case EVENT:
            task = new Event(
                    command.getArgumentValue(Command.Argument.EVENT),
                    command.getArgumentValue(Command.Argument.FROM),
                    command.getArgumentValue(Command.Argument.TO));
            tasks.add(task);
            break;
        case MARK:
            task = tasks.get(Integer.parseInt(command.getArgumentValue(
                    Command.Argument.MARK)));
            task.toggleDone();
            break;
        case DELETE:
            task = tasks.get(Integer.parseInt(
                    command.getArgumentValue(Command.Argument.DELETE)));
            tasks.remove(task);
            break;
        }
        return task;
    }

    public void save() {

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            result.append(index);
            result.append(". ");
            result.append(task);
            result.append("\n");
            index += 1;
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
