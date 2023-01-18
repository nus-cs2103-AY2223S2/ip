/**
 * DUKE
 * CS2103 project
 * @author EDWIN LIM
 * @version 0.01
 */

public class Duke {
    private static DukeIO dio;
    private static TaskMaster tm;

    public enum SWITCHTYPERELATED {
        TODO, EVENT, DEADLINE
    }

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
            userInput = dio.readln();
            if (!userInput.isEmpty()) {
                dio.lb();
                String[] userInputSplit = userInput.split(" ",2);
                try {
                    switch (userInput.split(" ",2)[0]) {
                        case "list":
                            tm.list();
                            break;
                        case "bye":
                            QUIT = true;
                            break;
                        case "mark":
                            tm.markComplete(dio.extractIndexParams(userInput), true);
                            break;
                        case "unmark":
                            tm.markComplete(dio.extractIndexParams(userInput), false);
                            break;
                        case "todo":
                            args = dio.extractTaskParams(userInput, SWITCHTYPERELATED.TODO);
                            tm.addToDo(args[0]);
                            break;
                        case "event":
                            args = dio.extractTaskParams(userInput, SWITCHTYPERELATED.EVENT);
                            tm.addEvent(args[0], args[1], args[2]);
                            break;
                        case "deadline":
                            args = dio.extractTaskParams(userInput, SWITCHTYPERELATED.DEADLINE);
                            tm.addDeadLine(args[0], args[1]);
                            break;
                        case "delete":
                            tm.delete(dio.extractIndexParams(userInput));
                            break;
                        case "?":
                            throw new DukeException.Unimplemented();
                        default:
                          throw new DukeException.Invalid.Command();
                    }
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
        tm = new TaskMaster(dio);
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

