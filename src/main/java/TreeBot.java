import exception.InvalidCommandException;
import exception.TaskFactoryException;
import exception.TreeBotException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeBot {
    private static final String EXIT_TOKEN = "bye";
    private TaskList taskList;
    private Ui ui = new Ui();
    private Storage storage;
    private Parser parser = new Parser(new TaskFactory());

    public TreeBot(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);

                if (c.equals(EXIT_TOKEN)) {
                    break;
                }
                c.execute(taskList, ui, storage);
            } catch (TreeBotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
