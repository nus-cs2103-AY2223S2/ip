package connor;

import connor.parser.Parser;
import connor.storage.Storage;
import connor.task.TaskList;
import connor.task.Task;
import connor.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

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
        DELETE,
        DELETEALL
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    public Connor() {
        this.ui = new Ui();
        this.ui.greet();
        this.storage = new Storage(getFile());
        this.parser = new Parser();
        try {
            LinkedList<Task> memory = storage.initialize();
            this.tasks = new TaskList(memory);
        } catch (IOException e) {
            this.tasks = new TaskList();
        }
    }

    private File getFile() {
        String homeDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(homeDir, "data");
        Path dataPath = Paths.get(homeDir, "data", "connor.Connor.txt");
        try {
            if (Files.exists(dataPath)) {
                Ui.printMessage("Existing data detected, loading data.");
                return new File(String.valueOf(dataPath));
            } else {
                Ui.printMessage("No existing data detected, creating new save file.");
                Files.createDirectories(directoryPath);
                return new File("data/connor.Connor.txt");
            }
        } catch (IOException e) {
            Ui.printMessage("ALERT! FAILED TO CREATE DIRECTORY!");
        }
        return new File("data/connor.Connor.txt");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver && sc.hasNextLine() ) {
            String input = sc.nextLine().trim();
            isOver = this.parser.parse(input, this.tasks, this.ui);
            this.storage.updateFile(tasks.getList());
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Connor().run();
    }
}
