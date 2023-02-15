package membot;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import membot.commands.Command;
import membot.model.Task;
import membot.storage.StorageManager;
import membot.utils.EmptyInputException;
import membot.utils.InvalidCommandException;
import membot.view.Printable;

/**
 * Main application class.
 */
public class Membot {
    private static final String FILE_NAME = "./data/tasks.txt";
    private static final String TEST_FILE_NAME = "./data/test_tasks.txt";
    private static final int EXIT_DELAY = 500;
    private final Printable ui;
    private StorageManager manager;

    /**
     * Generates an instance of Membot to run. Multiple instances can be
     * used if there are multiple types of views to render (e.g. CLI and GUI).
     *
     * @param ui The engine for displaying data to a user view.
     * @param isTest True if Membot instance is used for test purposes, false otherwise.
     */
    public Membot(Printable ui, boolean isTest) {
        this.ui = ui;

        try {
            String fileName = isTest ? TEST_FILE_NAME : FILE_NAME;
            this.manager = new StorageManager(fileName);
            Task.load(manager.loadFromFile());
            this.execute("help");
        } catch (IOException e) {
            ui.printlnError(e.toString());
            exit();
        }
    }

    /**
     * Executes the command that corresponds to the input string.
     *
     * @param input A <code>String</code> command input.
     */
    public void execute(String input) {
        String s = input.trim();
        Command command;

        try {
            command = Command.parse(s, ui, this);
            command.execute();
        } catch (EmptyInputException | InvalidCommandException e) {
            this.ui.printlnError("Sorry I do not understand what to do!");
        }
    }

    /**
     * Cleans up Membot and exits.
     */
    public void exit() {
        if (this.manager != null) {
            try {
                Task.save(this.manager);
            } catch (IOException e) {
                this.ui.printlnError(e.toString());
            }
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, EXIT_DELAY);
    }
}
