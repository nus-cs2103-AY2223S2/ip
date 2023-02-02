import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    protected static final String DIV_OPEN = "____________________________________________________________\n";
    protected static final String DIV_CLOSE = "____________________________________________________________\n";
    protected static final String logo = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\n";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        System.out.println("Loading Duke...");
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks);
    }

    public void run() {
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        while (isRunning) {
            String input = ui.takeInput(sc);
            if (input.equals("bye")) {
                isRunning = false;
            } else {
                try {
                    parser.parse(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                storage.save(tasks.tasks);
            }
        }
        sc.close();
    }

    public void terminate() {
        storage.save(tasks.tasks);
        ui.sayBye();
    }

    public static void main(String[] args) {

        String home = System.getProperty("user.home");
        Duke duke = new Duke(home);
        System.out.println("Loading complete.\n");

        duke.ui.sayHello();

        duke.run();

        duke.terminate();

    }
}
