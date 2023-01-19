import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Connor {

    public enum Commands {
        HI,
        BYE,
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    private static String getCommand(String input) {
        if (input.indexOf(' ') == -1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, input.indexOf(' ')).toUpperCase();
        }
    }

    private static String getTask(String input) throws InvalidTaskException {
        if (input.indexOf(' ') == -1) {
            throw new InvalidTaskException();
        }
        return input.substring(input.indexOf(' ') + 1).trim();
    }

    private static File getFile() {
        String homeDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(homeDir, "data");
        Path dataPath = Paths.get(homeDir, "data", "Connor.txt");
        try {
            if (Files.exists(dataPath)) {
                Responses.printMessage("Existing data detected, loading data.");
                return new File(String.valueOf(dataPath));
            } else {
                Responses.printMessage("No existing data detected, creating new save file.");
                Files.createDirectories(directoryPath);
                return new File("data/Connor.txt");
            }
        } catch (IOException e) {
            Responses.printMessage("ALERT! FAILED TO CREATE DIRECTORY!");
        }
        return null;
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        File dataFile = getFile();
        TaskList list = new TaskList(dataFile);
        list.initialize();
        boolean sessionOver = false;
        while (!sessionOver && sc.hasNextLine() ) {
            String input = sc.nextLine();
            String command = getCommand(input);
            try {
                switch (Commands.valueOf(command)) {
                    case HI:
                        Responses.greetings("HI");
                        break;

                    case BYE:
                        Responses.greetings("BYE");
                        sessionOver = true;
                        break;

                    case MARK:
                        list.markDone(Integer.parseInt(getTask(input)));
                        break;

                    case UNMARK:
                        list.markUndone(Integer.parseInt(getTask(input)));
                        break;

                    case LIST:
                        list.getList();
                        break;

                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Task task = TaskFactory.parseCommand(command, getTask(input));
                        list.addTask(task);
                        break;

                    case DELETE:
                        list.deleteTask(getTask(input));
                        break;
                }
            } catch (IllegalArgumentException | InvalidTaskException e) {
                System.out.println("        INVALID INPUT");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Responses.printMessage("Hello! I'm Connor, the android sent by Cyberlife.\n"
                + "        Please type in your command below.");
        run();
    }
}
