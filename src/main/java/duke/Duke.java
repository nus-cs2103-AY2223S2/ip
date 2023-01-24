package duke;

import java.io.IOException;
import java.util.Scanner;

import commands.Command;
import exceptions.DukeException;
import files.Storage;
import parsers.CommandParser;
import tasks.TaskList;
import ui.Ui;

/**
 * The main application runner which runs the task list.
 */
public class Duke {

    private static final String BANNER = "____________________________________________________________";

    private static final String FILEPATH = "src/main/data/duke.txt";

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the entire program.
     */
    public void run() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        CommandParser commandParser = new CommandParser();
        TaskList taskList = new TaskList();
        ui.displayWelcomeMessage();

        try {
            taskList = Storage.loadData(taskList, FILEPATH);
        } catch (IOException e) {
            System.out.println("Transmission error, I encountered! Jumping into hyperspace, it might be!");
        }
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String response = ui.readCommand(scanner);
                Command command = commandParser.parse(response);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showLoadingErrorMessage(e);
            } finally {
                System.out.println(BANNER);
            }
        }
        storage.saveData(FILEPATH, taskList);
        System.out.println(BANNER);
    }
}
