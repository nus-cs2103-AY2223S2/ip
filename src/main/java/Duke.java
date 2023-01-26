import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileWriter;

import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Duke {

    private static final String FILE_PATH = "./data/duke.txt";
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";

    private static final List<Task> taskList = new ArrayList<>();
    private static final String BOT_NAME = "Tyrone";

    public static void greet() {
        Duke.say("Sup, my name is " + Duke.BOT_NAME);
    }

    public static void processInput(String input) {
        try {
            // Split strings into 2, first part is the operation, 2nd part is the description
            String[] command = input.split(" ", 2);
            Operation op = Operation.valueOf(command[0].toUpperCase());

            switch (op) {
                case MARK:
                    Duke.markTask(command);
                    break;
                case UNMARK:
                    Duke.unmarkTask(command);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Duke.addTask(command, op);
                    break;
                case LIST:
                    Duke.listTasks();
                    break;
                case DELETE:
                    Duke.deleteTask(command);
            }
        } catch (DukeException e) {
            Duke.say(e.toString());
        } catch (IllegalArgumentException e) {
            Duke.say("What are you talkin about man?");
        }
    }

    public static void interact() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            // If input is "bye", terminate loop
            if (input.equals("bye")) {
                break;
            }

            // Else, we process the input
            Duke.processInput(input);
        }

        sc.close();
    }

    public static void say(String s) {
        String opener = "◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥";
        String ender = "◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢";
        System.out.println("\n" + opener + "\n" + s + "\n" + ender + "\n");
    }

    public static void listTasks() throws DukeException {

        if (taskList.size() == 0) {
            throw new DukeException("No tasks available right now. :>");
        }

        StringBuilder stringList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            stringList.append((i + 1) + ". " + task.toString());

            if (i < taskList.size() - 1) {
                stringList.append("\n");
            }
        }

        Duke.say(stringList.toString());
    }

    public static boolean isInvalidTask(String[] command) {
        String idx = command[1];

        // False if idx is not an integer or idx is out of bounds
        return (!idx.matches("-?\\d+")
                || Integer.parseInt(idx) < 1
                || Integer.parseInt(idx) > taskList.size());
    }

    public static boolean isNoTaskGiven(String[] command) {
        return command.length < 2;
    }

    public static void checkCommandValidity(String[] command) throws DukeException {
        if (isNoTaskGiven(command)) {
            throw new DukeException("You gave me no task.");
        }

        if (isInvalidTask(command)) {
            throw new DukeException("Task given is invalid / unavailable.");
        }
    }

    public static void markTask(String[] command) throws DukeException {
        Duke.checkCommandValidity(command);
        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        t.markAsDone();
        Duke.say("Task masked as done: " + t);
    }

    public static void unmarkTask(String[] command) throws DukeException {
        Duke.checkCommandValidity(command);
        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        t.markAsUndone();
        Duke.say("Task masked as undone: " + t);
    }

    /**
     * Adds a task to the task list, taking into account the type of task given.
     *
     * @param command The given command from user.
     * @param op The type of task given.
     * @throws DukeException
     */
    public static void addTask(String[] command, Operation op) throws DukeException {

        if (command.length < 2) {
            throw new DukeException("No task description given.");
        }

        String description = command[1];

        StringBuilder text = new StringBuilder();

        switch (op) {
            case TODO:
                Task todo = new ToDo(description);
                taskList.add(todo);
                text.append("New todo task added: " + todo);
                break;

            case DEADLINE:
                String[] deadlineDescription = description.split("/by", 2);
                Task deadline = new Deadline(deadlineDescription[0], deadlineDescription[1]);
                taskList.add(deadline);
                text.append("New deadline added: " + deadline);
                break;

            case EVENT:
                String[] eventDescription = description.split("/from", 2);

                // Parse the string to get to and from dates of the event
                String[] fromAndTo = eventDescription[1].split("/to", 2);
                String from = fromAndTo[0];
                String to = fromAndTo[1];

                Task event = new Event(eventDescription[0], from, to);
                taskList.add(event);
                text.append("New event added: " + event);
                break;
        }

        text.append("\nYou have " + taskList.size() + " task(s) in the list.");
        Duke.say(text.toString());
    }

    /**
     * Deletes the given task.
     *
     * @param command The input from the user.
     * @throws DukeException If task is invalid or task is not given.
     */
    public static void deleteTask(String[] command) throws DukeException {

        Duke.checkCommandValidity(command);

        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        taskList.remove(t);

        // Print response text to user
        StringBuilder text = new StringBuilder();
        text.append("Task deleted: " + t + "\n");
        text.append("You have " + taskList.size() + " task(s) remained.");
        Duke.say(text.toString());
    }

    /**
     * Prints shutdown message
     */
    public static void shutdown() {
        Duke.say("Aight imma head out");
    }

    /**
     * Adds a task from saved file to the task list.
     */
    private static void loadTaskFromFile(String task) {
        String[] command = task.split("\\|");
        String taskType = command[0];
        String description = command[2];

        Task t = new Task("placeholder");

        switch (taskType) {
        case "T":
            t = new ToDo(description);
            break;
        case "D":
            String by = command[3];
            t = new Deadline(description, by);
            break;
        case "E":
            String from = command[3];
            String to = command[4];
            t = new Event(description, from, to);
            break;
        }

        boolean isMarked = command[1].equals("1");
        if (isMarked) {
            t.markAsDone();
        }

        Duke.taskList.add(t);
    }

    /**
     * Gets the directories to the save file.
     *
     * @return String representing the directories to the save file.
     */
    public static String getDir() {
        String home = System.getProperty("user.dir");
        Path path = java.nio.file.Paths.get(home, Duke.FILE_DIRECTORY);
        return path.toString();
    }

    /**
     * Creates parent directories and the file if necessary. Then returns file.
     */
    private static File fileWithAssurance(String directory, String filename) {
        // Load parent directories
        File dir = new File(directory);

        // Create parent directories if necessary
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Load file
        File file = new File(directory + "/" + filename);

        // Create file if not exist
        try {
            if (file.createNewFile()) {
                Duke.say("New save file created.");
            } else {
                Duke.say("Save file detected. Loading...");
            }
        } catch (IOException e) {
            Duke.say("Something went wrong while creating a new save");
        }

        return file;
    }

    /**
     * Loads the save file at startup if it exists.
     * If directory does not exist, create a new directory.
     */
    private static void loadFile() {

        File file = fileWithAssurance(Duke.getDir(), FILE_NAME);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                Duke.loadTaskFromFile(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Duke.say("An error occurred while locating save file.");
        }
    }

    /**
     * Saves the task list to hard drive.
     */
    private static void saveFile() {
        Duke.say("Saving file...");
        try {
            FileWriter fw = new FileWriter(Duke.getDir() + "/" + FILE_NAME);

            for (int i = 0; i < Duke.taskList.size(); i++) {
                Task t = Duke.taskList.get(i);
                fw.write(t.toSavedString() + System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            Duke.say("Something went wrong with saving file");
        }
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.loadFile();
        Duke.interact();
        Duke.shutdown();
        Duke.saveFile();
    }
}
