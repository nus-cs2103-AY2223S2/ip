package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;

/**
 * Task List to manage tasks.
 */
public class TaskList {

    /** ArrayList of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Loads task list with tasks from a scanner.
     *
     * @param scanner Scanner to read saved tasks from.
     */
    public void load(Scanner scanner) throws IOException {
        while (scanner.hasNextLine()) {
            parseTask(scanner.nextLine());
        }
    }

    /**
     * Parses a string read from disk to reconstruct tasks.
     *
     * @param input Input string.
     */
    private void parseTask(String input) throws IOException {
        try {
            execute(Parser.parse(input));
        } catch (IllegalArgumentException e) {
            throw new IOException("Task list on disk corrupted!"
                    + " Some tasks might be missing or incorrect.");
        }
    }

    /**
     * Executes a given command.
     *
     * @param command Command to be executed.
     * @return The task involved in the command.
     */
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
        default:
            assert false : "Unhandled command: " + command;
        }
        return task;
    }

    /**
     * Saves the task list to disk.
     *
     * @throws IOException when there is an exception when writing to file or directory.
     */
    public void save(FileWriter fileWriter) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            fileWriter.write(tasks.get(i).getRecreateCommand(i));
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    /**
     * Finds tasks that match a keyword.
     *
     * @param keyword Keyword to match.
     * @return String representation of list of matched tasks.
     */
    public String findAll(String keyword) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.contains(keyword)) {
                result.append(i);
                result.append(". ");
                result.append(tasks.get(i));
                if (i < tasks.size() - 1) {
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }

    /**
     * Returns the string representation of the task list.
     * Contains string representation of every task.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i);
            result.append(". ");
            result.append(tasks.get(i));
            if (i < tasks.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
