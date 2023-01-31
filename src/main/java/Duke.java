import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
public class Duke {
    private static final String SAVE_FILE_PATH = "./data/";
    private static final String SAVE_FILE_NAME = "state.data";
    private static final File saveFile = new File(SAVE_FILE_PATH + SAVE_FILE_NAME);
    private static final List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final String logo = "   _____  _  _                      \n" +
                "  / ____|| |(_)                     \n" +
                " | |     | | _  _ __   _ __   _   _ \n" +
                " | |     | || || '_ \\ | '_ \\ | | | |\n" +
                " | |____ | || || |_) || |_) || |_| |\n" +
                "  \\_____||_||_|| .__/ | .__/  \\__, |\n" +
                "               | |    | |      __/ |\n" +
                "               |_|    |_|     |___/ ";
        System.out.println("Hello from\n" + logo);
        prettyPrint("Hello! I'm Clippy, your lightweight personal assistant.");
        loadState();
        prettyPrint("What can I do for you today?");

        while (parseCommand(sc.nextLine().trim()));

        return;
    }

    /**
     * Takes in a command and attempts to perform it, if valid.
     * Accepted commands: delete, todo, deadline, event, mark, unmark, list, bye
     * @param   command a string containing the command entered by the user
     * @return          true if programme should continue accepting further commands, else false
     */
    private static boolean parseCommand(String command) {
        try {
            String[] args = command.split(" ");
            switch (args[0]) {
                case "bye":
                    systemPrint("Saving state, please wait...");
                    saveState();
                    systemPrint("State successfully saved.");
                    prettyPrint("Hope I helped. Goodbye!");
                    return false;
                case "mark":
                    // todo: check if second argument is a valid number
                    prettyPrint("Great job! I've marked this task as done: ");
                    tasks.get(Integer.parseInt(args[1]) - 1).complete();
                    prettyPrint(tasks.get(Integer.parseInt(args[1]) - 1).toString());
                    return true;
                case "unmark":
                    // todo: check if second argument is a valid number
                    prettyPrint("Aww... I've marked this task as not done yet: ");
                    tasks.get(Integer.parseInt(args[1]) - 1).uncomplete();
                    prettyPrint(tasks.get(Integer.parseInt(args[1]) - 1).toString());
                    return true;
                case "todo":
                case "deadline":
                case "event":
                    if (args[0].equals("todo")) {
                        if (args.length < 2) {
                            throw new ClippyTodoEmptyDescriptionException();
                        }
                        tasks.add(new ToDo(String.join(" ", Arrays.copyOfRange(args, 1, args.length))));
                    } else if (args[0].equals("deadline")) {
                        int byIndex = command.indexOf("/by ");
                        if (byIndex == -1 || command.length() < byIndex + 4) {
                            throw new ClippyMissingDeadlineException();
                        }

                        // startIndex of command.substring() is 9 as "deadline " is 9 chars long
                        try {
                            LocalDate deadline = LocalDate.parse(
                                    command.substring(byIndex + 4, command.length()).trim());
                            tasks.add(new Deadline(
                                    command.substring(9, byIndex).trim(),
                                    deadline));
                        } catch (DateTimeParseException e) {
                            prettyPrint("Uh-oh, Clippy didn't quite understand the date provided.");
                            prettyPrint("Deadline not saved. " +
                                    "Try again with dates in the following format:");
                            prettyPrint("===> yyyy-mm-dd <====");
                            return true;
                        }
                    } else if (args[0].equals("event")) {
                        // todo: check if BOTH '/from' and '/to' exists
                        int fromIndex = command.indexOf("/from ");
                        int toIndex = command.indexOf("/to ");
                        if (fromIndex == -1 || toIndex == -1 || toIndex - fromIndex < 5 ||
                                command.length() - toIndex < 4) {
                            throw new ClippyInvalidEventException();
                        }

                        try {
                            // startIndex of command.substring() is 6 as "event " is 6 chars long
                            tasks.add(new Event(
                                    command.substring(6, fromIndex).trim(),
                                    LocalDate.parse(command.substring(fromIndex + 6, toIndex).trim()),
                                    LocalDate.parse(command.substring(toIndex + 4, command.length()).trim())));
                        } catch (DateTimeParseException e) {
                            prettyPrint("Uh-oh, Clippy didn't quite understand the date provided.");
                            prettyPrint("Deadline not saved. " +
                                    "Try again with dates in the following format:");
                            prettyPrint("===> yyyy-mm-dd <====");
                            return true;
                        }
                    }
                    prettyPrint("Got it! I've added this task:");
                    prettyPrint(tasks.get(tasks.size() - 1).toString());
                    prettyPrint(String.format("Now you have %d task%s in the list.",
                            tasks.size(), tasks.size() == 1 ? "" : "s"));
                    return true;
                case "list":
                    if (tasks.size() == 0) {
                        prettyPrint("No tasks found!");
                    }
                    for (int i = 1; i <= tasks.size(); i++) {
                        prettyPrint(String.format("%d. %s", i, tasks.get(i - 1)));
                    }
                    return true;
                case "delete":
                    // todo: check for valid list index
                    prettyPrint("Got it! I've removed this task:");
                    prettyPrint(tasks.remove(Integer.parseInt(args[1]) - 1).toString());
                    prettyPrint(String.format("Now you have %d task%s in the list.",
                            tasks.size(), tasks.size() == 1 ? "" : "s"));
                    return true;
                default:
                    throw new ClippyUnknownCommandException();
            }
        } catch (ClippyException e) {
            // print error, but continue accepting inputs
            System.out.println(e.toString());
            return true;
        } catch (Exception e) {
            // show error and exit
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Prints out the specified string with a prepended ">>> ".
     * @param output the output to be printed
     */
    private static void prettyPrint(String output) {
        System.out.println(">>> " + output);
    }

    private static void systemPrint(String output) {
        System.out.println("... " + output);
    }

    private static void loadState() {
        systemPrint("Loading saved data...");

        File dir = new File(SAVE_FILE_PATH);
        if (!dir.exists()) {
            systemPrint("data directory not found! Creating it now...");
            if (dir.mkdirs()) {
                systemPrint("Successfully created data directory!");
            }
        }

        if (!saveFile.exists()) {
            systemPrint("Save file not found! Creating it now...");
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                systemPrint("I/O failed: " + e.toString() + ". Data will not be saved!");
                return;
            } catch (SecurityException e) {
                systemPrint("Write access denied by security manager. Data will not be saved!");
                return;
            }
            systemPrint("Successfully created save file!");
        } else {
            try {
                Scanner saveFileScanner = new Scanner(saveFile);
                while (saveFileScanner.hasNext()) {
                    tasks.add(Task.parseCsvString(saveFileScanner.nextLine()));
                }
                systemPrint("Save file loaded successfully!");
            } catch (FileNotFoundException e) {
                // should not happen since we checked for file existence beforehand
                System.out.println("Unexpected error occurred - save file not found. Data will not be saved!");
            }
        }
    }
    private static void saveState() {
        // will save entire `tasks` list for now, will make it more specific
        // in later iterations
        try {
            FileWriter saveFileWriter = new FileWriter(saveFile, false);
            for (int i = 0; i < tasks.size(); i++) {
                saveFileWriter.write(tasks.get(i).getCsvString());
                // add line separator if not last Task in `tasks`
                if (i < tasks.size() - 1) {
                    saveFileWriter.write(System.lineSeparator());
                }
            }
            saveFileWriter.close();
        } catch (IOException e) {
            prettyPrint("I/O failed: " + e.toString() + ". Data will not be saved!");
            return;
        }
    }
}
