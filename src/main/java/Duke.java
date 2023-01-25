import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static String HOR_BAR = "✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";
    public static String[] commands = {"list", "mark", "unmark", "todo", "deadline", "event"};
    /* index                              0      1        2         3        4          5    */

    private static void greetingDialogue() {
        System.out.println(HOR_BAR);
        System.out.println("Hey there! I'm Berry the Bunny~ ૮ ˶ᵔ ᵕ ᵔ˶ ა\n"
                + "What are you looking to plan today?");
        System.out.println(HOR_BAR);
    }

    private static void byeDialogue() {
        System.out.println(HOR_BAR);
        System.out.println("Bye! I hope Berry was helpful to you ૮꒰˶• ༝ •˶꒱ა");
        System.out.println(HOR_BAR);
    }

    private static String printlnTab(String message) {
        return "    " + message;
    }

    private static boolean isValidTaskCommand(String input) throws DukeException {
        String com = input.split(" ")[0];

        if (com.equals(commands[3])) { // todo
            if (input.substring(4).isBlank()) { // empty description
                throw new EmptyDescriptionException(com);
            }
        } else if (com.equals(commands[4])) { //deadline
            if (!input.contains("/by ") || !input.contains("/by")) { // no by clause
                throw new MissingClauseException("by");
            } else if (input.split("/by")[0].substring(8).isBlank()) { // empty description
                throw new EmptyDescriptionException(com);
            } else if (input.endsWith("/by") || input.split("/by")[1].isBlank()) {
                throw new EmptyClauseException("by");
            }
        } else if (com.equals(commands[5])) { //event
            if (!input.contains("/from ") || !input.contains("/from")) {
                throw new MissingClauseException("from");
            } else if (input.split("/from")[0].substring(5).isBlank()) { // empty description
                throw new EmptyDescriptionException(com);
            } else if (!input.contains("/to ") || !input.contains("/to")) {
                throw new MissingClauseException("to");
            } else if (input.endsWith("/to") || input.split("/to")[1].isBlank()) {
                throw new EmptyClauseException("to");
            } else if (input.endsWith("/from") || input.split("/to")[0].split("/from")[1].isBlank()) {
                throw new EmptyClauseException("from");
            }
        } else { // unknown command
            throw new UnknownCommandException();
        }
        return true;
    }

    public static void main (String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Storage sr = new Storage();
        TaskBook tb;
        try {
            tb = new TaskBook(sr.retrieveTasks());
        } catch (FileNotFoundException e) {
            tb = new TaskBook();
        }
        String input = "lorem ipsum";

        greetingDialogue();

        while (!input.equals("bye")) {
            try {
                input = br.readLine();
                if (input.equals("bye")) {
                    break;
                }
                String splitInput[] = input.split(" ");
                String command = splitInput[0];
                int index = -1;

                switch (command) {
                case "list":
                    tb.listTasks();
                    break;
                case "mark":
                    index = Integer.parseInt(splitInput[1]);
                    if (tb.indexWithinRange(index)) {
                        tb.markDone(index);
                    }
                    break;
                case "unmark":
                    index = Integer.parseInt(splitInput[1]);
                    index = Integer.parseInt(splitInput[1]);
                    if (tb.indexWithinRange(index)) {
                        tb.markNotDone(index);
                    }
                    break;
                case "delete":
                    index = Integer.parseInt(splitInput[1]);
                    if (tb.indexWithinRange(index)) {
                        tb.deleteTask(index);
                    }
                    break;
                default:
                    TaskType taskType = TaskType.TODO;

                    if (isValidTaskCommand(input)) {
                        if (command.equals("todo")) {
                            taskType = TaskType.TODO;
                        } else if (command.equals("deadline")) {
                            taskType = TaskType.DEADLINE;
                        } else if (command.equals("event")) {
                            taskType = TaskType.EVENT;
                        }

                        tb.addTask(taskType, input);
                    }
                    break;
                }
            } catch (DukeException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
        sr.saveTasks(tb);
        byeDialogue();
        br.close();
    }
}
