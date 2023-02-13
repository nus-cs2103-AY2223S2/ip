package jarvis;

import java.util.Scanner;

import jarvis.command.Command;
import jarvis.exception.command.InvalidActionException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Jarvis class to run the program.
 */
public class Jarvis {
    private static final String BOT_NAME = Jarvis.class.getSimpleName();

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    private boolean isExit;

    /**
     * Constructor for Jarvis.
     */
    public Jarvis() {
        this.storage = new Storage("tasks.txt", "data");
        this.ui = new Ui(BOT_NAME);
        this.taskList = new TaskList(storage.readTasks());

        this.isExit = false;
    }

    /**
     * Runs Jarvis.
     */
    public void run() {
        this.ui.printLogo();
        this.ui.printStandard(Ui.Response.INTRO);

        Scanner scanner = new Scanner(System.in);
        while (!isExit && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            this.handleUserInput(line);
        }
        scanner.close();
    }

    public boolean isExit() {
        return isExit;
    }

    public String getResponse(String input) {
        this.handleUserInput(input);
        return this.ui.dumpResponses();
    }

    public boolean resetErrorFlag() {
        return this.ui.setErrorFlag(false);
    }

    private void handleUserInput(String input) {
        if (input == null || input.isBlank()) {
            this.ui.printUserPrompt();
            return;
        }

        Command command;
        try {
            command = Parser.parse(input);
        } catch (InvalidActionException e) {
            this.ui.printStandard(Ui.Response.CONFUSED);
            return;
        }
        command.execute(this.ui, this.taskList, this.storage);
        isExit = command.isExit();
    }
}
