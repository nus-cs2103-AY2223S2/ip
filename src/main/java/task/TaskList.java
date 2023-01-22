package task;

import command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task List to manage tasks.
 */
public class TaskList {

    /** ArrayList of tasks */
    private final ArrayList<Task> tasks;

    /** File path to retrieve and save tasks */
    private final String filePath;

    /**
     * Constructs a new task list and populate it with tasks saved on disk.
     *
     * @param filePath File path to read saved tasks from.
     */
    public TaskList(String filePath) {
        tasks = new ArrayList<>();
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                parseTask(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Task list not found on disk, creating empty list");
        }
    }

    private void parseTask(String input) {
        try {
            execute(new Command(input));
        } catch (IllegalArgumentException e) {
            System.out.println("Task list on disk corrupted!" +
                    " Some tasks might be missing or incorrect.");
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
        }
        return task;
    }

    /**
     * Saves the task list to disk.
     *
     * @throws IOException when there is an exception when writing to file or directory.
     */
    public void save() throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < tasks.size(); i++) {
            fileWriter.write(tasks.get(i).getRecreateCommand(i));
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

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
