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

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Connor() {
        ui = new Ui();
        this.ui.greet();
        storage = new Storage(getFile(), ui);
        this.tasks = new TaskList(this.storage, ui);
    }

    private String getCommand(String input) {
        if (input.indexOf(' ') == -1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, input.indexOf(' ')).toUpperCase();
        }
    }

    private String getTask(String input) throws InvalidTaskException {
        if (input.indexOf(' ') == -1) {
            throw new InvalidTaskException();
        }
        return input.substring(input.indexOf(' ') + 1).trim();
    }

    private File getFile() {
        String homeDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(homeDir, "data");
        Path dataPath = Paths.get(homeDir, "data", "Connor.txt");
        try {
            if (Files.exists(dataPath)) {
                this.ui.printMessage("Existing data detected, loading data.");
                return new File(String.valueOf(dataPath));
            } else {
                this.ui.printMessage("No existing data detected, creating new save file.");
                Files.createDirectories(directoryPath);
                return new File("data/Connor.txt");
            }
        } catch (IOException e) {
            this.ui.printMessage("ALERT! FAILED TO CREATE DIRECTORY!");
        }
        return new File("data/Connor.txt");
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver && sc.hasNextLine() ) {
            String input = sc.nextLine();
            String command = getCommand(input).trim();
            try {
                switch (Commands.valueOf(command)) {
                case HI:
                    this.ui.greetings("HI");
                    break;
                    
                case BYE:
                    this.ui.greetings("BYE");
                    isOver = true;
                    break;

                case LIST:
                    this.tasks.getList();
                    break;

                case MARK:
                    this.tasks.markDone(Integer.parseInt(getTask(input)));
                    break;

                case UNMARK:
                    this.tasks.markUndone(Integer.parseInt(getTask(input)));
                    break;


                case TODO:
                case DEADLINE:
                case EVENT:
                    Task task = Parser.parseCommand(command, getTask(input));
                    this.tasks.addTask(task);
                    break;

                case DELETE:
                    this.tasks.deleteTask(getTask(input));
                    break;
                }
            } catch (IllegalArgumentException e) {
                this.ui.printMessage("INVALID COMMAND");
            } catch (InvalidTaskException e) {
                this.ui.printMessage(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Connor().run();
    }
}
