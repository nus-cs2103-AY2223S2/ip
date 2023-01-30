import core.DukeIO;
import core.Parser;
import core.TaskMaster;
import exceptions.DukeException;

/**
 * DUKE
 * CS2103 project
 * @author EDWIN LIM
 * @version 0.01
 */

public class Duke {
    private static DukeIO ioHandler;
    private static TaskMaster taskMaster;

    /**
     * Main method for the program
     * @param args UNUSED
     */
    public static void main(String[] args) {

        boolean shouldQuit = false;
        String userInput;

        initialize();
        greet();

        while (!shouldQuit) {
            userInput = ioHandler.readLn();
            if (!userInput.isEmpty()) {
                ioHandler.lb();
                try {
                    Parser input = new Parser(userInput);
                    ioHandler.println(input.parse(taskMaster));
                } catch (exceptions.Quit e) {
                    shouldQuit = true;
                } catch (DukeException de) {
                    ioHandler.println(de.getMessage());
                }
                ioHandler.lb();
                ioHandler.flush();
            }
        }
        goodbye();
    }

    /**
     * Initialize Duke by initializing needed classes.
     */
    public static void initialize() {
        ioHandler = new DukeIO();
        taskMaster = new TaskMaster();
        try {
            ioHandler.readSave(taskMaster);
        } catch (exceptions.invalid.File e) {
            taskMaster = new TaskMaster();
        } catch (DukeException e) {
            throw new RuntimeException(e); //Figure out who throws tis
        }
    }

    /**
     * Prints standard welcome message.
     */
    public static void greet() {
        String logo = "                __  __ _           \n"
                + "               / _|/ _| |          \n"
                + "__      ____ _| |_| |_| | ___  ___ \n"
                + "\\ \\ /\\ / / _` |  _|  _| |/ _ \\/ __|\n"
                + " \\ V  V / (_| | | | | | |  __/\\__ \\\n"
                + "  \\_/\\_/ \\__,_|_| |_| |_|\\___||___/\n";
        ioHandler.println("Hello from\n" + logo + "\n");
        ioHandler.println("Hello! I'm " + "Waffles");
        ioHandler.println("What can I do for you?");
        ioHandler.flush();
    }

    /**
     * Prints standard goodby message and closes DIO.
     */
    public static void goodbye() {
        ioHandler.writeSave(taskMaster);
        ioHandler.println("Bye. Hope to see you again soon!");
        ioHandler.flush();
        ioHandler.close();
    }
}

