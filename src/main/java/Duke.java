import core.DukeIO;
import core.Parser;
import exceptions.DukeException;
import core.TaskMaster;

/**
 * DUKE
 * CS2103 project
 * @author EDWIN LIM
 * @version 0.01
 */

public class Duke {
    private static DukeIO dio;
    private static TaskMaster tm;

    /**
     * Main method for the program
     * @param args UNUSED
     */
    public static void main(String[] args) {

        boolean QUIT = false;
        String userInput;

        initialize();
        greet();

        while(!QUIT) {
            userInput = dio.readLn();
            if (!userInput.isEmpty()) {
                dio.lb();
                try {
                    Parser a = new Parser(userInput);
                    dio.println(a.parse(tm));
                } catch (exceptions.Quit e) {
                    QUIT = true;
                } catch (DukeException de) {
                    dio.println(de.getMessage());
                }
                dio.lb();
                dio.flush();
            }
        }
        goodbye();
    }

    public static void initialize() {
        dio = new DukeIO();
        tm = new TaskMaster();
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
        dio.println("Hello from\n" + logo + "\n");
        dio.println("Hello! I'm " + "Waffles");
        dio.println("What can I do for you?");
        dio.flush();
    }

    /**
     * Prints standard goodby message and closes DIO.
     */
    public static void goodbye() {
        dio.println("Bye. Hope to see you again soon!");
        dio.flush();
        dio.close();
    }

}

