import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    final static String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String WELCOME_MSG = "Greetings! JEDI GRANDMASTER YODA here\n" + "For you, What can I do?";
    final static String BANNER = "____________________________________________________________";
    final static String BYE_MSG = "Be Gone, You Must. May the Force be with You!";
    private final static String FILEPATH = "src/main/data/duke.txt";
    enum Command {
        LIST, MARK, UNMARK, DELETE
    }
    public static void main(String[] args) {
        Duke.displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String response = "";
        TaskList taskList = new TaskList();
        TaskInfoParser parser = new TaskInfoParser();
        try {
            taskList = Duke.loadData(taskList);
        } catch (IOException e) {
            System.out.println("Transmission error, I encountered! Jumping into hyperspace, it might be!");
        }
        while (true) {
            response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            }
            String[] commands = response.split(" ");
            System.out.println(BANNER);
            try {
                if (commands.length < 2 && isIndexRequiredCommand(commands[0])) {
                    throw new IncompleteCommandException(String.format("Hrrmmm. Not enough arguments, " +
                            "%s has. Hmm", commands[0]), null);
                }
                try {
                    Command command = Command.valueOf(commands[0].toUpperCase());
                    switch (command) {
                        case LIST:
                            taskList.listItems();
                            break;
                        case MARK:
                            taskList.markTask(commands[1]);
                            break;
                        case UNMARK:
                            taskList.unmarkTask(commands[1]);
                            break;
                        case DELETE:
                            taskList.deleteTask(commands[1]);
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    Task newTask = parser.obtainTask(response);
                    taskList.addTask(newTask);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(BANNER);
            }
        }
        Duke.saveData(FILEPATH, taskList);
        Duke.respond(BYE_MSG, true);
    }


    /**
     * Displays the welcome message when launched.
     */
    public static void displayWelcomeMessage() {
        System.out.println(String.format("%s\n%s\n%s", BANNER, WELCOME_MSG, BANNER));
    }

    /**
     * Responds to the command given by standard input with the appropriate formatting.
     * @param command the input that is retrieved from standard input
     * @param nextLine provides necessary indent should requested
     */
    public static void respond(String command, boolean nextLine) {
        String answer = "";
        if (nextLine) {
            answer += BANNER;
        }
        answer += "\n" + command + "\n" + BANNER;
        System.out.println(answer);
    }

    public static boolean isIndexRequiredCommand(String command) {
        return command.equals("mark")
                || command.equals("unmark")
                || command.equals("delete");
    }

    public static TaskList readFromFile(String path) throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);
        while(fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] commandArray = line.trim().split(" ");
            //System.out.println(Arrays.deepToString(commandArray));
            Task task = TaskInfoParser.obtainTask(commandArray);
            taskList.addTaskSilent(task);
        }
        fileScanner.close();
        return taskList;
    }

    public static TaskList loadData(TaskList taskList) throws IOException {
        try {
            taskList = Duke.readFromFile(FILEPATH);
            System.out.println("Hrmm Hrmm, some past tasks I see!!\n'list' command to see more, you must enter");
            System.out.println(BANNER);
            return taskList;
        } catch (FileNotFoundException e) {
            Path path = Paths.get("src/main/data");
            Files.createDirectories(path);
            File newTaskFile = new File(FILEPATH);
            newTaskFile.createNewFile();
            System.out.println("A new file created, I have!");
        }
        return new TaskList();
    }

    public static void saveData(String filepath, TaskList taskList) {
        DukeFileWriter.writeToFile(filepath, taskList);
    }

}