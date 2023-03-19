package smudge.main;

import smudge.command.Command;
import java.io.IOException;

/**
 * Main class for Smudge program
 */
public class Smudge {
    private final Ui UI;
    private Tasklist tasks;
    private Storage storage;
    private boolean shouldExit = false;

    /**
     * initialises Ui, storage and tasklist
     */
    public Smudge() {
        UI = new Ui();
        try {
            String FILEPATH = "data/duke.txt";
            storage = new Storage(FILEPATH);
            tasks = new Tasklist(storage.load());
        } catch (IOException ie) {
            UI.printException(ie);
            System.exit(0);
        } catch (SmudgeException de) {
            UI.printException(de);
            tasks = new Tasklist();
        }
    }

    /**
     * executes the command that is taken in as input from user
     *
     * @param input string input from user
     * @return response Smudge will provide as a string
     */
    public String getResponse(String input) {
        if (shouldExit) {
            return "";
        }
        try {
            Command command = Parser.parseCommand(input);
            shouldExit = command.isExit();
            return command.execute(tasks, UI, storage);
        } catch (SmudgeException | IOException e) {
            return UI.printException(e);
        }
    }

}
