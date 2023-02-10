package core;

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

//        greetCli();
        ioHandler.println(greet());
        initialize();
        ioHandler.flush();

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
        goodbyeCli();
    }

    /**
     * Initialize core.Duke by initializing needed classes.
     */
    public static String initialize() {
        ioHandler = new DukeIO();
        taskMaster = new TaskMaster();
        try {
            ioHandler.readSave(taskMaster);
            return taskMaster.getReminder();
        } catch (exceptions.invalid.File e) {
            taskMaster = new TaskMaster();
        } catch (DukeException e) {
            throw new RuntimeException(e); //Figure out who throws tis
        }
        return "";
    }

    /**
     * Prints standard welcome message.
     */
    public static void greetCli() {
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
     * Prints standard welcome message.
     * @return standard welcome message.
     */
    public static String greet() {
        StringBuilder ret = new StringBuilder();
        String logo = "                __  __ _           \n"
                + "               / _|/ _| |          \n"
                + "__      ____ _| |_| |_| | ___  ___ \n"
                + "\\ \\ /\\ / / _` |  _|  _| |/ _ \\/ __|\n"
                + " \\ V  V / (_| | | | | | |  __/\\__ \\\n"
                + "  \\_/\\_/ \\__,_|_| |_| |_|\\___||___/\n";
        ret.append("Hello from\n");
        ret.append(logo);
        ret.append("\n");
        ret.append("Hello! I'm Waffles\n");
        ret.append("What can I do for you?\n");

        return ret.toString();
    }

    /**
     * Prints standard goodbye message and closes DIO.
     */
    public static void goodbyeCli() {
        ioHandler.writeSave(taskMaster);
        ioHandler.println("Bye. Hope to see you again soon!");
        ioHandler.flush();
        ioHandler.close();
    }

    /**
     * Prints standard goodbye message and closes DIO.
     */
    public static void goodbye() {
        ioHandler.writeSave(taskMaster);
    }

    /**
     * Process user commands and returns appropriate message.
     * @param userInput String from the user
     * @return Message depending on how the command is interpreted.
     */
    public String getResponse(String userInput) {
        if (!userInput.isEmpty()) {
            try {
                Parser input = new Parser(userInput);
                return input.parse(taskMaster);
            } catch (exceptions.Quit e) {
                goodbye();
                System.exit(0);
            } catch (DukeException de) {
                return de.getMessage();
            }
        } else {
            try {
                throw new exceptions.invalid.Command();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return userInput;
    }
}

