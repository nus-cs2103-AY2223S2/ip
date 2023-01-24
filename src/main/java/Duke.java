import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final String SAVE_DATA_FILE_PATH = "saveData.txt";
    private static final String SAVE_DATA_DIR_PATH = "./data";

    private Ui ui;
    private Storage storage;

    private TaskList taskList;

    private CommandParser commandParser;

    public Duke(String saveDataDirPath, String saveDataFilePath) {
        ui = new Ui();
        storage = new Storage(saveDataDirPath, saveDataFilePath);
        taskList = new TaskList(storage.load());
        commandParser = new CommandParser();
    }

    public void run() {
        ui.printStartup();

        Scanner scanner = new Scanner(System.in);
        while (!commandParser.hasUserQuit()) {
            String input = scanner.nextLine();

            // Try executing command from input
            try {
                commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage);
            } catch (DukeException exception) {
                ui.printHorizontal();
                ui.printText(exception.getMessage());
                ui.printHorizontal();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(SAVE_DATA_DIR_PATH, SAVE_DATA_FILE_PATH).run();
    }
}
