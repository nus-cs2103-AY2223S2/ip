package roody;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javafx.application.Application;
import roody.gui.RoodyMain;

/**
 * Represents a CLI chatbot named Roody.
 */
public class Roody {
    /** Stores tasks */
    private ArrayList<Task> list;

    /** Manages loading/saving of task data */
    private Storage storage;

    /** Manages GUI */
    private Ui ui;

    /**
     * Creates a chatbot with specified filepath to task data.
     * @param filepath The filepath to task data.
     */
    public Roody(String filepath) {
        // Assumed no more than 100 tasks
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        list = storage.loadFile();
    }

    // toggles completion status of tasks
    private Task toggleTask(int index, boolean complete) throws RoodyException {
        if (index > list.size() - 1 || list.get(index) == null) {
            throw new RoodyException("Sorry, that task doesn't exist");
        }
        Task task = list.get(index);
        if (complete) {
            task.setDone();
        } else {
            task.setUnDone();
        }
        return task;
    }

    private ArrayList<Task> findTaskByKeyword(String keyword) throws RoodyException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : list) {
            // Splits by "|"
            String[] words = task.saveTask().split("\\|");
            // Further splits description by whitespace
            String[] desc = words[0].split("\\s");
            for (String word : desc) {
                // Searches for a match to keyword
                if (word.equals(keyword)) {
                    foundTasks.add(task);
                }
            }
        }
        return foundTasks;
    }

    private Task deleteTask(int index) throws RoodyException {
        if (index > list.size() - 1 || list.get(index) == null) {
            throw new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(index);
            list.remove(index);
            return task;
        }
    }

    private Task makeTodo(String[] commands) throws RoodyException {
        if (commands.length < 2) {
            throw new RoodyException("Tasks require a description");
        }
        return new Todo(commands[1]);
    }

    private Task makeDeadline(String[] commands) throws RoodyException {
        if (commands.length < 2) {
            throw new RoodyException("Tasks require a description");
        }
        try {
            return new Deadline(commands[1], LocalDate.parse(commands[2]));
        } catch (DateTimeParseException e) {
            throw new RoodyException("Accepted date format is yyyy-mm-dd.");
        }
    }

    private Task makeEvent(String[] commands) throws RoodyException {
        if (commands.length < 2) {
            throw new RoodyException("Tasks require a description");
        }
        try {
            return new Event(commands[1], LocalDate.parse(commands[2]), LocalDate.parse(commands[3]));
        } catch (DateTimeParseException e) {
            throw new RoodyException("Accepted date format is yyyy-mm-dd.");
        }
    }

    public String getResponse(String input) {
        Task task;
        String message = ui.showLine();
        try {
            String[] commands = Parser.parse(input);
            switch (commands[0]) {
            // special command, to greet user
            case "start":
                message += ui.greet();
                break;
            case "list":
                message += ui.printList(list);
                break;
            case "todo":
                task = makeTodo(commands);
                list.add(task);
                message += ui.showAddTask(task, list.size());
                break;
            case "deadline":
                task = makeDeadline(commands);
                list.add(task);
                message += ui.showAddTask(task, list.size());
                break;
            case "event":
                task = makeEvent(commands);
                list.add(task);
                message += ui.showAddTask(task, list.size());
                break;
            case "delete":
                if (commands.length != 2) {
                    throw new RoodyException("Please enter an index number"
                            + " - \"{command} {index}\"");
                }
                task = deleteTask(Integer.parseInt(commands[0]) - 1);
                message += ui.showDeleteTask(task, list.size());
                break;
            case "mark":
            case "unmark":
                if (commands.length != 2) {
                    throw new RoodyException("Please enter an index number"
                            + " - \"{command} {index}\"");
                }
                task = toggleTask(Integer.parseInt(commands[1]) - 1, commands[0].equals("mark"));
                message += ui.showMarkStatus(commands[0].equals("mark"), task);
                break;
            case "find":
                if (commands.length != 2) {
                    throw new RoodyException("Please enter a keyword to be searched"
                            + " - \"find {keyword}\"");
                }
                message += ui.showFoundTasks(findTaskByKeyword(commands[1]));
                break;
            case "bye":
                storage.saveFile(list);
                message += ui.bye();
                break;
            default:
                throw new RoodyException("I don't quite understand that.");
            }
        } catch (RoodyException e) {
            ui.showLine();
            message += e.getMessage() + '\n';
        } finally {
            message += ui.showLine();
        }
        return message;
    }
    /**
     * Runs Roody's main process.
     * @param args Args.
     */
    public static void main(String[] args) {
        Application.launch(RoodyMain.class, args);
    }
}
