import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Connor {

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

    private static File getFile(Path dataPath, Path directoryPath) {
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
        String homeDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(homeDir, "data");
        Path dataPath = Paths.get(homeDir, "data", "Connor.txt");
        File dataFile = getFile(dataPath, directoryPath);
        TaskList list = new TaskList(dataFile);
        list.initialize();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String command = getCommand(input);
            try {
                if (command.equals("HI")) {
                    Responses.greetings("HI");
                } else if (command.equals("BYE")) {
                    Responses.greetings("BYE");
                    sc.close();
                    break;
                } else if (command.equals("MARK")) {
                    list.markDone(Integer.parseInt(getTask(input)));
                } else if (command.equals("UNMARK")) {
                    list.markUndone(Integer.parseInt(getTask(input)));
                } else if (command.equals("LIST")) {
                    list.getList();
                } else if (command.equals("TODO") || command.equals("DEADLINE") || command.equals("EVENT")) {
                    Task task = TaskFactory.parseCommand(command, getTask(input));
                    list.addTask(task);
                } else if (command.equals("DELETE")) {
                    list.deleteTask(getTask(input));
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException | InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Responses.printMessage("Hello! I'm Connor, the android sent by Cyberlife.\n"
                + "        "
                + "Please type in your command below.");
        run();
    }
}
