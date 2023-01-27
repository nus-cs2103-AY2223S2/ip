package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.regex.Matcher;

public class Duke {
    private static UI ui = new UI();
    private static Path path;
    private static TaskList taskList;
    private static Storage storage;


    public Duke(String filePath) {
        path = Paths.get(filePath, "data", "Duke.txt");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            storage = new Storage(path);
            TaskList taskList = new TaskList(storage.load());
        } catch (InvalidPathException err) {
            ui.showLoadingError();
        } catch (IOException errIO){
            System.out.println("Unable to create File!");
            System.exit(-1);
        } catch (DukeException dukeErr){
            ui.displayError(dukeErr);
        }
    }

    public void run(){
        ui.welcome();
        try {
            Storage store = new Storage(path);
            while (true) {
                Parser parser = new Parser(ui.getInput());
                Command command = parser.parseArgs();
                command.execArgs(taskList);
                store.editFile(this.taskList.loadTaskList());
            }
        } catch (DukeException err){
            ui.displayError(err);
        }
    }

    public static void main(String[] args){
        Duke mainProgram = new Duke(System.getProperty("user.dir"));
        mainProgram.run();
    }

}

